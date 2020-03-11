package uk.co.santander.induction.manager.service;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.santander.induction.manager.Application;

import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class MyServiceTest {

    @Autowired
    MyService myService;

    @Test
    void shouldCallRegistry() {
        String targetUr = "/miPago";
        //crear stub igual que el original del que acabamos de crear en el contrato del registry


        myService.expectedLogicBasedOnUserStory();

        WireMock.verify(1, postRequestedFor(urlEqualTo(targetUr)));
    }
}