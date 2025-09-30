package com.team.project.payment;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.team.project.config.PortOneProps;

@Component
public class PortOneClient {
    private final RestClient http;
    private final PortOneProps props;

    public PortOneClient(PortOneProps props) {
        this.props = props;
        this.http = RestClient.builder().baseUrl(props.getBaseUrl()).build();
    }

    private String accessToken() {
        try {
            System.out.println("=== PortOne Token Request ===");
            System.out.println("API Key: " + props.getApiKey());
            System.out.println("API Secret: " + (props.getApiSecret() != null
                    ? "***" + props.getApiSecret().substring(Math.max(0, props.getApiSecret().length() - 4))
                    : "null"));

            var req = Map.of(
                    "imp_key", props.getApiKey(),
                    "imp_secret", props.getApiSecret());

            var res = http.post()
                    .uri("/users/getToken")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(req)
                    .retrieve()
                    .toEntity(Map.class);

            @SuppressWarnings("unchecked")
            var body = (Map<String, Object>) res.getBody();
            System.out.println("PortOne Response: " + body);

            if (body == null) {
                throw new RuntimeException("Empty response from PortOne API");
            }
            @SuppressWarnings("unchecked")
            var response = (Map<String, Object>) body.get("response");
            if (response == null) {
                System.out.println("Error in PortOne response: " + body);
                throw new RuntimeException("No response data from PortOne API: " + body);
            }
            String token = (String) response.get("access_token");
            System.out.println("Access token obtained: "
                    + (token != null ? "***" + token.substring(Math.max(0, token.length() - 4)) : "null"));
            return token;
        } catch (Exception e) {
            System.err.println("PortOne API Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to get PortOne access token: " + e.getMessage(), e);
        }
    }

    public Map<String, Object> getPaymentByImpUid(String impUid) {
        String token = accessToken();
        return http.get()
                .uri("/payments/{impUid}", impUid)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .getBody();
    }

    public Map<String, Object> cancelPayment(String impUid, Integer amount, String reason) {
        String token = accessToken();
        var payload = Map.of(
                "imp_uid", impUid,
                "amount", amount,
                "reason", reason);

        return http.post()
                .uri("/payments/cancel")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .getBody();
    }
}
