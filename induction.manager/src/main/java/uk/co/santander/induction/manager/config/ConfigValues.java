package uk.co.santander.induction.manager.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConfigValues {

    @Value("${myBaseUrl}")
    private String baseUrl;

}
