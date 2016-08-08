package com.naptex.evtsvc.store;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.naptex.evtsvc.model.Event;

@Component /* (value="esEventStore") */
@Primary
public class ElasticSearchEventStore implements IEventStore
{
	final static Logger logger = Logger
			.getLogger(ElasticSearchEventStore.class);

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
