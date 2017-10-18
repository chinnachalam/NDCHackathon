
package tcs.ndc.hackathon.ndcrest.model.employee;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "creditCard"
})
public class PaymentInformation {

    @JsonProperty("creditCard")
    private List<CreditCard> creditCard = null;

    @JsonProperty("creditCard")
    public List<CreditCard> getCreditCard() {
        return creditCard;
    }

    @JsonProperty("creditCard")
    public void setCreditCard(List<CreditCard> creditCard) {
        this.creditCard = creditCard;
    }

}
