package tcs.ndc.hackathon.ndcrest.model.miles.response;

import java.util.ArrayList;
import java.util.List;

public class StatusTier {
    private String code;
    List<MileageEarning> mileageEarnings = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MileageEarning> getMileageEarnings() {
        return mileageEarnings;
    }

    public void setMileageEarnings(List<MileageEarning> mileageEarnings) {
        this.mileageEarnings = mileageEarnings;
    }
}
