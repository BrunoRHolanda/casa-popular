package com.digix.challenge.holanda.ms.popular.home.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationExceptionResponse {
    private String message;
    private Integer code;
}
