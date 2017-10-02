package tcs.ndc.hackathon.ndccore.builder;

import java.util.*;

import javax.xml.datatype.*;

import tcs.ndc.hackathon.ndccore.ClientException;
import org.iata.ndc.schema.*;
import org.iata.ndc.schema.AirShopReqAttributeQueryTypeOriginDestination.CalendarDates;
import org.iata.ndc.schema.FarePreferencesType.Type;
import org.iata.ndc.schema.FlightDepartureType.AirportCode;

/**
 * This class provides a simple way to create AirShoppingRQ objects. It implements fluent interface, thus allowing to chain methods.<br>
 * Since the object returned by {@link #build build()} can be modified any further customization can be performed manually.
 */
public class AirShoppingRQBuilder extends AbstractTravelAgencyBuilder<AirShoppingRQ> {

	private AirShoppingRQ request;

	
	private MsgPartiesType party;

	private Set<String> airlines;
	private Set<String> fares;
	private Set<String> cabins;
        

	/**
	 * Creates a new instance of AirShoppingRQBuilder.
	 * A new instance can be created for each request or you can use the {@link #clear) clear()} method.<br>
	 *
	 * Defaults:<ol>
	 * <li> One adult traveler.
	 * </ol>
	 */
	public AirShoppingRQBuilder() {
	}

	/**
	 * Re-initializes builder to empty state.
	 */
    @Override
	protected void doInitialize() {
		airlines = new LinkedHashSet<String>();
		fares = new LinkedHashSet<String>();
		cabins = new LinkedHashSet<String>();

		request = Initializer.getObject(AirShoppingRQ.class);
		party = null;
	}

	/**
	 * Sets a pre-built MsgPartiesType object to the request.
	 * @param party object which represents Party node
	 * @return current builder instance
	 */
	public AirShoppingRQBuilder setParty(MsgPartiesType party) {
		this.party = party;
		return this;
	}


	
	/**
	 * Adds prebuilt {@link AirShopReqAttributeQueryTypeOriginDestination} instance to the list of OriginDestinations.
	 * @param originDestination originDestination to add
	 * @return current builder instance
	 */
	public AirShoppingRQBuilder addOriginDestination(AirShopReqAttributeQueryTypeOriginDestination originDestination) {
		request.getCoreQuery().getOriginDestinations().add(originDestination);
		return this;
	}

	/**
	 * Creates a new instance of {@link AirShopReqAttributeQueryTypeOriginDestination} using supplied data and adds it to the list of OriginDestinations.
	 * @param origin three letter airport code for the departure airport
	 * @param destination three letter airport code for the arrival airport
	 * @param date date of the flight
	 * @return current builder instance
	 */
	public AirShoppingRQBuilder addOriginDestination(String origin, String destination, Date date) {
		return addOriginDestination(origin, destination, date, 0, 0);
	}

	/**
	 * Creates a new instance of {@link AirShopReqAttributeQueryTypeOriginDestination} using supplied data and adds it to the list of OriginDestinations.
	 * This method allows to specify a period of dates when the flight date is flexible.
	 *
	 * @param origin three letter airport code for the departure airport
	 * @param destination three letter airport code for the arrival airport
	 * @param date date of the flight
	 * @param daysBefore request flights for a period of daysBefore before the specified flight date
	 * @param daysAfter request flights for a period of daysAfter after the specified flight date
	 * @return current builder instance
	 */
	public AirShoppingRQBuilder addOriginDestination(String origin, String destination, Date date, int daysBefore, int daysAfter) {
		AirShopReqAttributeQueryTypeOriginDestination originDestination = Initializer.getObject(AirShopReqAttributeQueryTypeOriginDestination.class);

		AirportCode airportCode = getFactory().createFlightDepartureTypeAirportCode();
		originDestination.getDeparture().setAirportCode(airportCode);
		originDestination.getDeparture().getAirportCode().setValue(origin);
		originDestination.getArrival().getAirportCode().setValue(destination);
		originDestination.getDeparture().setDate(getDate(date));

		if( daysBefore != 0 || daysAfter != 0) {
			CalendarDates dates = getFactory().createAirShopReqAttributeQueryTypeOriginDestinationCalendarDates();
			dates.setDaysBefore(daysBefore);
			dates.setDaysAfter(daysAfter);
			originDestination.setCalendarDates(dates);
		}

		return addOriginDestination(originDestination);
	}


