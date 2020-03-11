package uk.co.santander.induction.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
