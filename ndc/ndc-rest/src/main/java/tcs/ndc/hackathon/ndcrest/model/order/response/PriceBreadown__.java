
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "basePrice",
    "serviceTax",
    "VAT",
    "surcharge"
})
public class PriceBreadown__ {

    @JsonProperty("basePrice")
    private Integer basePrice;
    @JsonProperty("serviceTax")
    private Integer serviceTax;
    @JsonProperty("VAT")
    private Integer vAT;
    @JsonProperty("surcharge")
    private Integer surcharge;

    @JsonProperty("basePrice")
    public Integer getBasePrice() {
        return basePrice;
    }

    @JsonProperty("basePrice")
    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    @JsonProperty("serviceTax")
    public Integer getServiceTax() {
        return serviceTax;
    }

    @JsonProperty("serviceTax")
    public void setServiceTax(Integer serviceTax) {
        this.serviceTax = serviceTax;
    }

    @JsonProperty("VAT")
    public Integer getVAT() {
        return vAT;
    }

    @JsonProperty("VAT")
    public void setVAT(Integer vAT) {
        this.vAT = vAT;
    }

    @JsonProperty("surcharge")
    public Integer getSurcharge() {
        return surcharge;
    }

    @JsonProperty("surcharge")
    public void setSurcharge(Integer surcharge) {
        this.surcharge = surcharge;
    }

}
