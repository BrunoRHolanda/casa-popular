package com.digix.challenge.holanda.ms.popular.home.application.data.repositories;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {
}
