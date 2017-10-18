
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "imageLink",
    "description"
})
public class QrDetails_ {

    @JsonProperty("imageLink")
    private String imageLink;
    @JsonProperty("description")
    private String description;

    @JsonProperty("imageLink")
    public String getImageLink() {
        return imageLink;
    }

    @JsonProperty("imageLink")
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

}
