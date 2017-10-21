package tcs.ndc.hackathon.ndcrest.model.miles.response;

import java.util.ArrayList;
import java.util.List;

public class MilesResponse {
    private String id;

    private List<Flight> flights = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
