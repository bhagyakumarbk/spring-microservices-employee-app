package com.employee.app.model;

import java.util.Objects;

import com.employee.app.config.EmployeeIdGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "employee_table")
@Entity
public class EmployeeEntity {

	@Id
	private int id;
	private String name;
	private String emailId;
	private String bloodgroup;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = new EmployeeIdGenerator().generate();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the bloodgroup
	 */
	public String getBloodgroup() {
		return bloodgroup;
	}

	/**
	 * @param bloodgroup the bloodgroup to set
	 */
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloodgroup, emailId, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntity other = (EmployeeEntity) obj;
		return Objects.equals(bloodgroup, other.bloodgroup) && Objects.equals(emailId, other.emailId) && id == other.id
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", name=" + name + ", emailId=" + emailId + ", bloodgroup=" + bloodgroup
				+ "]";
	}

}
