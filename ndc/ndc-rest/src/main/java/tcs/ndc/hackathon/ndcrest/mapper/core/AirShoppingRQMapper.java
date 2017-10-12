package tcs.ndc.hackathon.ndcrest.mapper.core;

import org.iata.ndc.schema.AirShopReqParamsType;
import org.iata.ndc.schema.AirShoppingRQ;
import org.iata.ndc.schema.CurrCode;
import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndccore.builder.AirShoppingRQBuilder;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Connection;
import tcs.ndc.hackathon.ndcrest.model.offer.request.Offer;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.LinkedList;

@Component
public class AirShoppingRQMapper {

    public AirShoppingRQ map(Offer offer) {
        AirShoppingRQBuilder builder = new AirShoppingRQBuilder();
        builder.addTravelAgencySender("TCSNDCTestAgent", "98417900", "TCSNDCTestAgent");
        for (Connection connection : offer.getConnections()) {
            LocalDate departureDate = connection.getDepartureDate();
            GregorianCalendar gc = new GregorianCalendar();
            gc.set(GregorianCalendar.DAY_OF_MONTH, departureDate.getDayOfMonth());
            gc.set(GregorianCalendar.MONTH, departureDate.getMonthValue());
            gc.set(GregorianCalendar.YEAR, departureDate.getYear());

            builder.addOriginDestination(connection.getOrigin(), connection.getDestination(), gc.getTime());
        }
        builder.addAirlinePreference(offer.getAirlinePreference());
        builder.addCabinPreference(offer.getCabin().getValue());

        LinkedList<CurrCode> currCodes = new LinkedList<CurrCode>();
        CurrCode currCode = new CurrCode();
        currCode.setId(offer.getCurrency());
        currCode.setValue(offer.getCurrency());
        currCodes.add(currCode);

        AirShopReqParamsType airShopReqParamsType = new AirShopReqParamsType();
        airShopReqParamsType.setCurrCodes(currCodes);

        AirShoppingRQ request = builder.build();

        request.setParameters(airShopReqParamsType);

        return request;
    }
}
