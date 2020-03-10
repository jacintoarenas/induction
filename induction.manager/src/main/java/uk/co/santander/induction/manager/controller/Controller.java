package uk.co.santander.induction.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping("/miPago")
    @ResponseStatus(HttpStatus.CREATED)
    public String miPost(@RequestBody PaymentRequest paymentRequest) {
        return "hola";
    }
}
