package uk.co.santander.induction.manager.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.santander.induction.manager.client.RegistryClient;
import uk.co.santander.induction.manager.config.ConfigValues;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.RegistryRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

import javax.validation.constraints.NotNull;


@Service
@RequiredArgsConstructor
public class MyService {

    @NonNull
    private ConfigValues configValues;

    @NonNull
    private RegistryClient registryClient;

    public RegistryResponse expectedLogicBasedOnUserStory(PaymentRequest paymentRequest) {
        final HttpEntity<RegistryRequest> entity = new HttpEntity<RegistryRequest>(buildRegistryRequest(paymentRequest));
        final ResponseEntity<RegistryResponse> registryResponse = new RestTemplate()
                .postForEntity(configValues.getBaseUrl() + "/miPago", entity, RegistryResponse.class);
        return registryResponse.getBody();
    }

    public RegistryResponse expectedFeingLogicBasedOnUserStory(PaymentRequest givenPaymentRequest) {
        return registryClient.realizarPago(buildRegistryRequest(givenPaymentRequest));
    }

    private RegistryRequest buildRegistryRequest(PaymentRequest paymentRequest) {
        return RegistryRequest.builder()
                .debtor(paymentRequest.getOrigen())
                .creditor(paymentRequest.getDestino())
                .amount(paymentRequest.getCantidad())
                .build();
    }

}
