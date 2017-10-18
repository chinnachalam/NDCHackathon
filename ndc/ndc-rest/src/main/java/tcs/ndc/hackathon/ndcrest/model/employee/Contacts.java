
package tcs.ndc.hackathon.ndcrest.model.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "emailContact",
    "mobileContact",
    "addressContact"
})
public class Contacts {

    @JsonProperty("emailContact")
    private String emailContact;
    @JsonProperty("mobileContact")
    private MobileContact mobileContact;
    @JsonProperty("addressContact")
    private AddressContact addressContact;

    @JsonProperty("emailContact")
    public String getEmailContact() {
        return emailContact;
    }

    @JsonProperty("emailContact")
    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    @JsonProperty("mobileContact")
    public MobileContact getMobileContact() {
        return mobileContact;
    }

    @JsonProperty("mobileContact")
    public void setMobileContact(MobileContact mobileContact) {
        this.mobileContact = mobileContact;
    }

    @JsonProperty("addressContact")
    public AddressContact getAddressContact() {
        return addressContact;
    }

    @JsonProperty("addressContact")
    public void setAddressContact(AddressContact addressContact) {
        this.addressContact = addressContact;
    }

}
