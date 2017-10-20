
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalAmount",
    "currencyCode"
})
public class AmountDetails {

    @JsonProperty("totalAmount")
    private Integer totalAmount;
    @JsonProperty("currencyCode")
    private String currencyCode;

    @JsonProperty("totalAmount")
    public Integer getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("totalAmount")
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
