package com.digix.challenge.holanda.ms.benefited.catalog.repository;

import com.digix.challenge.holanda.ms.benefited.catalog.collection.BenefitedCatalog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BenefitedRepository extends MongoRepository<BenefitedCatalog, String> {
    public BenefitedCatalog findByOrdinance(Integer ordnance);
}
