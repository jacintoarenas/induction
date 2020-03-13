package uk.co.santander.induction.manager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.santander.induction.manager.Application;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class MyServiceTest {

    private static WireMockServer wiremock = new WireMockServer();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MyService myService;

    @BeforeEach
    void setup() {
        wiremock.start();
    }

    @AfterEach
    void after() {
        wiremock.resetAll();
    }

    @AfterAll
    static void clean() {
        wiremock.shutdown();
    }

    @Test
    void shouldCallRegistry() throws Exception {
        final PaymentRequest givenPaymentRequest = PaymentRequest.builder().origen("debtor").destino("creditor")
                .reference("miReferencia").cantidad(1.1).build();
        final RegistryResponse expectedRegistryResponse = RegistryResponse.builder()
                .paymentsHubId(UUID.randomUUID().toString()).status("PENDING").build();

        wiremock.stubFor(post(urlEqualTo("/miPago"))
                .withRequestBody(equalToJson("{\"debtor\":\"debtor\",\"creditor\":\"creditor\",\"amount\":1.1}"))
                .willReturn(new ResponseDefinitionBuilder().withHeader("Content-Type", APPLICATION_JSON.toString())
                        .withBody(objectMapper.writeValueAsString(expectedRegistryResponse))
                        .withStatus(HttpStatus.CREATED.value())));

        final RegistryResponse actualRegistryResponse = myService.expectedLogicBasedOnUserStory(givenPaymentRequest);

        WireMock.verify(1, postRequestedFor(urlEqualTo("/miPago")));
        JSONAssert.assertEquals(objectMapper.writeValueAsString(expectedRegistryResponse),
                objectMapper.writeValueAsString(actualRegistryResponse), true);
    }
}