package uk.co.santander.induction.manager.service;

import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import uk.co.santander.induction.manager.Application;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class MyServiceTest {

    public static WireMockServer wiremock = new WireMockServer();

    @AfterAll
    static void clean() {
        wiremock.shutdown();
    }

    @BeforeAll
    static void setupClass() {
        wiremock.start();
    }

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MyService myService;

    @BeforeEach
    public void setup() {
        myService.setBase("http://localhost:" + wiremock.port());
    }

    @AfterEach
    void after() {
        wiremock.resetAll();
    }

    @Test
    void shouldCallRegistry() throws JsonProcessingException {
        final String targetUrl = "/miPago";
        final RegistryResponse registryResponse = RegistryResponse.builder()
                .paymentsHubId(UUID.randomUUID().toString()).status("PENDING").build();

        wiremock.stubFor(post(urlEqualTo(targetUrl))
                //.withRequestBody()  // debtor creditor ammount
                .willReturn(new ResponseDefinitionBuilder().withHeader("Content-Type", APPLICATION_JSON.toString())
                        .withBody(objectMapper.writeValueAsString(registryResponse)).withStatus(201)));

        myService.expectedLogicBasedOnUserStory();

        WireMock.verify(1, postRequestedFor(urlEqualTo(targetUrl)));
    }
}