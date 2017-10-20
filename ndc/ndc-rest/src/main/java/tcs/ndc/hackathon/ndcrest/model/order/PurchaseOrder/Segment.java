
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "departure",
    "arrival",
    "marketingAirline",
    "operatingAirline",
    "passengerDetails"
})
public class Segment {

    @JsonProperty("departure")
    private Departure departure;
    @JsonProperty("arrival")
    private Arrival arrival;
    @JsonProperty("marketingAirline")
    private String marketingAirline;
    @JsonProperty("operatingAirline")
    private String operatingAirline;
    @JsonProperty("passengerDetails")
    private List<PassengerDetail> passengerDetails = null;

    @JsonProperty("departure")
    public Departure getDeparture() {
        return departure;
    }

    @JsonProperty("departure")
    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    @JsonProperty("arrival")
    public Arrival getArrival() {
        return arrival;
    }

    @JsonProperty("arrival")
    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    @JsonProperty("marketingAirline")
    public String getMarketingAirline() {
        return marketingAirline;
    }

    @JsonProperty("marketingAirline")
    public void setMarketingAirline(String marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    @JsonProperty("operatingAirline")
    public String getOperatingAirline() {
        return operatingAirline;
    }

    @JsonProperty("operatingAirline")
    public void setOperatingAirline(String operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    @JsonProperty("passengerDetails")
    public List<PassengerDetail> getPassengerDetails() {
        return passengerDetails;
    }

    @JsonProperty("passengerDetails")
    public void setPassengerDetails(List<PassengerDetail> passengerDetails) {
        this.passengerDetails = passengerDetails;
    }

}
