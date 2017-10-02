package tcs.ndc.hackathon.ndccore.builder;

import org.iata.ndc.schema.MsgDocumentType;
import org.iata.ndc.schema.ObjectFactory;

/**
 *
 */
final class MessageDocumentFactory {
    private static final String REF = "1.0";

    private static final ObjectFactory FACTORY = new ObjectFactory();

    static MsgDocumentType create(String name) {
        MsgDocumentType document = FACTORY.createMsgDocumentType();
        document.setName(name);
        document.setReferenceVersion(REF);
        return document;

    }
    
}
