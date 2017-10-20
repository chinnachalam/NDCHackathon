package tcs.ndc.hackathon.ndcrest.elasticsearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.UUID;

@Document(indexName = "logs", type = "loginfo")
public class LogDetails {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date = new Date();

    private Object logInfo;

    public LogDetails() {
    }

    public LogDetails(Object logInfo) {
        this.logInfo = logInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(Object logInfo) {
        this.logInfo = logInfo;
    }
}