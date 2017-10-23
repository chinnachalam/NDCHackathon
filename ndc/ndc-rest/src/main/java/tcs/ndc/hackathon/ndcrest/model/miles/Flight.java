package tcs.ndc.hackathon.ndcrest.model.miles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Flight {
    private String id = UUID.randomUUID().toString();
    List<Leg> legs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
}
