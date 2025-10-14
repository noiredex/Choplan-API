package com.choplan.payment.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.choplan.payment.config.PortOneProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PortOneClient {
    private final PortOneProperties props;
    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken() {
        String url = "https://api.iamport.kr/users/getToken"; // v1 토큰

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "imp_key", props.getKey(),
                "imp_secret", props.getSecret());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> resp = restTemplate.postForEntity(url, entity, Map.class);

        Map<String, Object> response = (Map<String, Object>) resp.getBody().get("response");
        if (response == null || response.get("access_token") == null) {
            throw new IllegalStateException("PortOne 토큰 발급 실패: " + resp.getBody());
        }
        return (String) response.get("access_token");
    }

    public Map<String, Object> getPaymentByImpUid(String accessToken, String impUid) {
        String url = "https://api.iamport.kr/payments/" + impUid; // v1 결제정보조회
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> resp = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        return (Map<String, Object>) resp.getBody().get("response");
    }
}
