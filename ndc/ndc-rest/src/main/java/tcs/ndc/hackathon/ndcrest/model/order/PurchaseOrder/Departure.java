
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "airportCode",
    "date",
    "time"
})
public class Departure {

    @JsonProperty("airportCode")
    private String airportCode;
    @JsonProperty("date")
    private String date;
    @JsonProperty("time")
    private String time;

    @JsonProperty("airportCode")
    public String getAirportCode() {
        return airportCode;
    }

    @JsonProperty("airportCode")
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

}
