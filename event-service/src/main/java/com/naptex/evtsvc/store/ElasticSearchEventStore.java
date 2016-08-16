package com.naptex.evtsvc.store;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.naptex.evtsvc.model.Event;

@Component /* (value="esEventStore") */
@Primary
public class ElasticSearchEventStore implements IEventStore
{
	final static Logger logger = Logger
			.getLogger(ElasticSearchEventStore.class);

	private final Client client;

	@Inject
	public ElasticSearchEventStore(@Value("${es.host}") String host,
			@Value("${es.port}") String port)
			throws NumberFormatException, UnknownHostException
	{
		logger.info("Connect to " + host + ":" + port);

		Client client = TransportClient.builder().build().addTransportAddress(
				new InetSocketTransportAddress(InetAddress.getByName(host),
						Integer.parseInt(port)));
		this.client = client;
	}

	public void storeEvent(Event event)
	{
		logger.info("Store event in Elastic Search");
		String json = event.toJson();
		IndexResponse response = client.prepareIndex("event_svc", "event")
				.setSource(json).get();
		logger.info(response);
	}

	public Iterator<Event> getEvents()
	{
		logger.info("Get events from Elastic Search");
		return null;
	}

}
