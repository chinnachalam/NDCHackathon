package tcs.ndc.hackathon.ndcrest.mapper.offer;

import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tcs.ndc.hackathon.ndcrest.consumer.DatabaseRestConsumer;
import tcs.ndc.hackathon.ndcrest.model.offer.response.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class OfferResponseMapper {

    @Autowired DatabaseRestConsumer databaseRestConsumer;

    public List<Offer> map(AirShoppingRS response) {
        List<Offer> offers = new ArrayList<>();

        try {
            AirShoppingRS.OffersGroup offersGroup = response.getOffersGroup();
            List<AirShoppingRS.OffersGroup.AirlineOffers> airlineOffersList = offersGroup.getAirlineOffers();

            for (AirShoppingRS.OffersGroup.AirlineOffers airlineOffers : airlineOffersList) {
                for (AirShoppingRS.OffersGroup.AirlineOffers.AirlineOffer airlineOffer : airlineOffers.getAirlineOffer()) {
                    PricedOffer pricedOffer = airlineOffer.getPricedOffer();

                    List<PricedFlightOfferAssocType> pricedFlightOfferAssocTypeList = pricedOffer.getAssociations();
                    for (PricedFlightOfferAssocType pricedFlightOfferAssocType : pricedFlightOfferAssocTypeList) {
                        Connection connection = new Connection();
                        List<Segment> segments = new ArrayList<>();
                        for (Object flightReference : pricedFlightOfferAssocType.getApplicableFlight().getFlightReferences().getValue()) {
                            DataListType.Flight flight = (DataListType.Flight) flightReference;
                            for (Object segmentReference : flight.getSegmentReferences().getValue()) {
                                ListOfFlightSegmentType coreSegment = (ListOfFlightSegmentType) segmentReference;
                                Segment segment = mapSegment(coreSegment);
                                segments.add(segment);
                            }
                        }
                        connection.setSegments(segments);

                        PriceClassType priceClassType = (PriceClassType) pricedFlightOfferAssocType.getPriceClass().getPriceClassReference();
                        connection.setFareBaseCode(priceClassType.getFareBasisCode().getCode());
                        connection.setCabinCode(priceClassType.getCode());

                        List<PricedFlightOfferType.OfferPrice> offerPriceList = pricedOffer.getOfferPrice();
                        for (PricedFlightOfferType.OfferPrice offerPrice : offerPriceList) {
                            Offer offer = new Offer();
                            offer.setConnection(connection);
                            offer.setPrice(mapPrice(offerPrice.getRequestedDate().getPriceDetail()));
                            for (PricedFlightOfferAssocType pricedFlightOfferAssoc : offerPrice.getRequestedDate().getAssociations()) {
                                offer.setServices(mapServices(pricedFlightOfferAssoc.getAssociatedService()));
                            }
                            databaseRestConsumer.save(offer, "offer");
                            offers.add(offer);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return offers;
    }

    private Segment mapSegment(ListOfFlightSegmentType coreSegment) {
        Segment segment = new Segment();
        segment.setArrival(mapArrival(coreSegment.getArrival()));
        segment.setDeparture(mapDeparture(coreSegment.getDeparture()));
        segment.setMarketingAirline(coreSegment.getMarketingCarrier().getAirlineID().getValue() + coreSegment.getMarketingCarrier().getFlightNumber().getValue());
        segment.setOperatingAirline(coreSegment.getOperatingCarrier().getAirlineID().getValue() + coreSegment.getOperatingCarrier().getFlightNumber().getValue());
        return segment;
    }

    private Arrival mapArrival(FlightArrivalType coreArrival) {
        Arrival arrival = new Arrival();
        arrival.setAirportCode(coreArrival.getAirportCode().getValue());
        arrival.setAirportName(coreArrival.getAirportName());
        arrival.setTime(coreArrival.getTime());
        arrival.setDate(coreArrival.getDate().toString());

        return arrival;
    }

    private tcs.ndc.hackathon.ndcrest.model.offer.response.Departure mapDeparture(org.iata.ndc.schema.Departure coreDeparture) {
        tcs.ndc.hackathon.ndcrest.model.offer.response.Departure departure = new tcs.ndc.hackathon.ndcrest.model.offer.response.Departure();
        departure.setAirportCode(coreDeparture.getAirportCode().getValue());
        departure.setAirportName(coreDeparture.getAirportName());
        departure.setTime(coreDeparture.getTime());
        departure.setDate(coreDeparture.getDate().toString());

        return departure;
    }

    private Price mapPrice(OfferPriceLeadDetailType.PriceDetail priceDetail) {
        Price price = new Price();
        price.setTotalAmount(priceDetail.getTotalAmount().getSimpleCurrencyPrice().getValue().toString());
        price.setBaseAmount(priceDetail.getBaseAmount().getValue().toString());
        price.setTaxAmount(priceDetail.getTaxes().getTotal().getValue().toString());

        return price;
    }

    private List<Service> mapServices(ServiceInfoAssocType serviceInfoAssocType) {
        List<Service> services = new ArrayList<>();
        for (Object serviceReference : serviceInfoAssocType.getServiceReferences()) {
            Service service = new Service();
            ServiceDetailType serviceDetailType = (ServiceDetailType) serviceReference;
            if (serviceDetailType.getEncoding() != null && serviceDetailType.getEncoding().getCode() != null) {
                service.setCode(serviceDetailType.getEncoding().getCode().getValue());
            }
            service.setDescription(serviceDetailType.getDescriptions().getDescription().get(0).getText().getValue());
            service.setPrice(serviceDetailType.getPrice().get(0).getTotal().getValue().toString());

            services.add(service);
        }

        return services;
    }
}