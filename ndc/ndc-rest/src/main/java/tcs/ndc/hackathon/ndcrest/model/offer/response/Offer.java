package tcs.ndc.hackathon.ndcrest.model.offer.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.iata.ndc.schema.NamedAssoc;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class Offer extends ResourceSupport {

    private Price price;
    private Connection connection;
    private List<Service> services;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
