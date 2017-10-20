
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "connectionId",
    "segments"
})
public class ConnectionDetail {

    @JsonProperty("connectionId")
    private Integer connectionId;
    @JsonProperty("segments")
    private List<Segment> segments = null;

    @JsonProperty("connectionId")
    public Integer getConnectionId() {
        return connectionId;
    }

    @JsonProperty("connectionId")
    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    @JsonProperty("segments")
    public List<Segment> getSegments() {
        return segments;
    }

    @JsonProperty("segments")
    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

}
