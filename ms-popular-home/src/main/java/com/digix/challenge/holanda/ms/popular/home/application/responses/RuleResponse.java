package com.digix.challenge.holanda.ms.popular.home.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RuleResponse {
    private String id;
    private Integer code;
    private String type;
    private boolean active;
}
