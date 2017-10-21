package tcs.ndc.hackathon.ndcrest.model.miles.response;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private String code;
    private List<StatusTier> statusTiers = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<StatusTier> getStatusTiers() {
        return statusTiers;
    }

    public void setStatusTiers(List<StatusTier> statusTiers) {
        this.statusTiers = statusTiers;
    }
}
