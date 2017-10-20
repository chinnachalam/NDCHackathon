
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loyaltyAccount",
    "totalPoints"
})
public class TcsLoyaltyPoints {

    @JsonProperty("loyaltyAccount")
    private String loyaltyAccount;
    @JsonProperty("totalPoints")
    private Integer totalPoints;

    @JsonProperty("loyaltyAccount")
    public String getLoyaltyAccount() {
        return loyaltyAccount;
    }

    @JsonProperty("loyaltyAccount")
    public void setLoyaltyAccount(String loyaltyAccount) {
        this.loyaltyAccount = loyaltyAccount;
    }

    @JsonProperty("totalPoints")
    public Integer getTotalPoints() {
        return totalPoints;
    }

    @JsonProperty("totalPoints")
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

}
