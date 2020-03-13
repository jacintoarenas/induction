package uk.co.santander.induction.manager.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uk.co.santander.induction.manager.controller.model.PaymentRequest;
import uk.co.santander.induction.manager.controller.model.RegistryRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

@FeignClient(
        url = "${myBaseUrl}",
        name = "registry-client"
)
public interface RegistryClient {

    @PostMapping("/miPago")
    RegistryResponse realizarPago(@RequestBody RegistryRequest registryRequest);
}
