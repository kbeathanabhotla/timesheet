package com.timesheet.service;

import java.util.List;

import com.timesheet.domain.Login;
import com.timesheet.domain.Pagination;
import com.timesheet.domain.hibernate.Person;

public interface IUserService {

	void createPerson(Person person);

	void verifyPerson(Login login);
	
	List<Person> getAllPeople(Pagination pagination);

	Person getPerson(int personId);

	void updatePerson(Person person);

	void deletePerson(int personId);

}
