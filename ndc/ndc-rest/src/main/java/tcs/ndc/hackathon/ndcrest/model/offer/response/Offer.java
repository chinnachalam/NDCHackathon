package tcs.ndc.hackathon.ndcrest.model.offer.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.iata.ndc.schema.NamedAssoc;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
