package test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.timesheet.domain.hibernate.Person;
import com.timesheet.domain.hibernate.Worklog;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = applicationContext.getBean("sessionFactory",SessionFactory.class);
		
		/*Worklog worklog = new Worklog();
		worklog.setHoursSpent(6.0f);
		worklog.setPersonId(1l);
		worklog.setActivityId(1l);
		worklog.setActivityTypeId(1l);
		worklog.setClientId(1l);
		worklog.setDate(new Date());
		worklog.setModuleId(1l);
		worklog.setStatusId(1l);
		worklog.setTaskDescription("Test description");
		worklog.setTaskName("test task Name");*/
		
		/*Person person = new Person();
		person.setAdmin(false);
		person.setEmail("email@email.com");
		person.setEmployeeId(1234);
		person.setName("Sai 2");
		person.setTeamId(1);*/
		
		
		
		Session session = sessionFactory.openSession();
		Person p = (Person)session.get(Person.class, 1);
		
		System.out.println(p);
		
		//Transaction transaction = session.beginTransaction();
		//session.save(person);
		//transaction.commit();
		session.close();
		
		
		
	}

}
