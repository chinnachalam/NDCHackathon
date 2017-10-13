package tcs.ndc.hackathon.database.rest.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Data {

    @Id
    private String id;

    private Object objectToStore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getObjectToStore() {
        return objectToStore;
    }

    public void setObjectToStore(Object objectToStore) {
        this.objectToStore = objectToStore;
    }
}
