package tcs.ndc.hackathon.ndcrest.mapper.order;

import org.iata.ndc.schema.OrderViewRS;
import tcs.ndc.hackathon.ndcrest.model.order.OrderView;

public class OrderViewResponseMapper {

    public OrderView map(OrderViewRS orderViewRS) {
        OrderView orderView = new OrderView();

        return orderView;
    }
}
