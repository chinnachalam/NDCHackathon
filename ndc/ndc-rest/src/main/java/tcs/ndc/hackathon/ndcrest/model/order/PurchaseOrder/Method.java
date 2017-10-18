
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "creditCard",
    "tcsLoyaltyPoints"
})
public class Method {

    @JsonProperty("creditCard")
    private CreditCard creditCard;
    @JsonProperty("tcsLoyaltyPoints")
    private TcsLoyaltyPoints tcsLoyaltyPoints;

    @JsonProperty("creditCard")
    public CreditCard getCreditCard() {
        return creditCard;
    }

    @JsonProperty("creditCard")
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @JsonProperty("tcsLoyaltyPoints")
    public TcsLoyaltyPoints getTcsLoyaltyPoints() {
        return tcsLoyaltyPoints;
    }

    @JsonProperty("tcsLoyaltyPoints")
    public void setTcsLoyaltyPoints(TcsLoyaltyPoints tcsLoyaltyPoints) {
        this.tcsLoyaltyPoints = tcsLoyaltyPoints;
    }

}
