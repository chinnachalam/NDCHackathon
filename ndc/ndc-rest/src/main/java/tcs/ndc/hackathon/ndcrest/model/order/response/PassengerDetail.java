
package tcs.ndc.hackathon.ndcrest.model.order.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "passengerId",
    "passengerType",
    "ticketNumber",
    "orderItems"
})
public class PassengerDetail {

    @JsonProperty("passengerId")
    private String passengerId;
    @JsonProperty("passengerType")
    private String passengerType;
    @JsonProperty("ticketNumber")
    private String ticketNumber;
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

    @JsonProperty("passengerType")
    public String getPassengerType() {
        return passengerType;
    }

    @JsonProperty("passengerType")
    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    @JsonProperty("ticketNumber")
    public String getTicketNumber() {
        return ticketNumber;
    }

    @JsonProperty("ticketNumber")
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
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
