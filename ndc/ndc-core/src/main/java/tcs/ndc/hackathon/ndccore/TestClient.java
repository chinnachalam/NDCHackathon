package tcs.ndc.hackathon.ndccore;

import tcs.ndc.hackathon.ndccore.builder.AirShoppingRQBuilder;
import org.iata.ndc.schema.AirShopReqParamsType;
import org.iata.ndc.schema.AirShoppingRQ;
import org.iata.ndc.schema.AirShoppingRS;
import org.iata.ndc.schema.CurrCode;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class TestClient {
    public static void main(String args[]) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(GregorianCalendar.DAY_OF_MONTH, 25);
        gc.set(GregorianCalendar.MONTH, GregorianCalendar.DECEMBER);
        gc.set(GregorianCalendar.YEAR, 2017);

        AirShoppingRQBuilder builder = new AirShoppingRQBuilder();
        builder.addTravelAgencySender("Test agency", "98417900", "test agent");
        builder.addOriginDestination("ARN", "LHR", gc.getTime());
        builder.addAirlinePreference("C9");
        builder.addCabinPreference("M");

        AirShoppingRQ request = builder.build();

       // NDCConsumer client = new NDCConsumer("http://iata.api.mashery.com/athena/ndc162api", "2be3rbjvuj32peygqcwp6fy5");
        NDCConsumer client = new NDCConsumer("http://iata.api.mashery.com/kronos/ndc162api", "2be3rbjvuj32peygqcwp6fy5");
        AirShoppingRS response = null;

        LinkedList<CurrCode> curr = new LinkedList<CurrCode>();

        CurrCode ccode = new CurrCode();
        ccode.setId("USD");
        ccode.setValue("USD");
        curr.add(ccode);

        AirShopReqParamsType t = new AirShopReqParamsType();
        t.setCurrCodes(curr);

        request.setParameters(t);

        System.out.println("Making the call");

        try {
            response = client.airShopping(request);

            try {
                System.out.println("Document Name: " + response.getDocument().getName());
            }
            catch (Exception e) {
             e.printStackTrace();
            }
        }
        catch (IOException e) {
           e.printStackTrace();
        }
    }
}