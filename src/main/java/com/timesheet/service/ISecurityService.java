package com.timesheet.service;

import com.timesheet.domain.Login;
import com.timesheet.domain.hibernate.Person;

public interface ISecurityService {

	Person signin(Login login);

	void signout(String authToken);

	void forgotPassword(Login login);

}
