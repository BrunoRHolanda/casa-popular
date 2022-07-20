package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class State extends Model {
    @NonNull
    private String name;

    @NonNull
    private Integer code;
}
