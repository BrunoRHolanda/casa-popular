package com.digix.challenge.holanda.ms.popular.home.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityResponse {
    private String id;
    private String name;
    private Integer code;
    private StateResponse state;
    private boolean active;
}
