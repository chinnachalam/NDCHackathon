
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "paymentSplit",
    "paymentModes",
    "totalAmount",
    "currencyCode",
    "contactInformation",
    "method"
})
public class Payment {

    @JsonProperty("paymentSplit")
    private Boolean paymentSplit;
    @JsonProperty("paymentModes")
    private List<String> paymentModes = null;
    @JsonProperty("totalAmount")
    private Integer totalAmount;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("contactInformation")
    private ContactInformation contactInformation;
    @JsonProperty("method")
    private Method method;

    @JsonProperty("paymentSplit")
    public Boolean getPaymentSplit() {
        return paymentSplit;
    }

    @JsonProperty("paymentSplit")
    public void setPaymentSplit(Boolean paymentSplit) {
        this.paymentSplit = paymentSplit;
    }

    @JsonProperty("paymentModes")
    public List<String> getPaymentModes() {
        return paymentModes;
    }

    @JsonProperty("paymentModes")
    public void setPaymentModes(List<String> paymentModes) {
        this.paymentModes = paymentModes;
    }

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

    @JsonProperty("contactInformation")
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    @JsonProperty("contactInformation")
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @JsonProperty("method")
    public Method getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(Method method) {
        this.method = method;
    }

}
