package com.timesheet.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.timesheet.annotation.SecuredMethod;
import com.timesheet.constants.ApplicationConstants;
import com.timesheet.domain.Login;
import com.timesheet.domain.Person;
import com.timesheet.service.ISecurityService;

@Path("/security")
@Component
public class SecurityResource {

	@Autowired
	private ISecurityService securityService;
	
	@POST
	@Path("/signin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signin(Login login) {
		Person person = securityService.signin(login);
		return Response.status(Response.Status.CREATED).entity(person).build();
	}
	
	@PUT
	@Path("/forgot-password")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response forgotPassword(@HeaderParam(ApplicationConstants.AUTHORIZATION) String authToken) {
		securityService.forgotPassword(authToken);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/signout")
	@Consumes(MediaType.APPLICATION_JSON)
	@SecuredMethod
	public Response signout(@HeaderParam(ApplicationConstants.AUTHORIZATION) String authToken) {
		securityService.signout(authToken);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
