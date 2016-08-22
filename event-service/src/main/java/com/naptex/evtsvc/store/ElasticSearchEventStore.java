package com.naptex.evtsvc.store;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.naptex.evtsvc.model.Event;

@Component /* (value="esEventStore") */
@Primary
public class ElasticSearchEventStore implements IEventStore
{
	private static final int SEARCH_LIMIT = 1000;
	private static final String TYPE_EVENT = "event";
	private static final String INDEX_EVENT_SVC = "event_svc";

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

	public String storeEvent(Event event)
	{
		logger.info("Store event in Elastic Search");
		String json = event.toJson();
		IndexResponse response = client
				.prepareIndex(INDEX_EVENT_SVC, TYPE_EVENT).setSource(json)
				.get();
		String eventId = response.getId();
		logger.info("Event id : " + eventId);
		return eventId;
	}

	public Iterator<Event> getEvents()
	{
		logger.info("Get events from Elastic Search");
		List<Event> events = new ArrayList<>();

		SearchResponse response = client.prepareSearch(INDEX_EVENT_SVC)
				.setTypes(TYPE_EVENT).setSize(SEARCH_LIMIT).execute()
				.actionGet();

		for (SearchHit hit : response.getHits())
		{
			String eventJson = hit.getSourceAsString();
			Event evt = Event.fromString(eventJson);
			evt.setId(hit.getId());
			events.add(evt);
		}

		return events.iterator();
	}

	@Override
	public Event getEvent(String id)
	{
		logger.info("Get event with id " + id);

		GetResponse response = client.prepareGet().setIndex(INDEX_EVENT_SVC)
				.setType(TYPE_EVENT).setId(id).setOperationThreaded(false)
				.get();
		String eventJson = response.getSourceAsString();
		Event evt = Event.fromString(eventJson);
		evt.setId(response.getId());
		return evt;
	}

	public static void main(String[] args)
			throws NumberFormatException, UnknownHostException
	{
		ElasticSearchEventStore store = new ElasticSearchEventStore("localhost",
				"9300");
		System.out.println(store.getEvent("AVaTniY_ppQVtl0vvelY").toJson());
	}

}
