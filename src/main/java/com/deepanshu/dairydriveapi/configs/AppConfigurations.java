package com.deepanshu.dairydriveapi.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class AppConfigurations {

    private int trialDays;
    private String baseUrl;
}
