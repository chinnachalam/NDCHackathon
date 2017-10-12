package tcs.ndc.hackathon.ndcrest.controllers;

import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tcs.ndc.hackathon.ndccore.NDCConsumer;
import tcs.ndc.hackathon.ndcrest.mapper.core.AirShoppingRQMapper;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Offer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestController {

    @Autowired AirShoppingRQMapper airShoppingRQMapper;
    @Autowired NDCConsumer ndcConsumer;

    @RequestMapping(value = "/offers", method = RequestMethod.POST)
    @ResponseBody
    public void offers(@RequestBody Offer offer) {

        try {
            AirShoppingRQ airShoppingRQ = airShoppingRQMapper.map(offer);
            AirShoppingRS response = ndcConsumer.airShopping(airShoppingRQ);

            AirShoppingRS.OffersGroup offersGroup = response.getOffersGroup();
            AirShoppingRS.DataLists dataLists = response.getDataLists();


            List<AirShoppingRS.OffersGroup.AirlineOffers> airlineOffersList = offersGroup.getAirlineOffers();
            for (AirShoppingRS.OffersGroup.AirlineOffers airlineOffers : airlineOffersList) {
                for (AirShoppingRS.OffersGroup.AirlineOffers.AirlineOffer airlineOffer : airlineOffers.getAirlineOffer()) {
                    PricedOffer pricedOffer = airlineOffer.getPricedOffer();
                    /*List<PricedFlightOfferType.OfferPrice> offerPriceList = pricedOffer.getOfferPrice();
                    for (PricedFlightOfferType.OfferPrice offerPrice : offerPriceList) {
                        System.out.print(offerPrice.getRequestedDate().getPriceDetail().getTotalAmount().getSimpleCurrencyPrice().getValue() + "---");
                        System.out.print(offerPrice.getRequestedDate().getPriceDetail().getBaseAmount().getValue() + "---");
                        System.out.print(offerPrice.getRequestedDate().getPriceDetail().getTaxes().getTotal().getValue() + "---");
                    }*/
                    List<PricedFlightOfferAssocType> pricedFlightOfferAssocTypeList = pricedOffer.getAssociations();
                    for (PricedFlightOfferAssocType pricedFlightOfferAssocType : pricedFlightOfferAssocTypeList) {
                        System.out.println("******************************************************");
                        for (Object flightReference : pricedFlightOfferAssocType.getApplicableFlight().getFlightReferences().getValue()) {
                            DataListType.Flight flight = (DataListType.Flight) flightReference;
                            for(Object segmentReference: flight.getSegmentReferences().getValue()) {
                                ListOfFlightSegmentType segment = (ListOfFlightSegmentType) segmentReference;
                                System.out.print(segment.getDeparture().getAirportCode().getValue()
                                        + segment.getDeparture().getTime()
                                        + "----------"
                                        + segment.getArrival().getAirportCode().getValue()
                                        + segment.getArrival().getTime());
                            }
                            System.out.println("");
                        }

                        PriceClassType priceClassType = (PriceClassType) pricedFlightOfferAssocType.getPriceClass().getPriceClassReference();
                        System.out.println(priceClassType.getFareBasisCode().getCode());
                        System.out.println("******************************************************");
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}