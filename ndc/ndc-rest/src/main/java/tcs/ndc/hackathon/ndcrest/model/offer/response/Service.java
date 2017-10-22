package tcs.ndc.hackathon.ndcrest.model.offer.response;

import org.springframework.hateoas.ResourceSupport;

public class Service extends ResourceSupport implements Comparable<Service> {
    private String serviceId;
    private String code;
    private String description;
    private Integer price;
    private String name;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int compareTo(Service service) {
        int comparePrice = ((Service) service).getPrice();
        return this.price - comparePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
