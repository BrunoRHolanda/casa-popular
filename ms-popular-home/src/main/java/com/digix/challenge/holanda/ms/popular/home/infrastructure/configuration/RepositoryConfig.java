package com.digix.challenge.holanda.ms.popular.home.infrastructure.configuration;

import com.digix.challenge.holanda.ms.popular.home.application.datasource.JdbcDatabaseAdapter;
import com.digix.challenge.holanda.ms.popular.home.application.repositories.FamilyJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.digix.challenge.holanda.ms.popular.home.application.repositories")
public class RepositoryConfig {
    @Bean
    public FamilyJdbcRepository familyJdbcRepository(final JdbcDatabaseAdapter databaseAdapter) {
        return new FamilyJdbcRepository(databaseAdapter);
    }
}
