
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bankName",
    "accountDetails",
    "totalPrice",
    "currencyCode",
    "priceBreadown"
})
public class PaymentSummary {

    @JsonProperty("bankName")
    private String bankName;
    @JsonProperty("accountDetails")
    private AccountDetails accountDetails;
    @JsonProperty("totalPrice")
    private Double totalPrice;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("priceBreadown")
    private PriceBreadown priceBreadown;

    @JsonProperty("bankName")
    public String getBankName() {
        return bankName;
    }

    @JsonProperty("bankName")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @JsonProperty("accountDetails")
    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    @JsonProperty("accountDetails")
    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    @JsonProperty("totalPrice")
    public Double getTotalPrice() {
        return totalPrice;
    }

    @JsonProperty("totalPrice")
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("priceBreadown")
    public PriceBreadown getPriceBreadown() {
        return priceBreadown;
    }

    @JsonProperty("priceBreadown")
    public void setPriceBreadown(PriceBreadown priceBreadown) {
        this.priceBreadown = priceBreadown;
    }

}
