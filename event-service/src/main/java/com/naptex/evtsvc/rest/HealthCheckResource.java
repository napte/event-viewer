package com.naptex.evtsvc.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.naptex.evtsvc.model.Event;
import com.naptex.evtsvc.model.Level;
import com.naptex.evtsvc.service.EventProcessorService;

@Path("/health")
@Component
@Scope("request")
public class HealthCheckResource
{
	final static Logger logger = Logger.getLogger(HealthCheckResource.class);

	private final EventProcessorService service;

	@Inject
	public HealthCheckResource(EventProcessorService service)
	{
		this.service = service;
	}

	@GET
	@Produces("text/html")
	public Response getHealthCheckPage()
	{
		Event healthCheckEvent = new Event();
		healthCheckEvent.setLevel(Level.DEBUG);
		healthCheckEvent.setMessage("Health Check");
		healthCheckEvent.setOrigin("SYS");
		healthCheckEvent.setTime(new Date());
		service.publishEvent(healthCheckEvent);

		String output = "<h1>Health Check</h1>"
				+ "<p>RESTful Service is running ... <br>Ping @ "
				+ new Date().toString() + "</p><br>";
		return Response.status(200).entity(output).build();
	}
}
