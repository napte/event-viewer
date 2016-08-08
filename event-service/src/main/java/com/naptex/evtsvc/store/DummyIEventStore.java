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
	public void storeEvent(Event event)
	{
		logger.info(event);
	}

	@Override
	public Iterator<Event> getEvents()
	{
		logger.info("Get events");
		return null;
	}

}
