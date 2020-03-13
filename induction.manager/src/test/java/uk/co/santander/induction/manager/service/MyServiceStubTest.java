package uk.co.santander.induction.manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.santander.induction.manager.Application;
import uk.co.santander.induction.manager.config.ConfigValues;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureStubRunner(
        ids = "uk.co.santander:induction.registry:+:stubs:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class MyServiceStubTest {

    @StubRunnerPort("induction.registry")
    private int stubRunnerPort;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MyService myService;

    @Autowired
    private ConfigValues values;

    @Test
    public void happyPath() throws Exception {
        final PaymentRequest givenPaymentRequest = PaymentRequest.builder()
                .origen("debtor")
                .destino("creditor")
                .reference("miReferencia")
                .cantidad(1.1)
                .build();

        RegistryResponse actualRegistryResponse = myService.expectedLogicBasedOnUserStory(givenPaymentRequest);
    }
}
