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
import tcs.ndc.hackathon.ndcrest.mapper.offer.OfferResponseMapper;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestController {

    @Autowired AirShoppingRQMapper airShoppingRQMapper;
    @Autowired NDCConsumer ndcConsumer;
    @Autowired OfferResponseMapper offerResponseMapper;

    @RequestMapping(value = "/offers", method = RequestMethod.POST)
    @ResponseBody
    public List<tcs.ndc.hackathon.ndcrest.model.offer.response.Offer> offers(@RequestBody Offer offer) {
        List<tcs.ndc.hackathon.ndcrest.model.offer.response.Offer> offers = new ArrayList<>();
        try {
            AirShoppingRQ airShoppingRQ = airShoppingRQMapper.map(offer);
            AirShoppingRS response = ndcConsumer.airShopping(airShoppingRQ);
            offers = offerResponseMapper.map(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }
}