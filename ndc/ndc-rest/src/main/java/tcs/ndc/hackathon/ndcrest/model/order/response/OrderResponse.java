
package tcs.ndc.hackathon.ndcrest.model.order.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "transactionId",
    "totalPaymentSummary",
    "flightReservation",
    "taxiReservation",
    "hotelReservation"
})
public class OrderResponse {

    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("totalPaymentSummary")
    private TotalPaymentSummary totalPaymentSummary;
    @JsonProperty("flightReservation")
    private FlightReservation flightReservation;
    @JsonProperty("taxiReservation")
    private List<TaxiReservation> taxiReservation = null;
    @JsonProperty("hotelReservation")
    private List<HotelReservation> hotelReservation = null;

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("totalPaymentSummary")
    public TotalPaymentSummary getTotalPaymentSummary() {
        return totalPaymentSummary;
    }

    @JsonProperty("totalPaymentSummary")
    public void setTotalPaymentSummary(TotalPaymentSummary totalPaymentSummary) {
        this.totalPaymentSummary = totalPaymentSummary;
    }

    @JsonProperty("flightReservation")
    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    @JsonProperty("flightReservation")
    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }

    @JsonProperty("taxiReservation")
    public List<TaxiReservation> getTaxiReservation() {
        return taxiReservation;
    }

    @JsonProperty("taxiReservation")
    public void setTaxiReservation(List<TaxiReservation> taxiReservation) {
        this.taxiReservation = taxiReservation;
    }

    @JsonProperty("hotelReservation")
    public List<HotelReservation> getHotelReservation() {
        return hotelReservation;
    }

    @JsonProperty("hotelReservation")
    public void setHotelReservation(List<HotelReservation> hotelReservation) {
        this.hotelReservation = hotelReservation;
    }

}
