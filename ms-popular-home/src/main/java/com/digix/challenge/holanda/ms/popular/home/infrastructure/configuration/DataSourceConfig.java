package com.digix.challenge.holanda.ms.popular.home.infrastructure.configuration;

import com.digix.challenge.holanda.ms.popular.home.application.datasource.JdbcDatabaseAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "com.digix.challenge.holanda.ms.popular.home.application.datasource")
public class DataSourceConfig {
    @Bean
    public JdbcDatabaseAdapter jdbcDatabaseAdapter(final JdbcTemplate template) {
        return new JdbcDatabaseAdapter(template);
    }
}
