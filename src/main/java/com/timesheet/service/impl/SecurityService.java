package com.timesheet.service.impl;

import org.springframework.stereotype.Service;

import com.timesheet.domain.Login;
import com.timesheet.domain.Person;
import com.timesheet.service.ISecurityService;

@Service
public class SecurityService implements ISecurityService {

	@Override
	public Person signin(Login login) {
		
		return null;
	}

	@Override
	public void signout(String authToken) {
		
	}

	@Override
	public void forgotPassword(String authToken) {
		
	}
}
