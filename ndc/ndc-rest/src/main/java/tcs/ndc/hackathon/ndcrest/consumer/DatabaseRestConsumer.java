package tcs.ndc.hackathon.ndcrest.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Offer;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Component
public class DatabaseRestConsumer {
    @Value("${databaserest.endpoint}")
    private String databaserestEndpoint;

    @Autowired RestTemplate restTemplate;

    public String save(Object object, String collectionName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json;charset=UTF-8");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity httpEntity = new HttpEntity(object, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(databaserestEndpoint + collectionName, HttpMethod.POST, httpEntity, Object.class, new HashMap<String, String>());

        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) responseEntity.getBody();
        return linkedHashMap.get("id");
    }

    public String saveWithId(Object object, String collectionName, String id) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json;charset=UTF-8");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity httpEntity = new HttpEntity(object, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(databaserestEndpoint + collectionName + "/" + id, HttpMethod.POST, httpEntity, Object.class, new HashMap<String, String>());

        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) responseEntity.getBody();
        return linkedHashMap.get("id");
    }

    public <T> ResponseEntity<T>  get(String collectionName, String id, Class<T> type)  {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json;charset=UTF-8");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<T> response = restTemplate.exchange(databaserestEndpoint + collectionName + "/"+ id, HttpMethod.GET, entity, type);

        return response;
    }
}
