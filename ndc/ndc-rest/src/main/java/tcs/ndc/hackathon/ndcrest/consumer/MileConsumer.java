package tcs.ndc.hackathon.ndcrest.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tcs.ndc.hackathon.ndcrest.model.miles.MilesRequest;
import tcs.ndc.hackathon.ndcrest.model.miles.response.MilesResponse;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Component
public class MileConsumer {

    @Autowired
    RestTemplate restTemplate;


    public MilesResponse getMiles(MilesRequest milesRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/hal+json");
        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("X-Api-Version", "v3.0");
        HttpEntity httpEntity = new HttpEntity(milesRequest, headers);

        ResponseEntity<MilesResponse> responseEntity = restTemplate.exchange("https://hackathon-milefy.30k.com/calculate?apiKey=c18a07be8b874a963aac&traveler=2833569a-37da-4803-86f9-23d2ebc7a708", HttpMethod.POST, httpEntity, MilesResponse.class, new HashMap<String, String>());

        MilesResponse response = responseEntity.getBody();

        return response;
    }
}
