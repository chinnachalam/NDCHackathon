package tcs.ndc.hackathon.ndcrest.mapper.core;

import org.iata.ndc.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import tcs.ndc.hackathon.ndccore.builder.element.PartyBuilder;
import tcs.ndc.hackathon.ndcrest.consumer.DatabaseRestConsumer;
import tcs.ndc.hackathon.ndcrest.model.employee.CreditCard;
import tcs.ndc.hackathon.ndcrest.model.employee.Employee;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Offer;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Service;
import tcs.ndc.hackathon.ndcrest.model.order.OrderCreate;
import tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder.OrderItem;
import tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder.Payment;
import tcs.ndc.hackathon.ndcrest.model.order.PurchaseOrder.PurchaseOrder;
import tcs.ndc.hackathon.ndcrest.model.shop.ShopDetails;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderCreateRQMapper {

    @Autowired
    CommonMapper commonMapper;
    /*@Autowired*/ ObjectFactory objectFactory;
    @Autowired
    DatabaseRestConsumer databaseRestConsumer;


    public OrderCreateRQ buildOrderCreateRQ(String shopId) {
        ShopDetails shopDetails = getShopDetails(shopId);
        System.out.println();
        //Employee employee = databaseRestConsumer.get("employee", shopDetails.getPassengerId(), Employee.class).getBody();
        Employee employee = databaseRestConsumer.get("employee", "888111", Employee.class).getBody();

        if (employee == null) {
            System.out.println("Employee null");
        }

        OrderCreateRQ orderCreateRQ = new OrderCreateRQ();
        orderCreateRQ.setDocument(commonMapper.buildDocument());
        orderCreateRQ.setParty(commonMapper.buildParty());
        orderCreateRQ.setQuery(buildQuery(shopId, employee, shopDetails));
        return orderCreateRQ;
    }

    public ShopDetails getShopDetails(@PathVariable String shopId) {
        ShopDetails shopDetails = databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() != null ? databaseRestConsumer.get("shop", shopId, ShopDetails.class).getBody() : new ShopDetails();
        Map<String, String> addedDetails = shopDetails != null && shopDetails.getAddedDetails() != null ? shopDetails.getAddedDetails() : new HashMap<>();
        List<tcs.ndc.hackathon.ndcrest.model.offer.response.Offer> offers = new ArrayList<>();
        List<Service> services = new ArrayList<>();
        for (String id : addedDetails.keySet()) {
            String type = addedDetails.get(id);
            if ("offer".equals(type)) {
                offers.add(databaseRestConsumer.get("offer", id, tcs.ndc.hackathon.ndcrest.model.offer.response.Offer.class).getBody());
            } else if ("service".equals(type)) {
                services.add(databaseRestConsumer.get("service", id, Service.class).getBody());
            }
        }
        shopDetails.setOffers(offers);
        shopDetails.setServices(services);
        shopDetails.setAddedDetails(null);
        return shopDetails;
    }

    //----- OrderCreateRQ/Query Section --------
    private OrderCreateRQ.Query buildQuery(String shopId, Employee employee, ShopDetails shopDetails) {
        OrderCreateRQ.Query query = new OrderCreateRQ.Query();
        query.setPassengers(buildPassengers(employee, shopDetails));
        query.setOrderItems(buildOrderItems(shopId, employee, shopDetails));
        query.setPayments(buildPayments(employee, shopDetails));
        return query;
    }

    private OrderCreateRQ.Query.Passengers buildPassengers(Employee employee, ShopDetails shopDetails) {
        OrderCreateRQ.Query.Passengers passengers = new OrderCreateRQ.Query.Passengers();
        Passenger passenger = new Passenger();
        passenger.setObjectKey("SH1");
        TravelerCoreType.PTC ptc = new TravelerCoreType.PTC();
        ptc.setQuantity(new BigInteger("1"));
        passenger.setPTC(ptc);

        //------ Name Details ------
        //Surname
        TravelerSummaryType.Name name = objectFactory.createTravelerSummaryTypeName();
        TravelerSummaryType.Name.Surname surname = new TravelerSummaryType.Name.Surname();
        surname.setValue(employee.getName().getSurname());
        name.setSurname(surname);

        //Given Name
        TravelerSummaryType.Name.Given givenName = new TravelerSummaryType.Name.Given();
        givenName.setValue(employee.getName().getFirstName());
        name.getGiven().add(givenName);

        //Middle Name
        TravelerSummaryType.Name.Middle middleName = new TravelerSummaryType.Name.Middle();
        middleName.setValue(employee.getName().getMiddle());
        name.getMiddle().add(middleName);

        name.setSurnameSuffix(employee.getName().getTitle());
        passenger.setName(name);

        //------ Contact List ------
        passenger.setContacts(buildContacts(employee));

        passengers.getPassenger().add(passenger);
        return passengers;
    }

    private List<Contact> buildContacts(Employee employee) {
        List<Contact> contactList = new ArrayList<>();

        Contact contact = new Contact();

        //Address Contact
        AddressContactType addressContactType = new AddressContactType();
        addressContactType.getStreet().add(employee.getContacts().getAddressContact().getStreet());
        addressContactType.setCityName(employee.getContacts().getAddressContact().getCityName());
        addressContactType.setPostalCode(employee.getContacts().getAddressContact().getPostalCode());
        addressContactType.setCounty(employee.getContacts().getAddressContact().getCountryName());
        contact.setAddressContact(addressContactType);

        //Email Contact
        EmailType emailType = new EmailType();
        EmailType.Address address = new EmailType.Address();
        address.setValue(employee.getContacts().getEmailContact());
        emailType.setAddress(address);
        contact.setEmailContact(emailType);

        //Other Contact
        if (employee.getContacts().getMobileContact() != null) {
            OtherContactMethodType otherContactMethodType = new OtherContactMethodType();
            otherContactMethodType.setName("Mobile");
            otherContactMethodType.setValue(employee.getContacts().getMobileContact().getMobileNumber());
            contact.setOtherContactMethod(otherContactMethodType);
        }

        contactList.add(contact);
        return contactList;
    }

    private OrderCreateRQ.Query.OrderItems buildOrderItems(String shopId, Employee employee, ShopDetails shopDetails) {
        OrderCreateRQ.Query.OrderItems orderItems = new OrderCreateRQ.Query.OrderItems();
        ShoppingResponseOrderType shoppingResponseOrderType = new ShoppingResponseOrderType();
        shoppingResponseOrderType.setOwner("C9");
        ShoppingResponseOrderType.ResponseID responseID = new ShoppingResponseOrderType.ResponseID();
        responseID.setValue(shopId);
        shoppingResponseOrderType.setResponseID(responseID);

        //Offer List
        List<ShoppingResponseOrderType.Offer> offerList = new ArrayList<>();
        Map<String, String> addedDetails = shopDetails.getAddedDetails();

        ShoppingResponseOrderType.Offer offer = new ShoppingResponseOrderType.Offer();
        ItemIDType itemIDType = new ItemIDType();
        itemIDType.setOwner("C9");
        offer.setOfferID(itemIDType);
        List<ShoppingResponseOrderType.Offer.OfferItem> offerItemList = new ArrayList<>();
        for (Map.Entry<String, String> entry : addedDetails.entrySet()) {

            //Service Offers
            if ("service".equals(entry.getValue().toLowerCase())) {
                ShoppingResponseOrderType.Offer.OfferItem offerItem = new ShoppingResponseOrderType.Offer.OfferItem();
                ItemIDType itemIDTypeOfferItem = new ItemIDType();
                itemIDTypeOfferItem.setObjectKey("ID1");
                itemIDTypeOfferItem.setOwner("C9");
                itemIDTypeOfferItem.setValue("1_1");
                offerItem.setOfferItemID(itemIDTypeOfferItem);
                List<Object> passengerList = new ArrayList<>();
                passengerList.add("SH1");
                offerItem.setPassengers(passengerList);
                offerItemList.add(offerItem);
            }
        }
        offer.setOfferItems(offerItemList);

        ShoppingResponseOrderType.Offer.TotalPrice totalPrice = new ShoppingResponseOrderType.Offer.TotalPrice();
        SimpleCurrencyPriceType simpleCurrencyPriceType = new SimpleCurrencyPriceType();
        simpleCurrencyPriceType.setValue(new BigDecimal("1500"));
        totalPrice.setSimpleCurrencyPrice(simpleCurrencyPriceType);
        offer.setTotalPrice(totalPrice);

        shoppingResponseOrderType.setOffers(offerList);

        orderItems.setShoppingResponse(shoppingResponseOrderType);
        return orderItems;
    }

    private List<OrderPaymentFormType> buildPayments (Employee employee, ShopDetails shopDetails) {
        List<OrderPaymentFormType> orderPaymentFormTypeList = new ArrayList<>();
        OrderPaymentFormType orderPaymentFormType = new OrderPaymentFormType();

        CreditCard creditCard = employee.getPaymentInformation().getCreditCard().get(0);
        //Payment Method
        OrderPaymentFormType.Method paymentMethod = new OrderPaymentFormType.Method();
        PaymentCardType paymentCardType = new PaymentCardType();
        paymentCardType.setCardType(creditCard.getCardType());
        paymentCardType.setCardCode(creditCard.getCardCode());

        PaymentCardType.SeriesCode seriesCode = new PaymentCardType.SeriesCode();
        seriesCode.setValue(String.valueOf(creditCard.getSeriesCode()));
        paymentCardType.setSeriesCode(seriesCode);

        PaymentCardType.CardHolderName cardHolderName = new PaymentCardType.CardHolderName();
        cardHolderName.setValue(creditCard.getCardHolderName());
        paymentCardType.setCardHolderName(cardHolderName);

        PaymentCardType.EffectiveExpireDate effectiveExpireDate = new PaymentCardType.EffectiveExpireDate();
        effectiveExpireDate.setEffective(creditCard.getEffectiveExpiryDate());
        effectiveExpireDate.setExpiration(creditCard.getExpirationDate());
        paymentCardType.setEffectiveExpireDate(effectiveExpireDate);

        paymentMethod.setPaymentCard(paymentCardType);
        orderPaymentFormType.setMethod(paymentMethod);

        //Amount
        SimpleCurrencyPriceType simpleCurrencyPriceType = new SimpleCurrencyPriceType();
        simpleCurrencyPriceType.setCode("EUR");
        simpleCurrencyPriceType.setValue(new BigDecimal("1500"));
        orderPaymentFormType.setAmount(simpleCurrencyPriceType);

        //Payer
        OrderPaymentFormType.Payer payer = new OrderPaymentFormType.Payer();
        OrderPaymentFormType.Payer.Name name = new OrderPaymentFormType.Payer.Name();
        OrderPaymentFormType.Payer.Name.Surname surname = new OrderPaymentFormType.Payer.Name.Surname();
        surname.setValue(employee.getName().getSurname());
        name.setSurname(surname);
        OrderPaymentFormType.Payer.Name.Given givenName = new OrderPaymentFormType.Payer.Name.Given();
        givenName.setValue(employee.getName().getFirstName());
        name.getGiven().add(givenName);
        payer.setName(name);
        payer.setContacts(buildContacts(employee));
        orderPaymentFormType.setPayer(payer);

        orderPaymentFormTypeList.add(orderPaymentFormType);
        return  orderPaymentFormTypeList;
    }
}
