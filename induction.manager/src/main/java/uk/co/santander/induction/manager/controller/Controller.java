package uk.co.santander.induction.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uk.co.santander.induction.manager.controller.model.PaymentResponse;

@RestController
public class Controller {

    @PostMapping("/miPago")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse miPost(@RequestBody PaymentRequest paymentRequest) {
        return PaymentResponse
                .builder()
                .paymentsHubId("132")
                .status("pending")
                .build();
    }
}
