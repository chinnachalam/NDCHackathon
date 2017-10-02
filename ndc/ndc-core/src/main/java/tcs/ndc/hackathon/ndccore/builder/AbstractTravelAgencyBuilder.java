package tcs.ndc.hackathon.ndccore.builder;

import java.util.List;
import tcs.ndc.hackathon.ndccore.builder.element.PartyBuilder;
import org.iata.ndc.schema.*;

/**
 *
 */
abstract class AbstractTravelAgencyBuilder<T> implements Buildable<T> {

    private final ObjectFactory factory;

    private final TravelersTravelerBuilder travelerBuilder;

    private MsgPartiesType.Sender sender;

    public AbstractTravelAgencyBuilder() {
        this.factory = new ObjectFactory();
        travelerBuilder = new TravelersTravelerBuilder();
        clear();
    }

    /**
     * Re-initializes builder to empty state.
     */
    public final void clear() {
        sender = null;
        doInitialize();
    }

    /**
     * Do some specific tasks in a child builder. It is not necessary to do
     * anything at all.
     */
    protected abstract void doInitialize();

    protected ObjectFactory getFactory() {
        return factory;
    }

    protected MsgPartiesType.Sender getSender() {
        //if the field is null create a empty sender to avoid null
        if(sender == null) {
            sender = factory.createMsgPartiesTypeSender();
            TravelAgencySenderType retTravelAgencySender = factory.createTravelAgencySenderType();
            retTravelAgencySender.setName("TEST_AGENCY");
            retTravelAgencySender.setAgencyID(new AgencyIDType());
            retTravelAgencySender.getAgencyID().setValue("20200154");
            retTravelAgencySender.setIATANumber("00010080");
            sender.setTravelAgencySender(retTravelAgencySender);
        }
        return sender;
    }

    protected TravelersTravelerBuilder getTravelerBuilder() {
        return travelerBuilder;
    }

    protected List<TravelersTraveler> buildTravelers() {
        return travelerBuilder.build();
    }
    
     /**
     * Sets default values.
     */
    protected void setDefaults() {
        travelerBuilder.setDefaults();
    }

    /**
     * Adds anonymous traveler of type {@link tcs.ndc.hackathon.ndccore.builder.Traveler} to traveler list.<br>
     * <strong>Note:</strong> if this type of traveler already exists,
     * increments the count for this type of traveler.
     *
     * @param traveler type of traveler
     * @return current builder instance
     */
    public AbstractTravelAgencyBuilder<T> addAnonymousTraveler(tcs.ndc.hackathon.ndccore.builder.Traveler traveler) {
        return addAnonymousTravelers(traveler, 1);
    }

    /**
     * Adds multiple anonymous travelers of type {@link tcs.ndc.hackathon.ndccore.builder.Traveler} to traveler
     * list.<br>
     * <strong>Note:</strong> if this type of traveler already exists,
     * increments the count for this type of traveler.
     *
     * @param traveler type of traveler
     * @param count number of travelers
     * @return current builder instance
     */
    public AbstractTravelAgencyBuilder<T> addAnonymousTravelers(Traveler traveler, int count) {
        travelerBuilder.addAnonymousTravelers(traveler, count);
        return this;
    }

    /**
     * Creates TravelAgencySender representation and sets it as request sender.
     * Second invocation will override previous value.<br>
     * If both this method and {@link #setParty(MsgPartiesType)} are called,
     * party data will be set. Sender in the party will be overridden by one
     * created in {@link #addTravelAgencySender(String, String, String)}
     *
     * <p>
     * Consider using {@link #setParty(MsgPartiesType)} with
     * {@link PartyBuilder}.
     *
     * @param name Travel agency name
     * @param iataNumber IATA number for the agency
     * @param agencyId agency ID
     * @return current builder instance
     */
    public AbstractTravelAgencyBuilder<T> addTravelAgencySender(String name, String iataNumber, String agencyId) {
        MsgPartiesType p = new PartyBuilder().setTravelAgencySender(name, iataNumber, agencyId).build();
        sender = p.getSender();
        return this;
    }

}
