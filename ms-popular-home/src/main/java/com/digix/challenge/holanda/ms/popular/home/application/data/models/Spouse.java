package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Date;

@Data
public class Spouse extends Model {
    @NonNull
    private String personId;
}
