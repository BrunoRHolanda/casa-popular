package com.digix.challenge.holanda.ms.popular.home.application.requests;

import lombok.Data;

@Data
public class DistrictRequest {
    private String name;
    private Integer code;
    private String cityId;
}
