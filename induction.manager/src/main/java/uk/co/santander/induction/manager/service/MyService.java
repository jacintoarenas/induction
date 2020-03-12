package uk.co.santander.induction.manager.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.PaymentResponseStub;

@Service
@Slf4j
public class MyService {

    String base = null;

    public PaymentResponseStub expectedLogicBasedOnUserStory() {
        final HttpEntity<PaymentRequest> entity = new HttpEntity<PaymentRequest>(buildPaymentRequest());
        final ResponseEntity<PaymentResponseStub> re = new RestTemplate().postForEntity(base + "/miPago", entity,
                PaymentResponseStub.class);

        log.info(re.getBody().getPaymentsHubId());
        log.info(re.getBody().getStatus());

        return re.getBody();
    }

    public void setBase(final String string) {
        base = string;
    }

    PaymentRequest buildPaymentRequest() {
        return PaymentRequest.builder().origen("origen").destino("destino").reference("miReferencia").cantidad(10.0)
                .build();
    }
}
