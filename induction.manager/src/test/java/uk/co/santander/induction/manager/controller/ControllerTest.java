package uk.co.santander.induction.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.santander.induction.manager.Application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldAllowPostPayment() throws Exception {
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .origen("origen")
                .destino("destino")
                .reference("miReferencia")
                .cantidad(10.0)
                .build();
        String body = objectMapper.writeValueAsString(paymentRequest);
        mockMvc.perform(post("/miPago")
                .contentType(APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.paymentsHubId", notNullValue()))
//                .andExpect(jsonPath("$.status", equalTo("pending")));
    }

}