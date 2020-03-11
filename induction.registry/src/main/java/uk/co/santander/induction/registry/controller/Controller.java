package uk.co.santander.induction.registry.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.co.santander.induction.registry.controller.model.PaymentRequest;
import uk.co.santander.induction.registry.controller.model.PaymentResponse;
//import uk.co.santander.induction.registry.service.MyService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

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
