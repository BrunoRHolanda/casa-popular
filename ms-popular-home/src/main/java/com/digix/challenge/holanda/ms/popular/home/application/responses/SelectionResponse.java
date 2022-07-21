package com.digix.challenge.holanda.ms.popular.home.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SelectionResponse {
    private String id;
    private Integer ordnance;
    private Date start;
    private Date end;
    private RuleResponse[] rules;
}
