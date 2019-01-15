package ru.spb.iac.Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "person")
public class Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "comment")
	private String comment;

	@Column(name = "update_date")
	private Date updateDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Person() {
		super();
	}

	public Person(long id, String lastName, String firstName, String middleName, Date birthDate, String comment,
			Date updateDate) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.birthDate = birthDate;
		this.comment = comment;
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", birthDate=" + birthDate + ", comment=" + comment + ", updateDate=" + updateDate + "]";
	}

}
