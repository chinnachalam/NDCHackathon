
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "passengerId",
    "orderItems"
})
public class PassengerDetail {

    @JsonProperty("passengerId")
    private String passengerId;
    @JsonProperty("orderItems")
    private List<OrderItem> orderItems = null;

    @JsonProperty("passengerId")
    public String getPassengerId() {
        return passengerId;
    }

    @JsonProperty("passengerId")
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    @JsonProperty("orderItems")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @JsonProperty("orderItems")
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
