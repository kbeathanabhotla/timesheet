package com.timesheet.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.timesheet.domain.ExceptionBean;

public class ValidationException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public ValidationException(ExceptionBean exceptionBean) {
		super(Response.status(422).entity(exceptionBean).build());
	}
}
