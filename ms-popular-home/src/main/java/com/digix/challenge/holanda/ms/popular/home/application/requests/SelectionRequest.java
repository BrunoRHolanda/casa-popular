package com.digix.challenge.holanda.ms.popular.home.application.requests;

import lombok.Data;

import java.util.Date;

@Data
public class SelectionRequest {
    private Integer ordinance;
    private String cityId;
    private Date start;
    private Date end;
    private String[] rules;
}
