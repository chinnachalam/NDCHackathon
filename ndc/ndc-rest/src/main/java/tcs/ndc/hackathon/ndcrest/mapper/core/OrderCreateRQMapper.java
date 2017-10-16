package tcs.ndc.hackathon.ndcrest.mapper.core;

import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndccore.builder.element.PartyBuilder;
import tcs.ndc.hackathon.ndcrest.model.order.OrderCreate;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderCreateRQMapper {

    @Autowired CommonMapper commonMapper;
    /*@Autowired*/ ObjectFactory objectFactory;

    public OrderCreateRQ buildOrderCreateRQ (OrderCreate orderCreate) {
        OrderCreateRQ orderCreateRQ = new OrderCreateRQ();

        orderCreateRQ.setDocument(commonMapper.buildDocument());
        orderCreateRQ.setParty(commonMapper.buildParty());
        orderCreateRQ.setQuery(buildQuery());
        return orderCreateRQ;
    }

    //----- OrderCreateRQ/Query Section --------
    private OrderCreateRQ.Query buildQuery() {
        OrderCreateRQ.Query query = new OrderCreateRQ.Query();

        //query.setPassengers(buildPassengers());

        return query;
    }

    private Passenger buildPassengers() {
        Passenger passenger = new Passenger();
        TravelerSummaryType.Name name = objectFactory.createTravelerSummaryTypeName();
        TravelerSummaryType.Name.Surname surname = new TravelerSummaryType.Name.Surname();
        surname.setValue("<<>>");
        name.setSurname(surname);
        passenger.setName(name);
        passenger.setContacts(buildContacts());

        return passenger;
    }

    private List<Contact> buildContacts() {
        List<Contact> contactList = new ArrayList<>();

        return contactList;
    }

}
