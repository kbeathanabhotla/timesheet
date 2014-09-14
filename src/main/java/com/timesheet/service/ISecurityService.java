package com.timesheet.service;

import com.timesheet.domain.Login;
import com.timesheet.domain.Person;

public interface ISecurityService {

	Person signin(Login login);

	void signout(String authToken);

	void forgotPassword(String authToken);

}
