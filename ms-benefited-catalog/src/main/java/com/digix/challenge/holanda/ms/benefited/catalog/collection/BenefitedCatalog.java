package com.digix.challenge.holanda.ms.benefited.catalog.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "benefited_catalog")
@Data
public class BenefitedCatalog {
    @Id
    private String id;
    @NonNull
    private Integer ordinance;
    @NonNull
    private Catalog[] catalog;

    @Data
    public static class Catalog {
        @NonNull
        private Family family;
        @NonNull
        private Integer score;

        @Data
        public static class Family {
            @NonNull
            private String suitor;
            @NonNull
            private String cpf;
        }
    }
}
