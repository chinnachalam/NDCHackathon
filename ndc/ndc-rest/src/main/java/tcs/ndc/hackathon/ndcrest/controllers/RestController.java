package tcs.ndc.hackathon.ndcrest.controllers;

import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tcs.ndc.hackathon.ndccore.NDCConsumer;
import tcs.ndc.hackathon.ndcrest.mapper.core.AirShoppingRQMapper;
import tcs.ndc.hackathon.ndcrest.mapper.core.OrderCreateRQMapper;
import tcs.ndc.hackathon.ndcrest.mapper.offer.OfferResponseMapper;
import tcs.ndc.hackathon.ndcrest.model.error.Error;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Offer;
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
    @Autowired OrderService orderService;
    @Autowired OrderCreateRQMapper orderCreateRQMapper;

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