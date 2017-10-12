package tcs.ndc.hackathon.ndcrest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import tcs.ndc.hackathon.ndccore.NDCConsumer;

import java.util.Arrays;

@SpringBootApplication
public class NdcRestApplication {

    @Value("${ndc.service.url}")
    private String ndcServiceUrl;

    @Value("${ndc.authorization.key}")
    private String authorizationKey;

    public static void main(String[] args) {
        SpringApplication.run(NdcRestApplication.class, args);
    }


    @Bean
    public NDCConsumer ndcConsumer (){
        NDCConsumer ndcConsumer = new NDCConsumer(ndcServiceUrl, authorizationKey);

        return ndcConsumer;
    }
}
