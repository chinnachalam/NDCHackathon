package tcs.ndc.hackathon.ndcrest.model.offer.response;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class OfferResponse extends ResourceSupport{

    List<Offer> offers = new ArrayList<>();

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
