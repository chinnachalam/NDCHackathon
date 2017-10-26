package tcs.ndc.hackathon.ndcrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
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
import tcs.ndc.hackathon.ndcrest.model.order.response.OrderResponse;
import tcs.ndc.hackathon.ndcrest.model.shop.ShopDetails;
import tcs.ndc.hackathon.ndcrest.service.OrderService;
import org.apache.commons.codec.binary.Base64;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@Controller
@CrossOrigin
public class RestController {

    @Autowired AirShoppingRQMapper airShoppingRQMapper;
    @Autowired NDCConsumer ndcConsumer;
    @Autowired OfferResponseMapper offerResponseMapper;
    @Autowired DatabaseRestConsumer databaseRestConsumer;
    @Autowired OrderService orderService;
    @Autowired OrderCreateRQMapper orderCreateRQMapper;

    @RequestMapping(value = "/offers", method = RequestMethod.POST)
    @ResponseBody
    public OfferResponse offers(HttpServletRequest request, @RequestBody Offer offer) {
        String path = request.getRequestURL().toString().replace(request.getRequestURI().toString(), request.getContextPath().toString());
        System.out.println(path);
        OfferResponse offerResponse = new OfferResponse();
        try {
            AirShoppingRQ airShoppingRQ = airShoppingRQMapper.map(offer);
            AirShoppingRS response = ndcConsumer.airShopping(airShoppingRQ);
            offerResponse = offerResponseMapper.map(request, response);
        }
        catch (Exception e) {
            // Log here
        }
        return offerResponse;
    }

    @RequestMapping(value = "/add-offer-to-shop/{shopId}/{offerId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addOfferToShop(@PathVariable String shopId, @PathVariable String offerId) {
        ShopDetails shopDetails = databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() != null ? databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() : new ShopDetails();
        Map<String, String> addedDetails = shopDetails != null && shopDetails.getAddedDetails() != null ? shopDetails.getAddedDetails() : new HashMap<>();
        addedDetails.put(offerId, "offer");
        shopDetails.setAddedDetails(addedDetails);
        databaseRestConsumer.saveWithId(shopDetails, "shop", shopId);

        return null;
    }

    @RequestMapping(value = "/remove-offer-from-shop/{shopId}/{offerId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> removeOfferFromShop(@PathVariable String shopId, @PathVariable String offerId) {
        ShopDetails shopDetails = databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() != null ? databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() : new ShopDetails();
        Map<String, String> addedDetails = shopDetails != null && shopDetails.getAddedDetails() != null ? shopDetails.getAddedDetails() : new HashMap<>();
        addedDetails.remove(offerId);
        shopDetails.setAddedDetails(addedDetails);
        databaseRestConsumer.saveWithId(shopDetails, "shop", shopId);

        return null;
    }

    @RequestMapping(value = "/available-services/{shopId}/{offerId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Service> availableServices(@PathVariable String shopId, @PathVariable String offerId) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Service> services = databaseRestConsumer.get("offer", offerId, tcs.ndc.hackathon.ndcrest.model.offer.response.Offer.class).getBody().getServices();
        for (Service service : services) {
            databaseRestConsumer.saveWithId(service, "service", service.getServiceId());
        }
        return services;
    }

    @RequestMapping(value = "/add-service-to-shop/{shopId}/{offerId}/{serviceId}", method = RequestMethod.POST)
    @ResponseBody
    public List<Service> addServiceToShop(@PathVariable String shopId, @PathVariable String offerId, @PathVariable String serviceId) {
        ShopDetails shopDetails = databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() != null ? databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() : new ShopDetails();
        Map<String, String> addedDetails = shopDetails != null && shopDetails.getAddedDetails() != null ? shopDetails.getAddedDetails() : new HashMap<>();
        addedDetails.put(serviceId, "service");
        shopDetails.setAddedDetails(addedDetails);
        databaseRestConsumer.saveWithId(shopDetails, "shop", shopId);

        return null;
    }

    @RequestMapping(value = "/remove-service-from-shop/{shopId}/{offerId}/{serviceId}", method = RequestMethod.POST)
    @ResponseBody
    public List<Service> removeServiceFromShop(@PathVariable String shopId, @PathVariable String offerId, @PathVariable String serviceId) {
        ShopDetails shopDetails = databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() != null ? databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() : new ShopDetails();
        Map<String, String> addedDetails = shopDetails != null && shopDetails.getAddedDetails() != null ? shopDetails.getAddedDetails() : new HashMap<>();
        addedDetails.remove(serviceId);
        shopDetails.setAddedDetails(addedDetails);
        databaseRestConsumer.saveWithId(shopDetails, "shop", shopId);

        return null;

    }

    @RequestMapping(value = "/shop-details/{shopId}", method = RequestMethod.GET)
    @ResponseBody
    public ShopDetails getShopDetails(@PathVariable String shopId) {
        ShopDetails shopDetails = databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() != null ? databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() : new ShopDetails();
        Map<String, String> addedDetails = shopDetails != null && shopDetails.getAddedDetails() != null ? shopDetails.getAddedDetails() : new HashMap<>();
        List<tcs.ndc.hackathon.ndcrest.model.offer.response.Offer> offers = new ArrayList<>();
        List<Service> services = new ArrayList<>();
        for (String id : addedDetails.keySet()) {
            String type = addedDetails.get(id);
            if ("offer".equals(type)) {
                offers.add(databaseRestConsumer.get("offer", id, tcs.ndc.hackathon.ndcrest.model.offer.response.Offer.class).getBody());
            }
            else if ("service".equals(type)) {
                services.add(databaseRestConsumer.get("service", id, Service.class).getBody());
            }
        }
        shopDetails.setOffers(offers);
        shopDetails.setServices(services);
        shopDetails.setAddedDetails(null);
        return shopDetails;
    }

    @RequestMapping(value = "/confirm-order/{shopId}", method = RequestMethod.POST)
    @ResponseBody
    public OrderResponse confirmOrder(@PathVariable String shopId) throws IOException {

        return orderService.createOrder(shopId);
    }

    @GetMapping(
            value = "/image/{shopId}/{imageId}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody byte[] getImage(HttpServletRequest request, @PathVariable String shopId, @PathVariable String imageId) throws IOException {
        InputStream input = new FileInputStream("C:/dev/workspace/NDCDEV/NDCHackathon/ndc/ndc-rest/generated/"+shopId+"/"+imageId+".png");
        return IOUtils.toByteArray(input);
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