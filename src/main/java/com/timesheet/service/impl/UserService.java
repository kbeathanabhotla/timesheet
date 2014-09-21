package com.timesheet.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.timesheet.domain.Login;
import com.timesheet.domain.Pagination;
import com.timesheet.domain.hibernate.Person;
import com.timesheet.service.IUserService;

@Service
public class UserService implements IUserService {

	@Override
	public void createPerson(Person person) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void verifyPerson(Login login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Person> getAllPeople(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPerson(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(int personId) {
		// TODO Auto-generated method stub
		
	}

	

}
