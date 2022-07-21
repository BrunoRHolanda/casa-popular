package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.*;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.*;
import com.digix.challenge.holanda.ms.popular.home.application.requests.FamilyRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.FamilyResponse;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Email;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;

public class CreateFamilyUseCase implements UseCase<FamilyRequest, FamilyResponse> {
    private final SuitorRepository suitorRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final SpouseRepository spouseRepository;
    private final FamilyRepository familyRepository;

    public CreateFamilyUseCase(
        SuitorRepository suitorRepository,
        PersonRepository personRepository,
        AddressRepository addressRepository,
        SpouseRepository spouseRepository,
        FamilyRepository familyRepository
    ) {
        this.suitorRepository = suitorRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.spouseRepository = spouseRepository;
        this.familyRepository = familyRepository;
    }
    @Override
    public FamilyResponse handle(FamilyRequest request) throws ValidationException {
        Address address = this.createAddress(request.getAddress());
        Suitor suitor = this.createSuitor(request.getSuitor());
        Spouse spouse = this.createSpouse(request.getSpouse());

        Family family = new Family(address.getId(), spouse.getId(), suitor.getId(), request.getIncome());

        if (request.getDependents() != null) {
            for (FamilyRequest.PersonRequest dependentRequest : request.getDependents()) {
                family.addDependent(this.createPerson(dependentRequest));
            }
        }

        this.familyRepository.save(family);

        return new FamilyResponse(family.getId());
    }

    private Address createAddress(FamilyRequest.AddressRequest addressRequest) {
        Address address = new Address(
                addressRequest.getDistrictId(),
                addressRequest.getStreet(),
                addressRequest.getZipCode()
        );

        if (addressRequest.getNumber() != null) {
            address.setNumber(addressRequest.getNumber());
        }

        if (addressRequest.getComplement() != null) {
            address.setComplement(addressRequest.getComplement());
        }

        this.addressRepository.save(address);

        return address;
    }
    private Spouse createSpouse(FamilyRequest.SpouseRequest spouseRequest) throws ValidationException {
        Person person = this.createPerson(spouseRequest.getPerson());

        Spouse spouse = new Spouse(
                person.getId()
        );

        com.digix.challenge.holanda.ms.popular.home.domain.entities.Spouse domainSpouse =
                new com.digix.challenge.holanda.ms.popular.home.domain.entities.Spouse(
                        person.getName(),
                        person.getAge(),
                        new Cpf(person.getCpf())
                );

        domainSpouse.validate();

        this.spouseRepository.save(spouse);

        return spouse;
    }

    private Suitor createSuitor(FamilyRequest.SuitorRequest suitorRequest) throws ValidationException {
        Person person = this.createPerson(suitorRequest.getPerson());

        Suitor suitor = new Suitor(
                suitorRequest.getEmail(),
                suitorRequest.getPhone(),
                person.getId()
        );

        com.digix.challenge.holanda.ms.popular.home.domain.entities.Suitor domainSuitor =
                new com.digix.challenge.holanda.ms.popular.home.domain.entities.Suitor(
                        new Phone(suitor.getPhone()),
                        new Email(suitor.getEmail()),
                        person.getName(),
                        person.getAge(),
                        new Cpf(person.getCpf())
                );

        domainSuitor.validate();

        this.suitorRepository.save(suitor);

        return suitor;
    }

    private Person createPerson(FamilyRequest.PersonRequest personRequest) throws ValidationException {
        Person person = new Person(
                personRequest.getName(),
                personRequest.getAge()
        );

        if (personRequest.getCpf() != null) {
            person.setCpf(personRequest.getCpf());
        }

        com.digix.challenge.holanda.ms.popular.home.domain.entities.Person domainPerson =
                new com.digix.challenge.holanda.ms.popular.home.domain.entities.Person(
                        person.getName(),
                        person.getAge()
                );

        if (person.getCpf() != null) {
            domainPerson.setCpf(new Cpf(person.getCpf()));
        }

        domainPerson.validate();

        this.personRepository.save(person);

        return person;
    }
}
