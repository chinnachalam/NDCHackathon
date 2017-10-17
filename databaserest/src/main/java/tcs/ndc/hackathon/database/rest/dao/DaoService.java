package tcs.ndc.hackathon.database.rest.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DaoService {

    @Resource
    MongoTemplate mongoTemplate;

    public void createCollection(String id) {
        mongoTemplate.createCollection(id, null);
    }

    public void deleteCollection(String id) {
        mongoTemplate.dropCollection(id);

    }

    public Response save(Object objectToStore, String collectionName, String id) {
        Data data = new Data();
        data.setObjectToStore(objectToStore);
        data.setId(id);
        mongoTemplate.save(data, collectionName);

        Response response = new Response();
        response.setId(data.getId());

        return response;
    }

    public Object get(String id, String collectionName) {
        Data data = mongoTemplate
                .findOne(new BasicQuery("{_id: \"" + id + "\"}"), Data.class, collectionName);
        if (data == null) {
            return "";
        }
        return data.getObjectToStore();
    }
}
