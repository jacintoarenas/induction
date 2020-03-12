package uk.co.santander.induction.registry.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uk.co.santander.induction.registry.controller.model.PaymentRequest;
import uk.co.santander.induction.registry.controller.model.PaymentResponse;

import java.util.UUID;

//import uk.co.santander.induction.registry.service.MyService;

@RestController
@AllArgsConstructor
@Slf4j
public class Controller {

    @PostMapping(path = "/miPago", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse miPost(@RequestBody PaymentRequest paymentRequest){
        return PaymentResponse
                .builder()
                .paymentsHubId(UUID.randomUUID().toString())
                .status("PENDING")
                .build();
    }

    /*
    @ExceptionHandler(Exception.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public void handleUnexpectedException(Exception e) {
        log.info("Unexpected error", e);
    }
     */
}
