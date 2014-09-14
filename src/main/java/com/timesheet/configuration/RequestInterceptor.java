package com.timesheet.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestInterceptor {
	
	/*@Pointcut("within(com.timesheet.resources..*)")
	public void userSecurityPointcut() {
		System.out.println("executed pointcut....");
	}*/
	
	@Pointcut("within(@com.timesheet.annotation.SecuredResource *)")
	public void securedResource() {}
	
	@Pointcut("@annotation(com.timesheet.annotation.OpenMethod)")
	public void nonSecuredMethod() {}
	
	@Pointcut("@annotation(com.timesheet.annotation.SecuredMethod)")
	public void securedMethod() {}
	
	@Pointcut("execution(public * *(..))")
	public void publicMethod() {}
	
	@Before("(securedResource() || securedMethod()) && publicMethod() && !nonSecuredMethod()")
	public void checkForAuthHeader() {
		
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		/*for(Entry<String, List<String>> header : httpHeaders.getRequestHeaders().entrySet()) {
			System.out.println(header.getKey()+"	"+header.getValue());
		}*/
		
		/*ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

		Enumeration<String> enumu = attributes.getRequest().getHeaderNames();
		
		while(enumu.hasMoreElements()) {
			String name = enumu.nextElement();
			System.out.println(name+"		"+attributes.getRequest().getHeader(name));
		}*/
		
		System.out.println(" executed auth header... ");
	}
	
}