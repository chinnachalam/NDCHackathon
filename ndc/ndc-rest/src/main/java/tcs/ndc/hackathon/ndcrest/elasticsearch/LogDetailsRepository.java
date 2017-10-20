package tcs.ndc.hackathon.ndcrest.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogDetailsRepository extends ElasticsearchRepository<LogDetails, String> {
}