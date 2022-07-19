package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Email;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.ZipCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FamilyTest {
    @Test
    @DisplayName("Verifica se a validação da entidade familia está funcionando corretamente")
    void verifyValidationOfFamilyIsCorrect() {
        Family family = new Family(
                new Suitor(
                        new Phone("5567991920971"),
                        new Email("bruno@gamail.com"),
                        "Bruno Holanda",
                        28,
                        new Cpf("81194996035")
                ),
                new Spouse("Maria Camila", 30, new Cpf("20095237097")),
                new Address(
                        new District(
                                new City(
                                        new State(
                                                "Mato Grosso do Sul",
                                                53
                                        ),
                                        "Campo Grande",
                                        1550
                                ),
                                "Bairro",
                                303
                        ),
                        "Rua 2",
                        new ZipCode("78380000")
                ),
                (float) 1300.0
        );

        try {
            family.validate();
        } catch (Exception e) {
            assertNotEquals(ValidationException.class, e.getClass());
        }
    }
}
