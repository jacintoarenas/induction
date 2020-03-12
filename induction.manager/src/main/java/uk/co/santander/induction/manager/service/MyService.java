package uk.co.santander.induction.manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.RegistryRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;


@Service
public class MyService {

    @Value("${myBaseUrl}")
    private String baseUrl;

    public RegistryResponse expectedLogicBasedOnUserStory(PaymentRequest paymentRequest) {
        final HttpEntity<RegistryRequest> entity = new HttpEntity<RegistryRequest>(buildRegistryRequest(paymentRequest));
        final ResponseEntity<RegistryResponse> registryResponse = new RestTemplate()
                .postForEntity(baseUrl + "/miPago", entity, RegistryResponse.class);
        return registryResponse.getBody();
    }

    RegistryRequest buildRegistryRequest(PaymentRequest paymentRequest) {
        return RegistryRequest.builder()
                .debtor(paymentRequest.getOrigen())
                .creditor(paymentRequest.getDestino())
                .amount(paymentRequest.getCantidad())
                .build();
    }
}
