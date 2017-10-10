package tcs.ndc.hackathon.ndcrest.model.offer.request;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Offer {

    private Map<Passenger, Integer> passengers = new EnumMap<>(Passenger.class);

    private List<Connection> connections = new ArrayList<>();

    private Cabin cabin;

    private String airlinePreference;

    private String currency;

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public Map<Passenger, Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(Map<Passenger, Integer> passengers) {
        this.passengers = passengers;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public String getAirlinePreference() {
        return airlinePreference;
    }

    public void setAirlinePreference(String airlinePreference) {
        this.airlinePreference = airlinePreference;
    }
}
