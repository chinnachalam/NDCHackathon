
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "registrationNumber",
    "model",
    "type"
})
public class TaxiDetails {

    @JsonProperty("registrationNumber")
    private String registrationNumber;
    @JsonProperty("model")
    private String model;
    @JsonProperty("type")
    private String type;

    @JsonProperty("registrationNumber")
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @JsonProperty("registrationNumber")
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

}
