package com.naptex.evtsvc.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/health")
@Component
@Scope("request")
public class HealthCheckResource
{
	final static Logger logger = Logger.getLogger(HealthCheckResource.class);

	@GET
	@Produces("text/html")
	public Response getHealthCheckPage()
	{
		String output = "<h1>Health Check</h1>"
				+ "<p>RESTful Service is running ... <br>Ping @ "
				+ new Date().toString() + "</p><br>";
		return Response.status(200).entity(output).build();
	}
}
