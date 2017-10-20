
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reservationId",
    "acknowledgementMedia",
    "taxiDetails",
    "pickUpDetails",
    "dropOffDetails",
    "paymentSummary"
})
public class TaxiReservation {

    @JsonProperty("reservationId")
    private String reservationId;
    @JsonProperty("acknowledgementMedia")
    private AcknowledgementMedia_ acknowledgementMedia;
    @JsonProperty("taxiDetails")
    private TaxiDetails taxiDetails;
    @JsonProperty("pickUpDetails")
    private PickUpDetails pickUpDetails;
    @JsonProperty("dropOffDetails")
    private DropOffDetails dropOffDetails;
    @JsonProperty("paymentSummary")
    private PaymentSummary_ paymentSummary;

    @JsonProperty("reservationId")
    public String getReservationId() {
        return reservationId;
    }

    @JsonProperty("reservationId")
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    @JsonProperty("acknowledgementMedia")
    public AcknowledgementMedia_ getAcknowledgementMedia() {
        return acknowledgementMedia;
    }

    @JsonProperty("acknowledgementMedia")
    public void setAcknowledgementMedia(AcknowledgementMedia_ acknowledgementMedia) {
        this.acknowledgementMedia = acknowledgementMedia;
    }

    @JsonProperty("taxiDetails")
    public TaxiDetails getTaxiDetails() {
        return taxiDetails;
    }

    @JsonProperty("taxiDetails")
    public void setTaxiDetails(TaxiDetails taxiDetails) {
        this.taxiDetails = taxiDetails;
    }

    @JsonProperty("pickUpDetails")
    public PickUpDetails getPickUpDetails() {
        return pickUpDetails;
    }

    @JsonProperty("pickUpDetails")
    public void setPickUpDetails(PickUpDetails pickUpDetails) {
        this.pickUpDetails = pickUpDetails;
    }

    @JsonProperty("dropOffDetails")
    public DropOffDetails getDropOffDetails() {
        return dropOffDetails;
    }

    @JsonProperty("dropOffDetails")
    public void setDropOffDetails(DropOffDetails dropOffDetails) {
        this.dropOffDetails = dropOffDetails;
    }

    @JsonProperty("paymentSummary")
    public PaymentSummary_ getPaymentSummary() {
        return paymentSummary;
    }

    @JsonProperty("paymentSummary")
    public void setPaymentSummary(PaymentSummary_ paymentSummary) {
        this.paymentSummary = paymentSummary;
    }

}
