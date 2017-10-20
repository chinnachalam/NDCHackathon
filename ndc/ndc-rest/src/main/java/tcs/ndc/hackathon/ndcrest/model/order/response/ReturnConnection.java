
package tcs.ndc.hackathon.ndcrest.model.order.response;

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
    "passengers"
})
public class ReturnConnection {

    @JsonProperty("departure")
    private Departure_ departure;
    @JsonProperty("arrival")
    private Arrival_ arrival;
    @JsonProperty("marketingAirline")
    private String marketingAirline;
    @JsonProperty("operatingAirline")
    private String operatingAirline;
    @JsonProperty("passengers")
    private List<Passenger> passengers = null;

    @JsonProperty("departure")
    public Departure_ getDeparture() {
        return departure;
    }

    @JsonProperty("departure")
    public void setDeparture(Departure_ departure) {
        this.departure = departure;
    }

    @JsonProperty("arrival")
    public Arrival_ getArrival() {
        return arrival;
    }

    @JsonProperty("arrival")
    public void setArrival(Arrival_ arrival) {
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

    @JsonProperty("passengers")
    public List<Passenger> getPassengers() {
        return passengers;
    }

    @JsonProperty("passengers")
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

}
