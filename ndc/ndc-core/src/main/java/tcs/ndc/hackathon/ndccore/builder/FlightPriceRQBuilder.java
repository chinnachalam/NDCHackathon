package tcs.ndc.hackathon.ndccore.builder;

import org.iata.ndc.schema.FlightPriceRQ;
import org.iata.ndc.schema.MsgPartiesType;

/**
 *
 * Builder for the {@link org.iata.ndc.schema.FlightPriceRQ}
 */
public class FlightPriceRQBuilder extends AbstractTravelAgencyBuilder<FlightPriceRQ> {

    private static final String MESSAGE = "NDC FlightPriceRQ Message";
    

    private FlightPriceRQ request;

    @Override
    public void doInitialize() {
        request = Initializer.getObject(FlightPriceRQ.class);
    }

    public FlightPriceRQBuilder setParty(MsgPartiesType party) {
        request.setParty(party);

        return this;
    }

    @Override
    public FlightPriceRQ build() {
        request.setVersion(VERSION);
        
        setDefaults();

        addTravelers();

        return request;
    }

    @Override
    protected void setDefaults() {
        super.setDefaults();
        request.setDocument(MessageDocumentFactory.create(MESSAGE));
        if(request.getParty() == null){
            MsgPartiesType party = getFactory().createMsgPartiesType();
            party.setSender(getSender());
            request.setParty(party);
        }
    }

    private void addTravelers() {
        request.getTravelers().addAll(buildTravelers());
    }

}
