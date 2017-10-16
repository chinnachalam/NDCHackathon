package tcs.ndc.hackathon.ndcrest.service;

import org.iata.ndc.schema.OrderCreateRQ;
import org.iata.ndc.schema.OrderListRS;
import org.iata.ndc.schema.OrderViewRS;
import org.springframework.beans.factory.annotation.Autowired;
import tcs.ndc.hackathon.ndccore.NDCConsumer;
import tcs.ndc.hackathon.ndcrest.mapper.core.OrderCreateRQMapper;
import tcs.ndc.hackathon.ndcrest.mapper.order.OrderViewResponseMapper;
import tcs.ndc.hackathon.ndcrest.model.order.OrderCreate;
import tcs.ndc.hackathon.ndcrest.model.order.OrderView;

public class OrderService {

    @Autowired OrderCreateRQMapper orderCreateRQMapper;
    @Autowired NDCConsumer ndcConsumer;
    @Autowired OrderViewResponseMapper orderViewResponseMapper;
    @Autowired OrderView orderView;


    public OrderView createOrder(OrderCreate orderCreate) {
        try {
        OrderCreateRQ orderCreateRQ = orderCreateRQMapper.buildOrderCreateRQ(orderCreate);
        OrderViewRS response = ndcConsumer.orderCreate(orderCreateRQ);
        orderView = orderViewResponseMapper.map(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return orderView;
    }
}
