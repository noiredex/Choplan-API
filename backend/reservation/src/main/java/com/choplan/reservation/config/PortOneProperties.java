package com.choplan.reservation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
@ConfigurationProperties(prefix = "portone")
public class PortOneProperties {
    private String apiKey;
    private String apiSecret;
    private String webhookSecret;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
    public void setWebhookSecret(String webhookSecret) {
        this.webhookSecret = webhookSecret;
    }
}
