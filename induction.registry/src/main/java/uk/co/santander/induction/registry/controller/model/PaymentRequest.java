package uk.co.santander.induction.registry.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    /*
    String origen;
    String destino;
    String reference;
    */
    double cantidad;
}
