package tcs.ndc.hackathon.ndcrest.mapper.core;

import org.iata.ndc.schema.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import java.util.Set;

@Component
public class CommonMapper {

    private static final ObjectFactory objectFactory = new ObjectFactory();

    private Set<String> airlines;

    //----- Document Section -------
    public MsgDocumentType buildDocument(){
        MsgDocumentType document = new MsgDocumentType();
        document.setReferenceVersion("1.0");
        return document;
    }

    //----- Party Section -------
    public MsgPartiesType buildParty() {
        MsgPartiesType party = new MsgPartiesType();
        party.setSender(buildSender());
        party.setRecipient(buildRecipient());
        return  party;
    }


    //----- Party/Sender Section ------
    public MsgPartiesType.Sender buildSender() {
        MsgPartiesType.Sender sender = new MsgPartiesType.Sender();
        //Travel Agency Sender
        TravelAgencySenderType travelAgentSender = new TravelAgencySenderType();
        travelAgentSender.setName("TCSNDCTestAgent");
        travelAgentSender.setIATANumber("98417900");
        AgencyIDType agencyIDType = objectFactory.createAgencyIDType();
        agencyIDType.setValue("TCSNDCTestAgent");
        travelAgentSender.setAgencyID(agencyIDType);
        sender.setTravelAgencySender(travelAgentSender);
        return sender;
    }

    public JAXBElement<MsgPartiesType.Recipient> buildRecipient() {
        JAXBElement<MsgPartiesType.Recipient> recipientJAXBElement = null;
        MsgPartiesType.Recipient recipient = new MsgPartiesType.Recipient();

        //ORA Recipient
        ORAAirlineRecipientType oraReciever = new ORAAirlineRecipientType();
        AirlineID airlineID = objectFactory.createAirlineID();
        airlineID.setValue("C9");
        oraReciever.setAirlineID(airlineID);
        recipient.setORARecipient(oraReciever);
        //recipientJAXBElement.setValue(recipient);
        return recipientJAXBElement;
    }


}
