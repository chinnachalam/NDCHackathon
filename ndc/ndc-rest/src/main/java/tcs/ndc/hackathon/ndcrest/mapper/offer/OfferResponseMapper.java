package tcs.ndc.hackathon.ndcrest.mapper.offer;

import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndcrest.consumer.DatabaseRestConsumer;
import tcs.ndc.hackathon.ndcrest.model.offer.response.*;
import tcs.ndc.hackathon.ndcrest.service.ImageGeneratorService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OfferResponseMapper {

    @Autowired
    DatabaseRestConsumer databaseRestConsumer;
    @Autowired
    ImageGeneratorService imageGeneratorService;
    @Autowired
    private ServletContext servletContext;

    public OfferResponse map(HttpServletRequest request, AirShoppingRS response) throws Exception {
        String path = request.getRequestURL().toString().replace(request.getRequestURI().toString(), request.getContextPath().toString());
        String shopId = UUID.randomUUID().toString();
        OfferResponse offerResponse = new OfferResponse();
        List<Offer> offers = new ArrayList<>();
        offerResponse.setOffers(offers);

        offerResponse.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).confirmOrder(shopId)).withRel("confirmOrder"));
        offerResponse.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).getShopDetails(shopId)).withRel("shopDetails"));

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
                        Duration duration = null;
                        for (Object flightReference : pricedFlightOfferAssocType.getApplicableFlight().getFlightReferences().getValue()) {
                            DataListType.Flight flight = (DataListType.Flight) flightReference;
                            duration = ((DataListType.Flight) flightReference).getJourney().getTime();
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
                        connection.setConnectionTime(buildConnectionTime(duration));

                        List<PricedFlightOfferType.OfferPrice> offerPriceList = pricedOffer.getOfferPrice();
                        for (PricedFlightOfferType.OfferPrice offerPrice : offerPriceList) {
                            Offer offer = new Offer();
                            String offerId = UUID.randomUUID().toString();
                            offer.setConnection(connection);
                            offer.setPrice(mapPrice(offerPrice.getRequestedDate().getPriceDetail()));
                            for (PricedFlightOfferAssocType pricedFlightOfferAssoc : offerPrice.getRequestedDate().getAssociations()) {
                                offer.setServices(mapServices(pricedFlightOfferAssoc.getAssociatedService(), shopId, offerId));
                            }
                            if (!filterOffer(offer)) {
                                databaseRestConsumer.saveWithId(offer, "offer", offerId);
                                if (true) {
                                    offer.setServices(null);
                                }
                                offer.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).availableServices(shopId, offerId)).withRel("availableServices"));
                                offer.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).addOfferToShop(shopId, offerId)).withRel("addOfferToShop"));
                                offer.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).removeOfferFromShop(shopId, offerId)).withRel("removeOfferFromShop"));
                                offers.add(offer);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        offers.subList(4, offers.size()).clear();
        for (int index = 0; index < offers.size(); index++) {
            String imageId = imageGeneratorService.createConnectionImage(shopId, offers.get(index).getConnection());
            Offer offer = offers.get(index);
            try {
                //offer.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).getImageWithMediaType2(shopId, imageId)).withRel("cardLink"));
                offer.add(new Link(path + "/image/"+shopId+"/"+imageId+".png", "carouselConnectionImage"));
                //offer.add(new Link("https://a598686c.ngrok.io/ndcrest/getImageDummy/"+shopId+"/"+imageId, "cardLink"));
                //System.out.println(servletContext.getContextPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //offers.get(index).getConnection().setCardLink(imageLink);
        }
        return offerResponse;
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

    private List<Service> mapServices(ServiceInfoAssocType serviceInfoAssocType, String shopId, String offerId) {
        List<Service> services = new ArrayList<>();
        for (Object serviceReference : serviceInfoAssocType.getServiceReferences()) {
            Service service = new Service();
            String serviceId = UUID.randomUUID().toString();
            service.setServiceId(serviceId);
            ServiceDetailType serviceDetailType = (ServiceDetailType) serviceReference;
            if (serviceDetailType.getEncoding() != null && serviceDetailType.getEncoding().getCode() != null) {
                service.setCode(serviceDetailType.getEncoding().getCode().getValue());
            }
            service.setDescription(serviceDetailType.getDescriptions().getDescription().get(0).getText().getValue());
            service.setPrice(serviceDetailType.getPrice().get(0).getTotal().getValue().toString());

            service.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).addServiceToShop(shopId, offerId, serviceId)).withRel("addServiceToShop"));
            service.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).removeServiceFromShop(shopId, offerId, serviceId)).withRel("removeServiceFromShop"));

            services.add(service);
        }

        return services;
    }

    private String buildConnectionTime(Duration duration) {
        Date connectionDate = new Date();
        long timeMilliseconds = duration.getTimeInMillis(connectionDate);
        long minute = (timeMilliseconds / (1000 * 60)) % 60;
        long hour = (timeMilliseconds / (1000 * 60 * 60));
        String connectionTime = String.format("%2dH%02dM", hour, minute).trim();
        return connectionTime;
    }

    private boolean filterOffer(Offer offer) {
        boolean offerStatus = false;

        //if(offer.getPrice() > )

        return offerStatus;
    }
}