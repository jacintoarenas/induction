package uk.co.santander.induction.manager.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistryRequest {

    private String debtor;
    private String creditor;
    private double amount;
}
