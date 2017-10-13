package tcs.ndc.hackathon.ndcrest.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class DatabaseRestConsumer {
    @Value("${databaserest.endpoint}")
    private String databaserestEndpoint;

    @Autowired RestTemplate restTemplate;

    public void save(Object object, String collectionName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json;charset=UTF-8");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity httpEntity = new HttpEntity(object, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(databaserestEndpoint + collectionName, HttpMethod.POST, httpEntity, Object.class, new HashMap<String, String>());
    }
}
