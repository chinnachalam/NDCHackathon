
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pnr",
    "orderId",
    "acknowledgementMedia",
    "paymentSummary",
    "connectionDetails"
})
public class FlightReservation {

    @JsonProperty("pnr")
    private String pnr;
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("acknowledgementMedia")
    private AcknowledgementMedia acknowledgementMedia;
    @JsonProperty("paymentSummary")
    private PaymentSummary paymentSummary;
    @JsonProperty("connectionDetails")
    private ConnectionDetails connectionDetails;

    @JsonProperty("pnr")
    public String getPnr() {
        return pnr;
    }

    @JsonProperty("pnr")
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    @JsonProperty("orderId")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("acknowledgementMedia")
    public AcknowledgementMedia getAcknowledgementMedia() {
        return acknowledgementMedia;
    }

    @JsonProperty("acknowledgementMedia")
    public void setAcknowledgementMedia(AcknowledgementMedia acknowledgementMedia) {
        this.acknowledgementMedia = acknowledgementMedia;
    }

    @JsonProperty("paymentSummary")
    public PaymentSummary getPaymentSummary() {
        return paymentSummary;
    }

    @JsonProperty("paymentSummary")
    public void setPaymentSummary(PaymentSummary paymentSummary) {
        this.paymentSummary = paymentSummary;
    }

    @JsonProperty("connectionDetails")
    public ConnectionDetails getConnectionDetails() {
        return connectionDetails;
    }

    @JsonProperty("connectionDetails")
    public void setConnectionDetails(ConnectionDetails connectionDetails) {
        this.connectionDetails = connectionDetails;
    }

}
