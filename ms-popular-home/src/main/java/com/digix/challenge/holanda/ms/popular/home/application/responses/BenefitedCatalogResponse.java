package com.digix.challenge.holanda.ms.popular.home.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BenefitedCatalogResponse {
    private Integer ordinance;
    private Catalog[] catalog;

    @Data
    @AllArgsConstructor
    public static class Catalog {
        private FamilyResponse family;
        private Integer score;
    }

    @Data
    @AllArgsConstructor
    public static class FamilyResponse {
        String suitor;
        String cpf;
    }
}
