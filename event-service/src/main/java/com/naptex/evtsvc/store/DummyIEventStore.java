package com.naptex.evtsvc.store;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.naptex.evtsvc.model.Event;

@Component
public class DummyIEventStore implements IEventStore
{
	final static Logger logger = Logger.getLogger(DummyIEventStore.class);

	@Override
	public String storeEvent(Event event)
	{
		logger.info(event);
		return null;
	}

	@Override
	public Iterator<Event> getEvents()
	{
		logger.info("Get events");
		return null;
	}

	@Override
	public Event getEvent(String id)
	{
		logger.info("Get event with id " + id);
		return null;
	}

}
