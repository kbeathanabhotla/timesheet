package com.timesheet.configuration;

import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestInterceptor {
	
	/*@Pointcut("within(com.timesheet.resources..*)")
	public void userSecurityPointcut() {
		System.out.println("executed pointcut....");
	}*/
	
	/*@HeaderParam("Authorization") 
	private String authToken;
	
	@Context
	private HttpHeaders httpHeaders;*/
	
	
	@Pointcut("within(@com.timesheet.annotation.SecuredResource *)")
	public void securedResource() {}
	
	@Pointcut("@annotation(com.timesheet.annotation.OpenMethod)")
	public void nonSecuredMethod() {}
	
	@Pointcut("@annotation(com.timesheet.annotation.SecuredMethod)")
	public void securedMethod() {}
	
	@Pointcut("@annotation(com.timesheet.annotation.AdminMethod)")
	public void adminMethod() {}
	
	@Pointcut("execution(public * *(..))")
	public void publicMethod() {}
	
	@Before("(securedResource() || securedMethod()) && publicMethod() && !nonSecuredMethod() && !adminMethod()")
	public void checkForAuthHeader() {
		
		HttpServletRequest requestAttributes = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		Enumeration<String> enumu = requestAttributes.getHeaderNames();
		
		while(enumu.hasMoreElements()) {
			String headerName = enumu.nextElement();
			System.out.println(headerName+"	"+requestAttributes.getHeader(headerName));
		}
		
		/*for(Entry<String, List<String>> header : httpHeaders.getRequestHeaders().entrySet()) {
			System.out.println(header.getKey()+"	"+header.getValue());
		}*/
		
		/*ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

		Enumeration<String> enumu = attributes.getRequest().getHeaderNames();
		
		while(enumu.hasMoreElements()) {
			String name = enumu.nextElement();
			System.out.println(name+"		"+attributes.getRequest().getHeader(name));
		}*/
		//System.out.println(httpHeaders);
		System.out.println(" executed auth header AOP method... ");
	}
	
	@Around("adminMethod()")
	public void checkUserIsAdmin(ProceedingJoinPoint proceedingJoinPoint) {
		
		System.out.println("admin AOP executed...");
		
	}
	
}