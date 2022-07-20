package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Model {
    @Id
    private String id;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;
}
