package com.digix.challenge.holanda.ms.popular.home.application.repositories;

import com.digix.challenge.holanda.ms.popular.home.application.datasource.DatabaseAdapter;
import com.digix.challenge.holanda.ms.popular.home.application.datasource.JdbcDatabaseAdapter;
import com.digix.challenge.holanda.ms.popular.home.domain.entities.Person;
import jdk.internal.access.SharedSecrets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public class FamilyJdbcRepository implements FamilyRepository {

    private final DatabaseAdapter database;

    @Autowired
    public FamilyJdbcRepository(JdbcDatabaseAdapter database) {
        this.database = database;
    }

    public Person createPerson() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        this.database.save(
                "INSERT INTO person (id, name, age, cpf, active, created_at, updated_at) VALUES(?,?,?,?,?,?,?)",
                new Object[] {uuid, "Bruno Holanda", 28, "05247619145", true, new Date(), new Date()}
        );

        return this.database.findOne("SELECT * FROM person WHERE id = ?", uuid.toString(), Person.class);
    }
}
