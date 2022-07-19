package com.digix.challenge.holanda.ms.popular.home.domain.valueobjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CpfTest {
    @ParameterizedTest(name = "CPF(Valid) = true")
    @DisplayName("Verifica se a validação do CPF irá retornar verdadeiro passado um CPF válido")
    @CsvSource({
            "82515446027",
            "18972637084",
            "20095237097",
            "81194996035"
    })
    void assertValidCpfTest(String cpf) {
        assertTrue((new Cpf(cpf)).validate());
    }

    @ParameterizedTest(name = "CPF(invalid) = false")
    @DisplayName("Verifica se a validação do CPF irá retornar false passado um CPF inválido")
    @CsvSource({
            "11111111111",
            "22222222222",
            "33333333333",
            "12345678895"
    })
    void assertInvalidCpfTest(String cpf) {
        assertFalse((new Cpf(cpf)).validate());
    }
}
