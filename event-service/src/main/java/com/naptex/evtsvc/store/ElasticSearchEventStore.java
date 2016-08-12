package com.naptex.evtsvc.store;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.log4j.Logger;
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

	private final String host;
	private final String port;

	@Inject
	public ElasticSearchEventStore(@Value("${es.host}") String host,
			@Value("${es.port}") String port)
	{
		this.host = host;
		this.port = port;

		logger.info("Connect to " + this.host + ":" + this.port);
	}

	public void storeEvent(Event event)
	{
		logger.info("Store event in Elastic Search");
	}

	public Iterator<Event> getEvents()
	{
		logger.info("Get events from Elastic Search");
		return null;
	}

}
