package com.naptex.evtsvc.rest;

import java.io.IOException;
import java.util.Iterator;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.naptex.evtsvc.model.Event;
import com.naptex.evtsvc.service.EventProcessorService;
import com.naptex.evtsvc.util.JsonFormatter;

@Path("/events")
@Component
@Scope("request")
public class EventResource
{
	final static Logger logger = Logger.getLogger(EventResource.class);

	private final EventProcessorService service;
	private final JsonFormatter jsonFormatter;

	@Inject
	public EventResource(EventProcessorService service,
			JsonFormatter jsonFormatter)
	{
		this.service = service;
		this.jsonFormatter = jsonFormatter;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEvents() throws IOException
	{
		logger.info("Get all events from ES");

		Iterator<Event> iterEvents = service.getAllEvents();
		String json = jsonFormatter.getJson(iterEvents);

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvent(@PathParam("id") String id) throws IOException
	{
		logger.info("Get event with id " + id);

		Event evt = service.getEvent(id);
		String json = jsonFormatter.getJson(evt);

		return Response.status(Status.OK).entity(json).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response publishEvent()
	{
		// TODO : Accept Event json and publish event
		return null;
	}

}
