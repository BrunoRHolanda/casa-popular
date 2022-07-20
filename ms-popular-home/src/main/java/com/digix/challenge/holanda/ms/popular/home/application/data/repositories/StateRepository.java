package com.digix.challenge.holanda.ms.popular.home.application.data.repositories;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.State;
import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<State, String> {
}
