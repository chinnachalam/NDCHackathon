
package tcs.ndc.hackathon.ndcrest.model.order.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "onwardConnection",
    "returnConnection"
})
public class ConnectionDetails {

    @JsonProperty("onwardConnection")
    private List<OnwardConnection> onwardConnection = null;
    @JsonProperty("returnConnection")
    private List<ReturnConnection> returnConnection = null;

    @JsonProperty("onwardConnection")
    public List<OnwardConnection> getOnwardConnection() {
        return onwardConnection;
    }

    @JsonProperty("onwardConnection")
    public void setOnwardConnection(List<OnwardConnection> onwardConnection) {
        this.onwardConnection = onwardConnection;
    }

    @JsonProperty("returnConnection")
    public List<ReturnConnection> getReturnConnection() {
        return returnConnection;
    }

    @JsonProperty("returnConnection")
    public void setReturnConnection(List<ReturnConnection> returnConnection) {
        this.returnConnection = returnConnection;
    }

}
