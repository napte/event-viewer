package com.naptex.evtsvc.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.naptex.evtsvc.model.Event;
import com.naptex.evtsvc.store.IEventStore;

@Service
public class EventProcessorService
{
	final static Logger logger = Logger.getLogger(EventProcessorService.class);

	private final IEventStore eventStore;

	@Inject
	public EventProcessorService(IEventStore eventStore)
	{
		this.eventStore = eventStore;
	}

	public void publishEvent(Event event)
	{
		logger.debug("Publish " + event);
		eventStore.storeEvent(event);
	}
}
