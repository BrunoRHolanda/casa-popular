package com.digix.challenge.holanda.ms.popular.home.application.requests;

import lombok.Data;

@Data
public class CityRequest {
    private String name;
    private Integer code;
    private String stateId;
}
