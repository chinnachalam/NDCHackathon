
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pdfDetails",
    "qrDetails"
})
public class AcknowledgementMedia_ {

    @JsonProperty("pdfDetails")
    private PdfDetails_ pdfDetails;
    @JsonProperty("qrDetails")
    private QrDetails_ qrDetails;

    @JsonProperty("pdfDetails")
    public PdfDetails_ getPdfDetails() {
        return pdfDetails;
    }

    @JsonProperty("pdfDetails")
    public void setPdfDetails(PdfDetails_ pdfDetails) {
        this.pdfDetails = pdfDetails;
    }

    @JsonProperty("qrDetails")
    public QrDetails_ getQrDetails() {
        return qrDetails;
    }

    @JsonProperty("qrDetails")
    public void setQrDetails(QrDetails_ qrDetails) {
        this.qrDetails = qrDetails;
    }

}
