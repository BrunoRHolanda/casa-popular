package com.digix.challenge.holanda.ms.popular.home.application.data.repositories;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
}
