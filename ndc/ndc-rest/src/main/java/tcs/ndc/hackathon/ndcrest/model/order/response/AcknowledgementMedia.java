
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pdfDetails",
    "qrDetails"
})
public class AcknowledgementMedia {

    @JsonProperty("pdfDetails")
    private PdfDetails pdfDetails;
    @JsonProperty("qrDetails")
    private QrDetails qrDetails;

    @JsonProperty("pdfDetails")
    public PdfDetails getPdfDetails() {
        return pdfDetails;
    }

    @JsonProperty("pdfDetails")
    public void setPdfDetails(PdfDetails pdfDetails) {
        this.pdfDetails = pdfDetails;
    }

    @JsonProperty("qrDetails")
    public QrDetails getQrDetails() {
        return qrDetails;
    }

    @JsonProperty("qrDetails")
    public void setQrDetails(QrDetails qrDetails) {
        this.qrDetails = qrDetails;
    }

}
