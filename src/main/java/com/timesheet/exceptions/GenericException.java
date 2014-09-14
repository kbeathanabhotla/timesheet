package com.timesheet.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.timesheet.domain.ExceptionBean;

public class GenericException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public GenericException(ExceptionBean exceptionBean) {
		super(Response.status(Response.Status.fromStatusCode(exceptionBean.getHttpStatusCode())).entity(exceptionBean).build());
	}
	
}
