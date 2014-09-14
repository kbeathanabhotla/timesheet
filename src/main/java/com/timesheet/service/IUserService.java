package com.timesheet.service;

import java.util.List;

import com.timesheet.domain.Pagination;
import com.timesheet.domain.Person;

public interface IUserService {

	void createUser(Person person);

	List<Person> getAllUsers(Pagination pagination);

	Person getUser(int userId);

	void updatePerson(int userId, Person person);

	void deletePerson(int userId);

}
