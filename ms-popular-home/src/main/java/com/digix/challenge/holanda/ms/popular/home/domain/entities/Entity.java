package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidCpfException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

@Data
public abstract class Entity {
    private String id;

    private boolean active;
    private Date createdAt;
    private Date updatedAt;

    public Entity() {

    }

    public Entity(String id, boolean active, Date createdAt, Date updatedAt) {
        this.id = id;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void refreshUpdated() {
        this.updatedAt = new Date();
    }

    public void defineCreatedAt() {
        this.createdAt = new Date();
    }

    public abstract void validate() throws ValidationException;
}
