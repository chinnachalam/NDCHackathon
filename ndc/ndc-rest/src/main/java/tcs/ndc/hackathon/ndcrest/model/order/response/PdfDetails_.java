
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pdfLink",
    "description"
})
public class PdfDetails_ {

    @JsonProperty("pdfLink")
    private String pdfLink;
    @JsonProperty("description")
    private String description;

    @JsonProperty("pdfLink")
    public String getPdfLink() {
        return pdfLink;
    }

    @JsonProperty("pdfLink")
    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
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
