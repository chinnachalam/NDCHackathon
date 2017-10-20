package tcs.ndc.hackathon.ndcrest;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.client.RestTemplate;
import tcs.ndc.hackathon.ndccore.NDCConsumer;

import java.net.InetAddress;

@SpringBootApplication
public class NdcRestApplication {

    @Value("${ndc.service.url}")
    private String ndcServiceUrl;

    @Value("${ndc.authorization.key}")
    private String authorizationKey;

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Autowired
    private ElasticsearchOperations es;

    public static void main(String[] args) {
        SpringApplication.run(NdcRestApplication.class, args);
    }

    @Bean
    public NDCConsumer ndcConsumer (){
        NDCConsumer ndcConsumer = new NDCConsumer(ndcServiceUrl, authorizationKey);

        return ndcConsumer;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }

    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.settingsBuilder()
                .put("cluster.name", EsClusterName)
                .build();

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        return TransportClient.builder()
                .settings(esSettings)
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}
