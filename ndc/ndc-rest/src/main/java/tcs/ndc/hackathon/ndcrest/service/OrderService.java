package tcs.ndc.hackathon.ndcrest.service;

import org.iata.ndc.schema.OrderCreateRQ;
import org.iata.ndc.schema.OrderListRS;
import org.iata.ndc.schema.OrderViewRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndccore.NDCConsumer;
import tcs.ndc.hackathon.ndcrest.mapper.core.OrderCreateRQMapper;
import tcs.ndc.hackathon.ndcrest.mapper.order.OrderViewResponseMapper;
import tcs.ndc.hackathon.ndcrest.model.order.OrderCreate;
import tcs.ndc.hackathon.ndcrest.model.order.OrderView;
import tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder.PurchaseOrder;
import tcs.ndc.hackathon.ndcrest.model.order.response.OrderResponse;

import java.io.IOException;

@Component
public class OrderService {

    @Autowired OrderCreateRQMapper orderCreateRQMapper;
    @Autowired NDCConsumer ndcConsumer;
    @Autowired OrderViewResponseMapper orderViewResponseMapper;
    @Value("{order.mockmode}")
    private String mockMode;

    public OrderResponse createOrder(String shopId) throws IOException {
        OrderResponse orderResponse = null;
        if("no".equals(mockMode)) {
            OrderCreateRQ orderCreateRQ = orderCreateRQMapper.buildOrderCreateRQ(shopId);
            System.out.println(orderCreateRQ);
            OrderViewRS response = ndcConsumer.orderCreate(orderCreateRQ);
            orderResponse = orderViewResponseMapper.map(response);
        } else {
            orderResponse = orderViewResponseMapper.mockMap();
        }
        return orderResponse;
    }
}
