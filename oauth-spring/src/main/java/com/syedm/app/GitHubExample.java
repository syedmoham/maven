package com.syedm.app;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GitHubExample {
    public static void main(String[] args) {
        SpringApplication.run(GitHubExample.class, args);
    }
    @Bean
    public RestTemplate restTemplate()  {
        return new RestTemplate();
    }
}

@RestController
class GitHubAsyncComponent {
    private static Logger logger = LoggerFactory.getLogger(GitHubExample.class);
    private final RestTemplate restTemplate;
    private final GitHubOAuthClient gitHubOAuthClient;
    private String accessToken;

    @Value("${github.client-id}")
    private String githubClientId;

    @Value("${github.client-secret}")
    private String githubClientSecret;

    public GitHubAsyncComponent(RestTemplate restTemplate, GitHubOAuthClient gitHubOAuthClient)  {
        this.restTemplate = restTemplate;
        this.gitHubOAuthClient = gitHubOAuthClient;
    }

    @GetMapping("/github")
    public String getGitHubUserInfo(String code) {
        // GitHub API endpoint for user information
        String apiUrl = "https://api.github.com/user";

        accessToken = gitHubOAuthClient.getAccessToken(githubClientId, githubClientSecret, code, "http://localhost:8080/login/oauth2/code/github");

        // Set the Authorization header with the access token
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        // Make a request to the GitHub API
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // Parse the JSON response
        JsonObject jsonResponse = JsonParser.parseString(responseEntity.getBody()).getAsJsonObject();

        // Extract user information
        String userId = jsonResponse.get("id").getAsString();
        String login = jsonResponse.get("login").getAsString();
        String name = jsonResponse.get("name").getAsString();

        return "GitHub User ID: " + userId + ", Login: " + login + ", Name: " + name;
    }
}