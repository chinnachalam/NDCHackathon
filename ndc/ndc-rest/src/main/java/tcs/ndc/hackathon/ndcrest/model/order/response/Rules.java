
package tcs.ndc.hackathon.ndcrest.model.order.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "link",
    "ruleDescription"
})
public class Rules {

    @JsonProperty("link")
    private String link;
    @JsonProperty("ruleDescription")
    private List<String> ruleDescription = null;

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("ruleDescription")
    public List<String> getRuleDescription() {
        return ruleDescription;
    }

    @JsonProperty("ruleDescription")
    public void setRuleDescription(List<String> ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

}
