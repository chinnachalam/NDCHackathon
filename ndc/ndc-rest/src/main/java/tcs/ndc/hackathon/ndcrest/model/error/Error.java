package tcs.ndc.hackathon.ndcrest.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(
        {
                "errorCode",
                "errorDescription",
                "stackTrace"
        }
)
public class Error{

    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("errorDescription")
    private String errorDescription;
    @JsonProperty("stackTrace")
    private String stackTrace;

    @JsonProperty("errorCode")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("errorDescription")
    public String getErrorDescription() {
        return errorDescription;
    }

    @JsonProperty("errorDescription")
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @JsonProperty("stackTrace")
    public String getStackTrace() {
        return stackTrace;
    }

    @JsonProperty("stackTrace")
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
