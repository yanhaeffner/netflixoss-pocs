package com.github.diegopacheco.sandbox.java.netflixoss.dyno.msa.rest;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/cache")
public class SimpleCacheResource {

	private static final Logger logger = LoggerFactory.getLogger(SimpleCacheResource.class);
	
	private DynoManager dyno;
	
	@Inject
	public SimpleCacheResource( DynoManager dyno) {
		this.dyno = dyno;
	}
	
	@GET
	@Path("set/{k}/{v}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response set(@PathParam("k") String k,@PathParam("v") String v) {
		try {
			dyno.getClient().set(k, v);
			//dyno.getShadowClient().set(k, v);
			return Response.ok("200").build();
		} catch (Exception e) {
			logger.error("Error creating json response.", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("get/{k}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("k") String k) {
		try {
			return Response.ok( dyno.getClient().get(k) ).build();
		} catch (Exception e) {
			logger.error("Error creating json response.", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}


}
