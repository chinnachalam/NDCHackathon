package tcs.ndc.hackathon.ndcrest.model.miles;

import java.util.ArrayList;
import java.util.List;

public class Leg {

    private List<Segment> segments = new ArrayList<>();

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
