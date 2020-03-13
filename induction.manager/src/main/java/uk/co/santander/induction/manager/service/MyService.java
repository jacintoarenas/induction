package uk.co.santander.induction.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.RegistryRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

@Service
public class MyService {

    @Autowired
    private ClientRegistryService servicioRegistry;

    public RegistryResponse expectedLogicBasedOnUserStory(final PaymentRequest paymentRequest) {
        return servicioRegistry.getRegistryResponse(buildRegistryRequest(paymentRequest));
    }

    private RegistryRequest buildRegistryRequest(final PaymentRequest paymentRequest) {
        return RegistryRequest.builder().debtor(paymentRequest.getOrigen()).creditor(paymentRequest.getDestino())
                .amount(paymentRequest.getCantidad()).build();
    }
}
