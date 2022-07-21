package com.digix.challenge.holanda.ms.benefited.catalog.controller;

import com.digix.challenge.holanda.ms.benefited.catalog.collection.BenefitedCatalog;
import com.digix.challenge.holanda.ms.benefited.catalog.repository.BenefitedRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BenefitedController {
    private final BenefitedRepository repository;

    public BenefitedController(BenefitedRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/api/selections/{ordnance}")
    public BenefitedCatalog index(@PathVariable Integer ordnance) {
        return this.repository.findByOrdinance(ordnance);
    }

    @PostMapping(path ="/api/selections")
    public BenefitedCatalog store(@RequestBody BenefitedCatalog catalog) {
        BenefitedCatalog catalogOld = this.repository.findByOrdinance(catalog.getOrdinance());

        this.repository.delete(catalogOld);
        this.repository.save(catalog);

        return this.repository.findByOrdinance(catalog.getOrdinance());
    }
}
