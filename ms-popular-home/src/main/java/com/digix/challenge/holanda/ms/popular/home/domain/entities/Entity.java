package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import java.util.Date;
import java.util.UUID;

public abstract class Entity {
    protected UUID id;
    protected boolean Active;
    protected Date createdAt;
    protected Date updatedAt;
}
