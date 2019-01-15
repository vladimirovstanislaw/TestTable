package ru.spb.iac.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;

import ru.spb.iac.Entity.Person;

public class PersonDTO {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private long id;

	private String lastName;

	
	private String firstName;

	
	private String middleName;

	
	private String birthDate;

	
	private String comment;

	
	private String updateDate;
	public static PersonDTO newDTO(Person t) {
		if(t!=null) {
			PersonDTO p=new PersonDTO(t.getId(),t.getLastName(),t.getFirstName(),t.getMiddleName(),"",t.getComment(),"");
			if(t.getBirthDate()!=null) {
				p.setBirthDate(sdf.format(t.getBirthDate()));
			}
			if(t.getUpdateDate()!=null) {
				p.setUpdateDate(sdf.format(t.getUpdateDate()));
			}
			return p;
		}
		else {
			return null;
		}

		
	}
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public static SimpleDateFormat getSdf() {
		return sdf;
	}
	public PersonDTO(long id, String lastName, String firstName, String middleName, String birthDate, String comment,
			String updateDate) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.birthDate = birthDate;
		this.comment = comment;
		this.updateDate = updateDate;
	}
	public PersonDTO() {
		super();
	}
	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", middleName="
				+ middleName + ", birthDate=" + birthDate + ", comment=" + comment + ", updateDate=" + updateDate + "]";
	}
	
}
