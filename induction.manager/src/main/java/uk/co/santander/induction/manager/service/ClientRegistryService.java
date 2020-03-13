package uk.co.santander.induction.manager.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import uk.co.santander.induction.manager.controller.model.RegistryRequest;
import uk.co.santander.induction.manager.controller.model.RegistryResponse;

@FeignClient(name = "pagos", url = "${myBaseUrl}")
public interface ClientRegistryService {

	@PostMapping(value = "/miPago", consumes = "application/json")
	RegistryResponse getRegistryResponse(RegistryRequest request);

}
