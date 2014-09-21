package com.timesheet.util;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.timesheet.domain.ExceptionBean;

@Component
public class ExceptionHandler {

	@Resource(name="httpStatusCodes")
	private Properties httpStatusCodes;
	
	private static Properties statuses;
	
	@PostConstruct
	private void init() {
		statuses = httpStatusCodes;
	}
	
	public static ExceptionBean getExceptionbean(int exceptionConstant, String exceptionInfo) {
	
		ExceptionBean exceptionBean = new ExceptionBean();
		exceptionBean.setCode(exceptionConstant);
		exceptionBean.setErrorInfo(exceptionInfo);
		if(statuses.get(exceptionConstant) != null) {
			exceptionBean.setHttpStatusCode(Integer.parseInt(statuses.get(exceptionConstant).toString()));
		}
		
		return exceptionBean;
	}
}
