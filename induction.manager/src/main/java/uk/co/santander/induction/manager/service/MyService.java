package uk.co.santander.induction.manager.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

@Service
@Slf4j
public class MyService {

    String base = null;

    public RegistryResponse expectedLogicBasedOnUserStory(PaymentRequest paymentRequest) {
        final HttpEntity<PaymentRequest> entity = new HttpEntity<PaymentRequest>(buildPaymentRequest());
        final ResponseEntity<RegistryResponse> registryResponse = new RestTemplate().postForEntity(base + "/miPago", entity,
                RegistryResponse.class);

        log.info(registryResponse.getBody().getPaymentsHubId());
        log.info(registryResponse.getBody().getStatus());

        return registryResponse.getBody();
    }

    public void setBase(final String string) {
        base = string;
    }

    PaymentRequest buildPaymentRequest() {
        return PaymentRequest.builder().origen("origen").destino("destino").reference("miReferencia").cantidad(10.0)
                .build();
    }
}
