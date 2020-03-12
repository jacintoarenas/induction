package uk.co.santander.induction.manager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.PaymentResponse;
import uk.co.santander.induction.manager.service.MyService;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestController
@AllArgsConstructor
@Slf4j
public class Controller {

    final MyService myService;

    @PostMapping("/miPago")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse miPost(@RequestBody PaymentRequest paymentRequest){
        myService.expectedLogicBasedOnUserStory(paymentRequest);
        return PaymentResponse
                .builder()
                .paymentsHubId("132")
                .status("pending")
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public void handleUnexpectedException(Exception e) {
        log.info("Unexpected error", e);
    }
}
