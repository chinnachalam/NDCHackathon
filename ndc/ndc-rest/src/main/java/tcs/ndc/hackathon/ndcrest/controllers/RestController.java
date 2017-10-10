package tcs.ndc.hackathon.ndcrest.controllers;

import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tcs.ndc.hackathon.ndccore.NDCConsumer;
import tcs.ndc.hackathon.ndccore.builder.AirShoppingRQBuilder;
import tcs.ndc.hackathon.ndcrest.mapper.core.AirShoppingRQMapper;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Offer;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

@Controller
public class RestController {

     @Autowired AirShoppingRQMapper airShoppingRQMapper;

    @RequestMapping(value = "/offers", method = RequestMethod.POST)
    @ResponseBody
    public void offers(@RequestBody Offer offer) {
       /* NDCConsumer client = new NDCConsumer("http://iata.api.mashery.com/athena/ndc162api", "2be3rbjvuj32peygqcwp6fy5");*/
        NDCConsumer client = new NDCConsumer("http://iata.api.mashery.com/kronos/ndc162api", "2be3rbjvuj32peygqcwp6fy5");

        try {
            AirShoppingRQ airShoppingRQ = airShoppingRQMapper.map(offer);
            AirShoppingRS response = client.airShopping(airShoppingRQ);

            try {
                System.out.println("SuccessType:" + response.getSuccess());
                System.out.println("Document Name: " + response.getDocument().getName());
                AirShoppingRS.OffersGroup offersGroup = response.getOffersGroup();
                List<AirShoppingRS.OffersGroup.AirlineOffers> airlineOffersList = offersGroup.getAirlineOffers();
                for (AirShoppingRS.OffersGroup.AirlineOffers airlineOffers : airlineOffersList) {
                    for (AirShoppingRS.OffersGroup.AirlineOffers.AirlineOffer airlineOffer : airlineOffers.getAirlineOffer()) {
                        System.out.print(airlineOffer.getOfferID().getValue());
                        PricedOffer pricedOffer = airlineOffer.getPricedOffer();
                        List<PricedFlightOfferType.OfferPrice> offerPriceList = pricedOffer.getOfferPrice();
                        for (PricedFlightOfferType.OfferPrice offerPrice: offerPriceList) {
                            System.out.print(offerPrice.getRequestedDate().getPriceDetail().getTotalAmount().getSimpleCurrencyPrice().getValue() + "---");
                            System.out.print(offerPrice.getRequestedDate().getPriceDetail().getBaseAmount().getValue() + "---");
                            System.out.print(offerPrice.getRequestedDate().getPriceDetail().getTaxes().getTotal().getValue() + "---");
                        }
                        List<PricedFlightOfferAssocType> pricedFlightOfferAssocTypeList = pricedOffer.getAssociations();
                        for (PricedFlightOfferAssocType pricedFlightOfferAssocType: pricedFlightOfferAssocTypeList) {

                            for (Object flightReference: pricedFlightOfferAssocType.getApplicableFlight().getFlightReferences().getValue()) {
                                DataListType.Flight flight = (DataListType.Flight) flightReference;
                                System.out.print(flight.getFlightKey());
                            }
                        }
                        System.out.println();
                    }
                }

                AirShoppingRS.DataLists dataLists = response.getDataLists();
                List<ListOfFlightSegmentType> listOfFlightSegmentTypes = dataLists.getFlightSegmentList();
                for(ListOfFlightSegmentType listOfFlightSegmentType: listOfFlightSegmentTypes) {
                    System.out.println("+++++++++++++++++++++++++++++++++++");
                    Departure departure = listOfFlightSegmentType.getDeparture();
                    System.out.print(departure.getAirportCode().getValue() + "-");
                    FlightArrivalType flightArrivalType = listOfFlightSegmentType.getArrival();
                    System.out.print(flightArrivalType.getAirportCode().getValue() + "-");
                    System.out.print(listOfFlightSegmentType.getMarketingCarrier().getAirlineID().getValue() + "-");
                    System.out.println(listOfFlightSegmentType.getOperatingCarrier().getAirlineID().getValue());
                    System.out.println("------------------------------------");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}