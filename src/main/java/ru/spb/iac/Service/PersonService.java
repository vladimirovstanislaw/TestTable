package ru.spb.iac.Service;

import java.util.List;

import DTO.PersonDTO;
import ru.spb.iac.Entity.Person;

public interface PersonService {
	public void create(Person person);

	public void update();

	public Person getById();

	public void deleteById();

	public List<Person> getAll();

	public void updateSomeRow();
	
	public boolean isValid(PersonDTO personDTO);
}
