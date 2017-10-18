
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cardNumber",
    "cardType",
    "cardHolderName",
    "cvv",
    "expiryDate",
    "issueDate",
    "amountDetails"
})
public class CreditCard {

    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("cardType")
    private String cardType;
    @JsonProperty("cardHolderName")
    private String cardHolderName;
    @JsonProperty("cvv")
    private String cvv;
    @JsonProperty("expiryDate")
    private String expiryDate;
    @JsonProperty("issueDate")
    private String issueDate;
    @JsonProperty("amountDetails")
    private AmountDetails amountDetails;

    @JsonProperty("cardNumber")
    public String getCardNumber() {
        return cardNumber;
    }

    @JsonProperty("cardNumber")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @JsonProperty("cardType")
    public String getCardType() {
        return cardType;
    }

    @JsonProperty("cardType")
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @JsonProperty("cardHolderName")
    public String getCardHolderName() {
        return cardHolderName;
    }

    @JsonProperty("cardHolderName")
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @JsonProperty("cvv")
    public String getCvv() {
        return cvv;
    }

    @JsonProperty("cvv")
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @JsonProperty("expiryDate")
    public String getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("expiryDate")
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @JsonProperty("issueDate")
    public String getIssueDate() {
        return issueDate;
    }

    @JsonProperty("issueDate")
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @JsonProperty("amountDetails")
    public AmountDetails getAmountDetails() {
        return amountDetails;
    }

    @JsonProperty("amountDetails")
    public void setAmountDetails(AmountDetails amountDetails) {
        this.amountDetails = amountDetails;
    }

}
