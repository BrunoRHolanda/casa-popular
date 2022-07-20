package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Data
public class Spouse extends Model {
    @MappedCollection(keyColumn = "person_id", idColumn = "id")
    private Person person;
}
