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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

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
    String preferredAncillaryType = "MEL";
    String preferredAncillaryName = "Kosher meal";
    String preferredAncillaryDescription = "Prepared by kosher caterers under rabbinical supervisio";

    public OfferResponse map(HttpServletRequest request, AirShoppingRS response) throws Exception {
        String path = request.getRequestURL().toString().replace(request.getRequestURI().toString(), request.getContextPath().toString());
        String shopId = UUID.randomUUID().toString();
        int budgetAmount=1500;

        OfferResponse offerResponse = new OfferResponse();
        List<Offer> offers = new ArrayList<>();
        List<Offer> offerList = new ArrayList<>();
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
                            //String originalOfferItemId = offerPrice.getOfferItemID();
                            offer.setConnection(connection);
                            offer.setPrice(mapPrice(offerPrice.getRequestedDate().getPriceDetail()));
                            for (PricedFlightOfferAssocType pricedFlightOfferAssoc : offerPrice.getRequestedDate().getAssociations()) {
                                offer.setServices(mapServices(pricedFlightOfferAssoc.getAssociatedService(), shopId, offerId));
                            }
                            offer.setServices(filterService(offer));
                            Service service = new Service();
                            String serviceId = UUID.randomUUID().toString();
                            service.setServiceId(serviceId);
                            service.setCode(preferredAncillaryType);
                            service.setName(preferredAncillaryName);
                            service.setDescription(preferredAncillaryDescription);
                            service.setPrice(10);
                            service.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).addServiceToShop(shopId, offerId, serviceId)).withRel("addServiceToShop"));
                            service.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).removeServiceFromShop(shopId, offerId, serviceId)).withRel("removeServiceFromShop"));
                            offer.getServices().add(service);
                            if (!filterOffer(budgetAmount, offer)) {
                                databaseRestConsumer.saveWithId(offer, "offer", offerId);
                                /*if (true) {
                                    offer.setServices(null);
                                }*/
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

        if(offers.size()>0) {
            //List<String> list = offers.stream().map(Offer::getConnection()).collect(Collectors.toList());
             List<Offer> offerings = new ArrayList<>();
            if (offers.size() > 5) {
                Random random = new Random();
                int randNo = 0;
                randNo = random.nextInt(offers.size() - 1);
                for (int index = 0; index < 5; index++) {
                    offerings.add(offers.get(randNo));
                }
            } else {
                offerings.addAll(offers);
            }

            //List<Offer> offerList = new ArrayList<>();
            offers = personalizeOffers(offerings);

            //offers.subList(5, offers.size()).clear();
            for (int index = 0; index < offers.size(); index++) {
                Offer offer = offers.get(index);
                offer.setServices(null);
                String imageId=null;
                //String imageId = imageGeneratorService.createConnectionImage(shopId, offers.get(index).getConnection());
                try {
                    //offer.add(linkTo(methodOn(tcs.ndc.hackathon.ndcrest.controllers.RestController.class).getImageWithMediaType2(shopId, imageId)).withRel("cardLink"));
                    offer.add(new Link(path + "/image/" + shopId + "/" + imageId + ".png", "carouselConnectionImage"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            offerResponse.setOffers(offers);
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
            service.setPrice(serviceDetailType.getPrice().get(0).getTotal().getValue().intValue());
            service.setName(serviceDetailType.getName().getValue());
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

    private boolean filterOffer(int budget, Offer offer) {

        boolean offerStatus = false;
        if(Double.valueOf(offer.getPrice().getTotalAmount()) > budget) {
            return true;
        } else {
            return false;
        }

        //return offerStatus;
    }

    private List<Service> filterService(Offer offer) {
        if(offer!=null) {
            if (offer.getServices() != null) {
                List<Service> serviceList = new ArrayList<>();
                List<String> coveredServices = new ArrayList<>();
                for (Service service : offer.getServices()) {
                    if (!coveredServices.contains(service.getCode())) {
                        coveredServices.add(service.getCode());
                        serviceList.add(service);
                    }
                }
                return serviceList;
            }
        }
        return null;
    }

    private List<Offer> personalizeOffers (List<Offer> offerList) {
        List<Offer> offerListPersonalized = new ArrayList<>();

        int lowPriceIndex=0;
        int preferredMealIndex=0;
        int bestSeatIndex=0;

        boolean mealSet = false;
        for (int i = 0; i < offerList.size(); i++) {

            if (lowPriceIndex != i) {
                if(!mealSet) {
                    Offer preferredMealOffer = new Offer();
                    preferredMealOffer = offerList.get(i);
                    Service service = new Service();
                    service.setCode(preferredAncillaryType);
                    service.setName(preferredAncillaryName);
                    service.setDescription(preferredAncillaryDescription);
                    service.setPrice(10);
                    preferredMealOffer.getServices().add(service);
                    preferredMealOffer.setBestOfferStatus(true);
                    preferredMealOffer.setBestOfferReason("Your favourite Meal");
                    offerListPersonalized.add(preferredMealOffer);
                    mealSet=true;
                }
                else{
                            /*Offer preferredMealOffer = offerList.get(preferredMealIndex);
                            preferredMealOffer.setBestOfferStatus(true);
                            preferredMealOffer.setBestOfferReason("Your favourite Meal");
                            offerListPersonalized.add(preferredMealOffer);*/
                }
            } else { }
        }

        Double lowestPrice=0.0;
        if(offerList.size()>0) {
            for (int i = 0; i < offerList.size(); i++) {
                System.out.println("Lowest : " + Double.valueOf(offerList.get(0).getPrice().getTotalAmount()));
                System.out.println("Current : " + Double.valueOf(offerList.get(i).getPrice().getTotalAmount()));
                if (lowestPrice > Double.valueOf(offerList.get(i).getPrice().getTotalAmount())) {
                    lowPriceIndex=i;
                }
            }

            Offer cheapestOffer = new Offer();
            cheapestOffer = offerList.get(lowPriceIndex);
            cheapestOffer.setPrice(offerList.get(lowPriceIndex).getPrice());
            cheapestOffer.setConnection(offerList.get(lowPriceIndex).getConnection());
            cheapestOffer.setServices(offerList.get(lowPriceIndex).getServices());
            cheapestOffer.setBestOfferStatus(true);
            cheapestOffer.setBestOfferReason("Lowest Fare");
            offerListPersonalized.add(cheapestOffer);

            for (int i = 1; i < offerList.size(); i++) {

                if (offerList.get(1).getServices() != null) {
                    if(i!=lowPriceIndex) {
                        for (Service service : offerList.get(1).getServices()) {
                            if (service.getCode().equals(preferredAncillaryType)) {
                                if (service.getName().equals(preferredAncillaryName)) {
                                    preferredMealIndex=i;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            //if(preferredMealIndex==0) {


            /*for(int i=1; i<offerList.size();i++) {
                if(of)
            }*/

        /*for(int i=1; i<offerList.size(); i++) {
            if(offerList.get(1).getServices()!=null) {
                for (Service service : offerList.get(1).getServices()) {
                    service.

                }
            }
        }*/
            /*if (offerList.size() > 4) {
                Offer offerY = offerList.get(2);
                offerY.setBestOfferStatus(true);
                offerY.setBestOfferReason("More Products");
                offerListPersonalized.add(offerList.get(2));
                offerListPersonalized.add(offerList.get(3));
                offerListPersonalized.add(offerList.get(4));
            } else if (offerList.size() > 3) {
                offerListPersonalized.add(offerList.get(2));
                offerListPersonalized.add(offerList.get(3));
            } else {
                offerListPersonalized.add(offerList.get(2));
            }*/
        }
        /*Offer offer = offerList.get(i);
        offerListPersonalized.add(offer);*/
        offerList.addAll(offerListPersonalized);
        return offerListPersonalized;
    }
}