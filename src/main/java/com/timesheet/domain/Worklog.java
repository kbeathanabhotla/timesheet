package com.timesheet.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Worklog")
public class Worklog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="person",referencedColumnName="id",insertable=false,updatable=false,nullable=false)
	private Person person;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="client",referencedColumnName="id",nullable=false,insertable=false,updatable=false)
	private Client client;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="activity_type",referencedColumnName="id",nullable=false,insertable=false,updatable=false)
	private ActivityType activityType;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="module",referencedColumnName="id",nullable=false,insertable=false,updatable=false)
	private Module module;
	
	@Column(name="task_name",nullable=false,length=160)
	private String taskName;
	
	@Column(name="task_description",nullable=false,length=160)
	private String taskDescription;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="activity",referencedColumnName="id",nullable=false,insertable=false,updatable=false)
	private Activity activity;
	
	@Column(nullable=false,name="hours_spent")
	private Float hoursSpent;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="status",referencedColumnName="id",nullable=false,insertable=false,updatable=false)
	private Status status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Float getHoursSpent() {
		return hoursSpent;
	}
	public void setHoursSpent(Float hoursSpent) {
		this.hoursSpent = hoursSpent;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(0);
		builder.append("Worklog [id=" + id + ", person=" + person + ", date=" + date + ", client=" + client + ", activityType=" + activityType + ", module=" + module + ", taskName=" + taskName + ", taskDescription=" + taskDescription + ", activity=" + activity + ", hoursSpent=" + hoursSpent + ", status=" + status + "]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((hoursSpent == null) ? 0 : hoursSpent.hashCode());
		result = prime * result + id;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((taskDescription == null) ? 0 : taskDescription.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worklog other = (Worklog) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (activityType == null) {
			if (other.activityType != null)
				return false;
		} else if (!activityType.equals(other.activityType))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (hoursSpent == null) {
			if (other.hoursSpent != null)
				return false;
		} else if (!hoursSpent.equals(other.hoursSpent))
			return false;
		if (id != other.id)
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (taskDescription == null) {
			if (other.taskDescription != null)
				return false;
		} else if (!taskDescription.equals(other.taskDescription))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}
}
