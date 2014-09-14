package com.timesheet.resources;

import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.timesheet.annotation.SecuredResource;

@Path("/worklog")
@Component
@SecuredResource
public class WorklogResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWorklog(@QueryParam("offset") int offset,@QueryParam("count") int count) {
		
		return Response.status(Response.Status.OK).entity("{\"Message\":\"OK\"}").build();
	}
	
}
