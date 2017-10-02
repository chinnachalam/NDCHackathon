package tcs.ndc.hackathon.ndccore.builder;

import org.iata.ndc.schema.*;

public class ServiceListRQBuilder implements Buildable<ServiceListRQ>{
	private static final ObjectFactory factory = new ObjectFactory();

	private ServiceListRQ request;

	/**
	 * Creates a new instance of ServiceListRQBuilder.
	 * A new instance can be created for each request or you can use the {@link #clear() clear()} method.<br>
	 */
	public ServiceListRQBuilder() {
		clear();
	}

	/**
	 * Re-initializes builder to empty state.
	 */
	public void clear() {
		request = Initializer.getObject(ServiceListRQ.class);
	}

	/**
	 * Sets a pre-built MsgPartiesType object to the request.
	 * @param party object which represents Party node
	 * @return current builder instance
	 */
	public ServiceListRQBuilder setParty(MsgPartiesType party) {
		request.setParty(party);
		return this;
	}



	public ServiceListRQBuilder setOrderID(String orderID, String owner) {
		ServiceListRQ.Query serviceListRQQuery = factory.createServiceListRQQuery();
		OrderIDType orderIdType = factory.createOrderIDType();
		orderIdType.setValue(orderID);
		orderIdType.setOwner(owner);
		serviceListRQQuery.setOrderID(orderIdType);
		request.setQuery(serviceListRQQuery);
		return this;
	}

        @Override
	public ServiceListRQ build() {
		request.setVersion(VERSION);
		addDocumentNode();
		return request;
	}

	private void addDocumentNode() {
		MsgDocumentType document = factory.createMsgDocumentType();
		document.setName("NDC ServiceListRQ Message");
		document.setReferenceVersion("1.0");
		request.setDocument(document);
	}

}