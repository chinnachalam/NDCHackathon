package tcs.ndc.hackathon.ndcrest.model.miles;

import java.util.ArrayList;
import java.util.List;

public class MilesRequest {
    private List<Flight> flights = new ArrayList<>();

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
