
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "offerID",
    "offerItems"
})
public class OrderItem {

    @JsonProperty("offerID")
    private String offerID;
    @JsonProperty("offerItems")
    private List<String> offerItems = null;

    @JsonProperty("offerID")
    public String getOfferID() {
        return offerID;
    }

    @JsonProperty("offerID")
    public void setOfferID(String offerID) {
        this.offerID = offerID;
    }

    @JsonProperty("offerItems")
    public List<String> getOfferItems() {
        return offerItems;
    }

    @JsonProperty("offerItems")
    public void setOfferItems(List<String> offerItems) {
        this.offerItems = offerItems;
    }

}
