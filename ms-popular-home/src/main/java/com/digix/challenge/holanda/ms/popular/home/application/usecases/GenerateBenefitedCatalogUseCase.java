package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.SelectionRule;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.*;
import com.digix.challenge.holanda.ms.popular.home.application.requests.GenerateBenefitedCatalogRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.BenefitedCatalogResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.FamilyResponse;
import com.digix.challenge.holanda.ms.popular.home.domain.entities.*;
import com.digix.challenge.holanda.ms.popular.home.domain.entities.factories.RuleFactory;
import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.BusinessLogicException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Email;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.ZipCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class GenerateBenefitedCatalogUseCase implements UseCase<GenerateBenefitedCatalogRequest, BenefitedCatalogResponse> {

    private final SelectionRepository selectionRepository;
    private final RuleRepository ruleRepository;
    private final SuitorRepository suitorRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final SpouseRepository spouseRepository;
    private final FamilyRepository familyRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    @SneakyThrows
    @Override
    public BenefitedCatalogResponse handle(GenerateBenefitedCatalogRequest request) throws ValidationException {
        Selection selection = this.loadSelection(request.getSelection());

        ArrayList<FamilySelection> domainFamiliesSelections = selection.generatePriorityList();

        BenefitedCatalogResponse.Catalog[] benefited =
                new BenefitedCatalogResponse.Catalog[domainFamiliesSelections.size()];

        var i = 0;

        for (FamilySelection familySelection : domainFamiliesSelections) {
            String cpf = "";

            if (familySelection.getFamily().getSuitor().getCpf() != null) {
                cpf = familySelection.getFamily().getSuitor().getCpf().make();
            }
            benefited[i] = new BenefitedCatalogResponse.Catalog(
                    new BenefitedCatalogResponse.FamilyResponse(
                        familySelection.getFamily().getSuitor().getName(),
                        cpf
                    ),
                    familySelection.calculateScore()
            );

            i++;
        }

        return new BenefitedCatalogResponse(
                selection.getOrdinance(),
                benefited
        );
    }

    private Selection loadSelection(String selectionId) throws BusinessLogicException {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.Selection selectionData
                = this.selectionRepository.findById(selectionId).get();

        Selection selection = new Selection(
                selectionData.getOrdinance(),
                this.loadCity(selectionData.getCityId()),
                selectionData.getStart(),
                selectionData.getEnd(),
                this.loadRules(selectionData)
        );

        var familiesSelections = new ArrayList<FamilySelection>();

        for (com.digix.challenge.holanda.ms.popular.home.application.data.models.FamilySelection familiesSelectionsData : selectionData.getFamilySelections()) {
            com.digix.challenge.holanda.ms.popular.home.application.data.models.Family familyData =
                    this.familyRepository.findById(familiesSelectionsData.getFamilyId()).get();
            Family domainFamily = new Family(
                this.loadSuitor(familyData.getSuitorId()),
                this.loadSpouse(familyData.getSpouseId()),
                this.loadAddress(familyData.getAddressId()),
                familyData.getIncome()
            );

            for (com.digix.challenge.holanda.ms.popular.home.application.data.models.Dependents d : familyData.getDependents()) {
                Person domainPerson = this.loadPerson(d.getPersonId());

                domainFamily.addDependent(domainPerson);
            }

            familiesSelections.add(new FamilySelection(domainFamily, selection));
        }

        selection.setFamilySelections(familiesSelections);

        return selection;
    }

    private Address loadAddress(String addressId) {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.Address addressData =
                this.addressRepository.findById(addressId).get();
        District domainDistrict = this.loadDistrict(addressData.getDistrictId());

        Address domainAddress = new Address(
            domainDistrict,
            addressData.getStreet(),
            new ZipCode(addressData.getZipCode())
        );

        if (addressData.getNumber() != null) {
            domainAddress.setNumber(addressData.getNumber());
        }

        if (addressData.getComplement() != null) {
            domainAddress.setComplement(addressData.getComplement());
        }

        return domainAddress;
    }
    private Spouse loadSpouse(String spouseId) {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.Spouse spouseData =
                this.spouseRepository.findById(spouseId).get();
        Person personSpouseDomain = this.loadPerson(spouseData.getPersonId());

        return new Spouse(
                personSpouseDomain.getName(),
                personSpouseDomain.getAge(),
                personSpouseDomain.getCpf()
        );
    }
    private Suitor loadSuitor(String suitorId) {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.Suitor suitorData =
                this.suitorRepository.findById(suitorId).get();
        Person personSuitor = this.loadPerson(suitorData.getPersonId());

        return new Suitor(
                new Phone(suitorData.getPhone()),
                new Email(suitorData.getEmail()),
                personSuitor.getName(),
                personSuitor.getAge(),
                personSuitor.getCpf()
        );
    }
    private Person loadPerson(String personId) {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.Person personData =
                this.personRepository.findById(personId).get();

        Person domainPerson = new Person(personData.getName(), personData.getAge());

        if (personData.getCpf() != null) {
            domainPerson.setCpf(new Cpf(personData.getCpf()));
        }

        return domainPerson;
    }

    private Map<RuleType, Rule> loadRules(com.digix.challenge.holanda.ms.popular.home.application.data.models.Selection selectionData) throws BusinessLogicException {
        var rules = new HashMap<RuleType, Rule>();

        for (SelectionRule sr : selectionData.getSelectionRules()) {
            com.digix.challenge.holanda.ms.popular.home.application.data.models.Rule ruleData
                    = this.ruleRepository.findById(sr.getRuleId()).get();

            rules.put(ruleData.getType(), RuleFactory.create(ruleData.getType()));
        }

        return rules;
    }

    private District loadDistrict(String districtId) {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.District districtData =
                this.districtRepository.findById(districtId).get();
        City cityDomain = this.loadCity(districtData.getCityId());

        return new District(cityDomain, districtData.getName(), districtData.getCode());
    }
    private City loadCity(String cityId) {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.City cityData =
                this.cityRepository.findById(cityId).get();
        State domainState = this.loadState(cityData.getStateId());

        return new City(domainState, cityData.getName(), cityData.getCode());
    }

    private State loadState(String stateId) {
        com.digix.challenge.holanda.ms.popular.home.application.data.models.State stateData =
                this.stateRepository.findById(stateId).get();

        return new State(stateData.getName(), stateData.getCode());
    }
}
