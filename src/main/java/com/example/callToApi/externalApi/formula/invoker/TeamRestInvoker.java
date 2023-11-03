package com.example.callToApi.externalApi.formula.invoker;

import com.example.callToApi.entity.Team;
import com.example.callToApi.externalApi.formula.response.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamRestInvoker {

    private final RestTemplate restTemplate;
    private static final String F1_API_URL = "https://v1.formula-1.api-sports.io/teams?search=";

    public List<Team> getTeams(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "8010659d3db4840e870bc96299d1341d");
        headers.add("X-RapidAPI-Host", "api-formula-1.p.rapidapi.com");

        HttpEntity<Team> entity = new HttpEntity<>(headers);

        ResponseEntity<TeamResponse> t_response = restTemplate.exchange(
                F1_API_URL + name,
                HttpMethod.GET,
                entity,
                TeamResponse.class
        );

        if (t_response.getBody() == null) {
            return new ArrayList<>();
        }
        return t_response.getBody().getResponse();
    }
}