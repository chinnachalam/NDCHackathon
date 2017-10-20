
package tcs.ndc.hackathon.ndcrest.model.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cardCode",
    "cardType",
    "cardNumber",
    "seriesCode",
    "cardHolderName",
    "effectiveExpiryDate",
    "expirationDate"
})
public class CreditCard {

    @JsonProperty("cardCode")
    private String cardCode;
    @JsonProperty("cardType")
    private String cardType;
    @JsonProperty("cardNumber")
    private Long cardNumber;
    @JsonProperty("seriesCode")
    private Integer seriesCode;
    @JsonProperty("cardHolderName")
    private String cardHolderName;
    @JsonProperty("effectiveExpiryDate")
    private String effectiveExpiryDate;
    @JsonProperty("expirationDate")
    private String expirationDate;

    @JsonProperty("cardCode")
    public String getCardCode() {
        return cardCode;
    }

    @JsonProperty("cardCode")
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    @JsonProperty("cardType")
    public String getCardType() {
        return cardType;
    }

    @JsonProperty("cardType")
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @JsonProperty("cardNumber")
    public Long getCardNumber() {
        return cardNumber;
    }

    @JsonProperty("cardNumber")
    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    @JsonProperty("seriesCode")
    public Integer getSeriesCode() {
        return seriesCode;
    }

    @JsonProperty("seriesCode")
    public void setSeriesCode(Integer seriesCode) {
        this.seriesCode = seriesCode;
    }

    @JsonProperty("cardHolderName")
    public String getCardHolderName() {
        return cardHolderName;
    }

    @JsonProperty("cardHolderName")
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @JsonProperty("effectiveExpiryDate")
    public String getEffectiveExpiryDate() {
        return effectiveExpiryDate;
    }

    @JsonProperty("effectiveExpiryDate")
    public void setEffectiveExpiryDate(String effectiveExpiryDate) {
        this.effectiveExpiryDate = effectiveExpiryDate;
    }

    @JsonProperty("expirationDate")
    public String getExpirationDate() {
        return expirationDate;
    }

    @JsonProperty("expirationDate")
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
