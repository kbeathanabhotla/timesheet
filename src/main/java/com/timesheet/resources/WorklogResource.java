package com.timesheet.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.timesheet.annotation.SecuredResource;
import com.timesheet.constants.ApplicationConstants;
import com.timesheet.domain.hibernate.Worklog;

@Path("/worklog")
@Component
@SecuredResource
public class WorklogResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllWorklog(
			@HeaderParam(ApplicationConstants.AUTHORIZATION) String authToken,
			@DefaultValue("1") @QueryParam(ApplicationConstants.OFFSET) int offset,
			@DefaultValue("50") @QueryParam(ApplicationConstants.COUNT) int count) {

		return Response.status(Response.Status.OK)
				.entity("{\"Message\":\"OK\"}").build();
	}
	
	@GET
	@Path("{logId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorklog(
			@HeaderParam(ApplicationConstants.AUTHORIZATION) String authToken,
			@PathParam("logId") int logId) {

		return Response.status(Response.Status.OK)
				.entity("{\"Message\":\"OK\"}").build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addWorklog(
			@HeaderParam(ApplicationConstants.AUTHORIZATION) String authToken,
			Worklog worklog) {

		return Response.status(Response.Status.OK)
				.entity("{\"Message\":\"OK\"}").build();
	}
	
	@PUT
	@Path("{logId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateWorklog(
			@HeaderParam(ApplicationConstants.AUTHORIZATION) String authToken,
			@PathParam("logId") int logId, Worklog worklog) {

		return Response.status(Response.Status.OK)
				.entity("{\"Message\":\"OK\"}").build();
	}
	
	@DELETE
	@Path("{logId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteWorklog(@HeaderParam(ApplicationConstants.AUTHORIZATION) String authToken,
									@PathParam("logId") int logId) {

		return Response.status(Response.Status.OK)
				.entity("{\"Message\":\"OK\"}").build();
	}
	
	
	
	

}
