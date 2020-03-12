package uk.co.santander.induction.manager.controller.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PaymentResponse {

    @NotNull
    private String paymentsHubId;

    private String status;
}
