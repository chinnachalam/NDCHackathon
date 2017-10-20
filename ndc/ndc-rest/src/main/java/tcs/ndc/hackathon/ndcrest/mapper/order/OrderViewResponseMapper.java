package tcs.ndc.hackathon.ndcrest.mapper.order;

import org.iata.ndc.schema.OrderViewRS;
import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndcrest.model.order.OrderView;
import tcs.ndc.hackathon.ndcrest.model.order.response.FlightReservation;
import tcs.ndc.hackathon.ndcrest.model.order.response.OrderResponse;

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
}
