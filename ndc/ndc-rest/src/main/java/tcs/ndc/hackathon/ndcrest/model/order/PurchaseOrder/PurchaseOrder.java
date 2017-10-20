
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "transactionID",
    "cartId",
    "shoppingId",
    "payment",
    "connectionDetails"
})
public class PurchaseOrder {

    @JsonProperty("transactionID")
    private String transactionID;
    @JsonProperty("cartId")
    private String cartId;
    @JsonProperty("shoppingId")
    private String shoppingId;
    @JsonProperty("payment")
    private Payment payment;
    @JsonProperty("connectionDetails")
    private List<ConnectionDetail> connectionDetails = null;

    @JsonProperty("transactionID")
    public String getTransactionID() {
        return transactionID;
    }

    @JsonProperty("transactionID")
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    @JsonProperty("cartId")
    public String getCartId() {
        return cartId;
    }

    @JsonProperty("cartId")
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @JsonProperty("shoppingId")
    public String getShoppingId() {
        return shoppingId;
    }

    @JsonProperty("shoppingId")
    public void setShoppingId(String shoppingId) {
        this.shoppingId = shoppingId;
    }

    @JsonProperty("payment")
    public Payment getPayment() {
        return payment;
    }

    @JsonProperty("payment")
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @JsonProperty("connectionDetails")
    public List<ConnectionDetail> getConnectionDetails() {
        return connectionDetails;
    }

    @JsonProperty("connectionDetails")
    public void setConnectionDetails(List<ConnectionDetail> connectionDetails) {
        this.connectionDetails = connectionDetails;
    }

}
