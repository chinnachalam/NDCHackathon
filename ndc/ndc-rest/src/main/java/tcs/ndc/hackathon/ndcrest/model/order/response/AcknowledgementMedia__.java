
package tcs.ndc.hackathon.ndcrest.model.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pdfDetails",
    "qrDetails"
})
public class AcknowledgementMedia__ {

    @JsonProperty("pdfDetails")
    private PdfDetails__ pdfDetails;
    @JsonProperty("qrDetails")
    private QrDetails__ qrDetails;

    @JsonProperty("pdfDetails")
    public PdfDetails__ getPdfDetails() {
        return pdfDetails;
    }

    @JsonProperty("pdfDetails")
    public void setPdfDetails(PdfDetails__ pdfDetails) {
        this.pdfDetails = pdfDetails;
    }

    @JsonProperty("qrDetails")
    public QrDetails__ getQrDetails() {
        return qrDetails;
    }

    @JsonProperty("qrDetails")
    public void setQrDetails(QrDetails__ qrDetails) {
        this.qrDetails = qrDetails;
    }

}
