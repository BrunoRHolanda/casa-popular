package com.digix.challenge.holanda.ms.popular.home.infrastructure.http.client;

import com.digix.challenge.holanda.ms.popular.home.application.responses.BenefitedCatalogResponse;
import com.digix.challenge.holanda.ms.popular.home.infrastructure.http.client.pojo.BenefitedCatalog;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MsBenefitedCatalogHttp {
    private String baseUrl = "http://ms-benefited-catalog:8092/api";
    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public MsBenefitedCatalogHttp() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public void publishCatalog(BenefitedCatalogResponse catalogBenefitedCatalogResponse) {
        BenefitedCatalog.Catalog[] catalogPOJO =
                new BenefitedCatalog.Catalog[catalogBenefitedCatalogResponse.getCatalog().length];

        var i = 0;

        for(var benefited : catalogBenefitedCatalogResponse.getCatalog()) {
            BenefitedCatalog.Catalog benefitedPOJO = new BenefitedCatalog.Catalog(
                    new BenefitedCatalog.Catalog.Family(
                            benefited.getFamily().getSuitor(),
                            benefited.getFamily().getCpf()
                    ),
                    benefited.getScore()
            );
            catalogPOJO[i] = benefitedPOJO;
            i++;
        }

        HttpEntity<BenefitedCatalog> requestEntity = new HttpEntity<>(new BenefitedCatalog(
                catalogBenefitedCatalogResponse.getOrdinance(),
                catalogPOJO
        ), this.headers);

        ResponseEntity<BenefitedCatalog> responseEntity =
                rest.exchange(
                        this.baseUrl + "/selections",
                        HttpMethod.POST,
                        requestEntity,
                        BenefitedCatalog.class
        );

        this.setStatus(responseEntity.getStatusCode());
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
