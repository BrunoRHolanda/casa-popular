package com.digix.challenge.holanda.ms.popular.home.infrastructure.http.client.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitedCatalog implements Serializable {
    private Integer ordinance;
    private Catalog[] catalog;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Catalog {
        private Family family;
        private Integer score;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Family {
            private String suitor;
            private String cpf;
        }
    }
}
