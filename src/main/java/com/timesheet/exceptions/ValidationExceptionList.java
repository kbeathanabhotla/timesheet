package com.timesheet.exceptions;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.timesheet.domain.ExceptionBean;

public class ValidationExceptionList extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public ValidationExceptionList(List<ExceptionBean> exceptionBeans) {
		super(Response.status(422).entity(exceptionBeans).build());
	}
}