	/**
	 * Adds airline preference to the set of preferred airlines.
	 * @param airlineId two letter code for the airline
	 * @return current builder instance
	 */
	public AirShoppingRQBuilder addAirlinePreference(String airlineId) {
		airlines.add(airlineId);

		return this;
	}

	/**
	 * Adds preferred fare code to the set of fare preferences.
	 * @param fareCode fare code
	 * @return current builder instance
	 */
	public AirShoppingRQBuilder addFarePreference(String fareCode) {
		fares.add(fareCode);

		return this;
	}

	/**
	 * Adds cabin preference to the set of cabin preferences
	 * @param cabinCode
	 * @return current builder instance
	 */
	public AirShoppingRQBuilder addCabinPreference(String cabinCode) {
		cabins.add(cabinCode);

		return this;
	}

	/**
	 * Builds AirShoppingRQ instance and returns it.
	 * @return constructed AirShoppingRQ instance
	 */
        @Override
	public AirShoppingRQ build() {
		setDefaults();

		addRequestAttributes();
		addDocumentNode();
		addPartyNode();
		addTravelers();

		addAirlinePreferences();
		addFarePreferences();
		addCabinPreferences();

		return request;
	}

	private void addCabinPreferences() {
		if (cabins.size() == 0) {
			return;
		}
		CabinPreferencesType cabinPreferencesType = getFactory().createCabinPreferencesType();
		for (String code: cabins) {
			CabinType cabin = getFactory().createCabinType();
			cabin.setCode(code);
			cabinPreferencesType.getCabinType().add(cabin);
		}
		org.iata.ndc.schema.AirShoppingRQ.Preference preferenceElement = request.getPreference();
		if(preferenceElement == null) {
			preferenceElement = getFactory().createAirShoppingRQPreference();
			request.setPreference(preferenceElement);
		}
		preferenceElement.setCabinPreferences(cabinPreferencesType);
	}

	private void addFarePreferences() {
		if (fares.size() == 0) {
			return;
		}
		FarePreferencesType farePreferences = getFactory().createFarePreferencesType();
		for (String code : fares) {
			Type type = getFactory().createFarePreferencesTypeType();
			type.setCode(code);
			farePreferences.getTypes().add(type);
		}
		org.iata.ndc.schema.AirShoppingRQ.Preference preferenceElement = request.getPreference();
		if(preferenceElement == null) {
			preferenceElement = getFactory().createAirShoppingRQPreference();
			request.setPreference(preferenceElement);
		}
		preferenceElement.setFarePreferences(farePreferences);
	}

	private void addAirlinePreferences() {
		if (airlines.size() == 0) {
			return;
		}
		AirlinePreferencesType airlinePreferences = getFactory().createAirlinePreferencesType();
		for (String code : airlines) {
			AirlinePreferencesType.Airline airline = getFactory().createAirlinePreferencesTypeAirline();
			AirlineID airlineID = getFactory().createAirlineID();
			airlineID.setValue(code);
			airline.setAirlineID(airlineID);
			airlinePreferences.getAirline().add(airline);
		}
		org.iata.ndc.schema.AirShoppingRQ.Preference preferenceElement = request.getPreference();
		if(preferenceElement == null) {
			preferenceElement = getFactory().createAirShoppingRQPreference();
			request.setPreference(preferenceElement);
		}
		preferenceElement.setAirlinePreferences(airlinePreferences);
	}


	private void addPartyNode() {
		if (party == null) {
			party = getFactory().createMsgPartiesType();
		}
		party.setSender(getSender());

		request.setParty(party);
	}

	private void addTravelers() {
		request.getTravelers().addAll(buildTravelers());
	}

	private void addDocumentNode() {
		MsgDocumentType document = getFactory().createMsgDocumentType();
		document.setName("NDC AirShoppingRQ Message");
		document.setReferenceVersion("1.0");
		request.setDocument(document);
	}

	private void addRequestAttributes() {
		request.setVersion(VERSION);
	}

	private static XMLGregorianCalendar getDate(Date date) {
		XMLGregorianCalendar xmlgc = null;
		DatatypeFactory df = null;
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new ClientException(e);
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		xmlgc = df.newXMLGregorianCalendar(gc);
		xmlgc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		xmlgc.setSecond(DatatypeConstants.FIELD_UNDEFINED);
		xmlgc.setMinute(DatatypeConstants.FIELD_UNDEFINED);
		xmlgc.setHour(DatatypeConstants.FIELD_UNDEFINED);
		xmlgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		return xmlgc;
	}
}
