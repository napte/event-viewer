package com.naptex.evtsvc.store;

import java.util.Iterator;

import com.naptex.evtsvc.model.Event;

public interface IEventStore
{
	public String storeEvent(Event event);

	public Iterator<Event> getEvents();

	public Event getEvent(String id);
}
