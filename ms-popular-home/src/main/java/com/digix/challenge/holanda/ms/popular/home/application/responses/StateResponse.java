package com.digix.challenge.holanda.ms.popular.home.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StateResponse {
    private String id;
    private String name;
    private Integer code;
    private boolean active;
}
