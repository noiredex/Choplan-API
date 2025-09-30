package com.team.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "portone")
public class PortOneProps {
    private String baseUrl;
    private String apiKey;
    private String apiSecret;
    private String webhookSecret;

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String v) { this.baseUrl = v; }

    public String getApiKey() { return apiKey; }
    public void setApiKey(String v) { this.apiKey = v; }

    public String getApiSecret() { return apiSecret; }
    public void setApiSecret(String v) { this.apiSecret = v; }
}
