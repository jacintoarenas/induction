package uk.co.santander.induction.manager.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner(
        ids = "uk.co.santander:induction.registry:+:stubs:8181",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class MyServiceTestStub {
    @StubRunnerPort("induction.registry")
    private int stubRunnerPort;


}
