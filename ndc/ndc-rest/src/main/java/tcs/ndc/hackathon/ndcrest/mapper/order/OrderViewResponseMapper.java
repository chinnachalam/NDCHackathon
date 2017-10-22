package tcs.ndc.hackathon.ndcrest.mapper.order;

import org.iata.ndc.schema.OrderViewRS;
import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndcrest.model.order.OrderView;
import tcs.ndc.hackathon.ndcrest.model.order.response.FlightReservation;
import tcs.ndc.hackathon.ndcrest.model.order.response.OrderResponse;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

@Component
public class OrderViewResponseMapper {

    public OrderResponse map(OrderViewRS orderViewRS) {
        OrderResponse orderResponse = new OrderResponse();

        FlightReservation flightReservation = new FlightReservation();
        flightReservation.setPnr(orderViewRS.getResponse().getOrder().get(0).getBookingReferences().get(0).getID());
        flightReservation.setOrderId(orderViewRS.getResponse().getOrder().get(0).getOrderID().getValue());
        orderResponse.setFlightReservation(flightReservation);
        return orderResponse;
    }

    public OrderResponse mockMap() {
        OrderResponse orderResponse = new OrderResponse();
        FlightReservation flightReservation = new FlightReservation();
        flightReservation.setPnr(new OrderViewResponseMapper().randomString(6));
        flightReservation.setOrderId(new OrderViewResponseMapper().randomString(15));
        orderResponse.setFlightReservation(flightReservation);
        return orderResponse;
    }

    public String randomString( int len ) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}
