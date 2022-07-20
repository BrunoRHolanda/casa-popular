package com.digix.challenge.holanda.ms.popular.home.infrastructure.configuration;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.Model;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.Date;
import java.util.UUID;

@Configuration
@EnableJdbcRepositories("com.digix.challenge.holanda.ms.popular.home.application.data.repositories")
public class JpaConfiguration {
    @Bean
    BeforeConvertCallback<Model> beforeConvertCallback() {

        return (model) -> {
            if (model.getId() == null) {
                model.setId(UUID.randomUUID().toString());
                model.setActive(true);
                model.setCreatedAt(new Date());
                model.setUpdatedAt(new Date());
            } else {
                model.setUpdatedAt(new Date());
            }

            return model;
        };
    }
}
