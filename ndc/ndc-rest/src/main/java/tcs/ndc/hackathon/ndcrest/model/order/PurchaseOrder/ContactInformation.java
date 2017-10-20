
package tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "addressContact",
    "EmailAddress"
})
public class ContactInformation {

    @JsonProperty("addressContact")
    private AddressContact addressContact;
    @JsonProperty("EmailAddress")
    private String emailAddress;

    @JsonProperty("addressContact")
    public AddressContact getAddressContact() {
        return addressContact;
    }

    @JsonProperty("addressContact")
    public void setAddressContact(AddressContact addressContact) {
        this.addressContact = addressContact;
    }

    @JsonProperty("EmailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty("EmailAddress")
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
