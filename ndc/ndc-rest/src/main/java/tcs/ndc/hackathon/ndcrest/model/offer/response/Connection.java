package tcs.ndc.hackathon.ndcrest.model.offer.response;

import java.util.List;

public class Connection {
    private List<Segment> segments;
    private String fareBaseCode;
    private String cabinCode;

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public String getFareBaseCode() {
        return fareBaseCode;
    }

    public void setFareBaseCode(String fareBaseCode) {
        this.fareBaseCode = fareBaseCode;
    }

    public String getCabinCode() {
        return cabinCode;
    }

    public void setCabinCode(String cabinCode) {
        this.cabinCode = cabinCode;
    }
}
