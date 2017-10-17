package tcs.ndc.hackathon.ndcrest.model.shop;

import com.fasterxml.jackson.annotation.JsonInclude;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Offer;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShopDetails {
    Map<String, String> addedDetails = new HashMap<>();

    List<Offer> offers = new ArrayList<>();

    List<Service> services = new ArrayList<>();

    public Map<String, String> getAddedDetails() {
        return addedDetails;
    }

    public void setAddedDetails(Map<String, String> addedDetails) {
        this.addedDetails = addedDetails;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
