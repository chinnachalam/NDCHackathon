package tcs.ndc.hackathon.ndcrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tcs.ndc.hackathon.ndccore.NDCConsumer;
import tcs.ndc.hackathon.ndcrest.consumer.DatabaseRestConsumer;
import tcs.ndc.hackathon.ndcrest.mapper.core.AirShoppingRQMapper;
import tcs.ndc.hackathon.ndcrest.mapper.core.OrderCreateRQMapper;
import tcs.ndc.hackathon.ndcrest.mapper.offer.OfferResponseMapper;
import tcs.ndc.hackathon.ndcrest.model.error.Error;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Offer;
import tcs.ndc.hackathon.ndcrest.model.offer.response.OfferResponse;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Service;
import tcs.ndc.hackathon.ndcrest.model.order.OrderCreate;
import tcs.ndc.hackathon.ndcrest.model.order.OrderView;
import tcs.ndc.hackathon.ndcrest.service.OrderService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RestController {

    @Autowired AirShoppingRQMapper airShoppingRQMapper;
    @Autowired NDCConsumer ndcConsumer;
    @Autowired OfferResponseMapper offerResponseMapper;
    @Autowired DatabaseRestConsumer databaseRestConsumer;
    @Autowired OrderService orderService;
    @Autowired OrderCreateRQMapper orderCreateRQMapper;

    @RequestMapping(value = "/offers", method = RequestMethod.POST)
    @ResponseBody
    public OfferResponse offers(@RequestBody Offer offer) {
        OfferResponse offerResponse = new OfferResponse();
        try {
            AirShoppingRQ airShoppingRQ = airShoppingRQMapper.map(offer);
            AirShoppingRS response = ndcConsumer.airShopping(airShoppingRQ);
            offerResponse = offerResponseMapper.map(response);
        }
        catch (Exception e) {
           // Log here
        }
        return offerResponse;
    }

    @RequestMapping(value = "/available-services/{shopId}/{offerId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Service> availableServices(@PathVariable String shopId, @PathVariable String offerId) {
        ObjectMapper objectMapper = new ObjectMapper();
        return databaseRestConsumer.get("offer", offerId, tcs.ndc.hackathon.ndcrest.model.offer.response.Offer.class).getBody().getServices();

    }

    @RequestMapping(value = "/add-offer-to-shop/{shopId}/{offerId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addOfferToShop(@PathVariable String shopId, @PathVariable String offerId) {
        return null;
    }

    @RequestMapping(value = "/remove-offer-from-shop/{shopId}/{offerId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> removeOfferFromShop(@PathVariable String shopId, @PathVariable String offerId) {
        return null;
    }

    @RequestMapping(value = "/confirm-order/{shopId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> confirmOrder(@PathVariable String shopId) {
        return null;
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    @ResponseBody
    public OrderView createOrder(@RequestBody OrderCreate orderCreate) {
        return orderService.createOrder(orderCreate);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Error handleException(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        Error error = new Error();

        exception.printStackTrace(new PrintWriter(stringWriter));
        error.setErrorCode("500");
        error.setErrorDescription("Something went wrong");
        //error.setStackTrace(stringWriter.toString());
        exception.printStackTrace();
        return error;
    }

}