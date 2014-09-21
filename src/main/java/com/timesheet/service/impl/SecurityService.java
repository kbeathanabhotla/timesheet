package com.timesheet.service.impl;

import org.springframework.stereotype.Service;

import com.timesheet.domain.Login;
import com.timesheet.domain.hibernate.Person;
import com.timesheet.service.ISecurityService;
import com.timesheet.util.CacheUtil;

@Service
public class SecurityService implements ISecurityService {

	@Override
	public Person signin(Login login) {
		
		return null;
	}

	@Override
	public void signout(String authToken) {
		CacheUtil.evictPersonFromCache(authToken);
	}

	@Override
	public void forgotPassword(Login login) {
		
	}
}
