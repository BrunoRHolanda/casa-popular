package com.digix.challenge.holanda.ms.benefited.catalog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages ="com.digix.challenge.holanda.ms.benefited.catalog.repository")
public class MongoConfig {
}
