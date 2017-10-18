
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reservationId",
    "acknowledgementMedia",
    "roomDetails",
    "rules",
    "paymentSummary"
})
public class HotelReservation {

    @JsonProperty("reservationId")
    private String reservationId;
    @JsonProperty("acknowledgementMedia")
    private AcknowledgementMedia__ acknowledgementMedia;
    @JsonProperty("roomDetails")
    private RoomDetails roomDetails;
    @JsonProperty("rules")
    private Rules rules;
    @JsonProperty("paymentSummary")
    private PaymentSummary__ paymentSummary;

    @JsonProperty("reservationId")
    public String getReservationId() {
        return reservationId;
    }

    @JsonProperty("reservationId")
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    @JsonProperty("acknowledgementMedia")
    public AcknowledgementMedia__ getAcknowledgementMedia() {
        return acknowledgementMedia;
    }

    @JsonProperty("acknowledgementMedia")
    public void setAcknowledgementMedia(AcknowledgementMedia__ acknowledgementMedia) {
        this.acknowledgementMedia = acknowledgementMedia;
    }

    @JsonProperty("roomDetails")
    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    @JsonProperty("roomDetails")
    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    @JsonProperty("rules")
    public Rules getRules() {
        return rules;
    }

    @JsonProperty("rules")
    public void setRules(Rules rules) {
        this.rules = rules;
    }

    @JsonProperty("paymentSummary")
    public PaymentSummary__ getPaymentSummary() {
        return paymentSummary;
    }

    @JsonProperty("paymentSummary")
    public void setPaymentSummary(PaymentSummary__ paymentSummary) {
        this.paymentSummary = paymentSummary;
    }

}
