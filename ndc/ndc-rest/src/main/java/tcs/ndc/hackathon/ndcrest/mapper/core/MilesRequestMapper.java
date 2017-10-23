package tcs.ndc.hackathon.ndcrest.mapper.core;

import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndcrest.model.miles.Flight;
import tcs.ndc.hackathon.ndcrest.model.miles.Leg;
import tcs.ndc.hackathon.ndcrest.model.miles.MilesRequest;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Offer;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Segment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MilesRequestMapper {

    public MilesRequest map(Offer offer) {
        MilesRequest milesRequest = new MilesRequest();
        List<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();
        Leg leg = new Leg();
        List<tcs.ndc.hackathon.ndcrest.model.miles.Segment> segments = new ArrayList<>();
        for (Segment segment : offer.getConnection().getSegments()) {
            tcs.ndc.hackathon.ndcrest.model.miles.Segment milesSegment = new tcs.ndc.hackathon.ndcrest.model.miles.Segment();
            milesSegment.setDepartureAirport(segment.getDeparture().getAirportCode());
            milesSegment.setArrivalAirport(segment.getArrival().getAirportCode());
            milesSegment.setDepartureDate(segment.getDeparture().getDate() + "T" + segment.getDeparture().getTime());
            milesSegment.setBookingClass(offer.getConnection().getCabinCode());
            milesSegment.setFareBasisCode(offer.getConnection().getFareBaseCode());
            milesSegment.setMarketingAirline("SK");
            milesSegment.setOperatingAirline("SK");
            milesSegment.setFlightNumber(segment.getMarketingAirline().substring(2, segment.getMarketingAirline().length()));

            segments.add(milesSegment);
        }
        leg.setSegments(segments);
        flight.setLegs(Arrays.asList(leg));
        flights.add(flight);

        milesRequest.setFlights(flights);

        return milesRequest;
    }
}
