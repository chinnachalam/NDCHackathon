package tcs.ndc.hackathon.ndccore;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.AbstractResponseHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

class UnmarshallingResponseHandler<T> extends AbstractResponseHandler<T> implements ResponseHandler<T> {

	@Override
	public T handleEntity(HttpEntity entity) throws IOException {
		return unmarshallRequest(entity.getContent());
	}

	@SuppressWarnings("unchecked")
	private T unmarshallRequest(InputStream response) {
		try {
			JAXBContext context = NDCConsumer.getJaxbContext();
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(response);
		} catch (JAXBException e) {
			throw new ClientException(e);
		}
	}

}
