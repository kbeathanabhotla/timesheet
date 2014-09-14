package com.timesheet.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.timesheet.annotation.AdminMethod;
import com.timesheet.annotation.OpenMethod;
import com.timesheet.annotation.SecuredResource;
import com.timesheet.domain.Pagination;
import com.timesheet.domain.Person;
import com.timesheet.service.IUserService;

@Path("users")
@Component
@SecuredResource
public class UserResource {

	@Autowired
	private IUserService userService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@OpenMethod
	public Response createUser(Person person) {
		person.setAdmin(false);
		userService.createUser(person);
		return Response.status(Response.Status.CREATED).entity(person).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(@QueryParam("offset") int offset, @QueryParam("count") int count) {
		List<Person> people = userService.getAllUsers(new Pagination(offset, count));
		return Response.status(Response.Status.OK).entity(people).build();
	}
	
	@GET
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("userId") int userId) {
		Person person = userService.getUser(userId);
		return Response.status(Response.Status.OK).entity(person).build();
	}
	
	@PUT
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("userId") int userId, Person person) {
		userService.updatePerson(userId,person);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminMethod
	public Response deletePerson(@PathParam("userId") int userId) {
		userService.deletePerson(userId);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
