package com.digix.challenge.holanda.ms.popular.home.application.data.repositories;

import com.digix.challenge.holanda.ms.popular.home.domain.entities.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FamilyRepository extends CrudRepository<Family, UUID> {
}
