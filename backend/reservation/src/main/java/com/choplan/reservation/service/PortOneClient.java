package com.choplan.reservation.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.choplan.reservation.config.PortOneProperties;
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
        String body = String.format("{\\\"imp_key\\\":\\\"%s\\\",\\\"imp_secret\\\":\\\"%s\\}", props.getApiKey(), props.getApiSecret());
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> resp = restTemplate.postForEntity(url, entity, Map.class);
        Map data = (Map) resp.getBody().get("response");
        return (String) data.get("access_token");    
    }

    public Map<String,Object> getPaymentByImpUid(String accessToken, String impUid) {
        String url = "https://api.iamport.kr/payments/" + impUid; // v1 결제정보조회
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> resp = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        return (Map<String,Object>) resp.getBody().get("response");
    }
}
