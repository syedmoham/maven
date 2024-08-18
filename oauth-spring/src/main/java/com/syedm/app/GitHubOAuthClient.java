package com.syedm.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubOAuthClient {
    private static Logger logger = LoggerFactory.getLogger(GitHubOAuthClient.class);
    private final RestTemplate restTemplate;

    public GitHubOAuthClient(RestTemplate restTemplate)  {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken(String clientId, String clientSecret, String code, String redirectUri)   {
        String oauthUrl = "https://github.com/login/oauth/access_token";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        // request body with client ID, client secret, authorization code, and redirect Url
        String requestBody = "client_id=" + clientId +
                            "&client_secret=" + clientSecret +
                            "&code=" + code +
                            "&redirect_uri=" + redirectUri;

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(oauthUrl, HttpMethod.POST, requestEntity, String.class);

        String access_token = "no_token";

        if (responseEntity.getStatusCode().is2xxSuccessful())  {
            // Parse JSON response to extract access token
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try  {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                access_token = jsonNode.get("access_token").asText();
            }
            catch (Exception jex)  {
                logger.error("EXCEPTIION : " + jex);
            }
        }
        return access_token;
    }
}
