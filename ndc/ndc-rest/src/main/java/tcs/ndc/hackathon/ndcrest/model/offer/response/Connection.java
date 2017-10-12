package tcs.ndc.hackathon.ndcrest.model.offer.response;

import java.util.List;

public class Connection {
    private List<Segment> segments;

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
