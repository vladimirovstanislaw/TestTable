package ru.spb.iac.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spb.iac.DAO.DAO;
import ru.spb.iac.Entity.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	DAO<Person> personDAO;


	@Override
	public void create(Person person) throws Exception {
		personDAO.create(person);

	}

	@Override
	public int update(Person person) {
		return personDAO.update(person);

	}

	@Override
	public PersonDTO getById(long id) {
		return PersonDTO.newDTO(personDAO.get(id));
	}

	@Override
	public int deleteById(long id) {
		Person person = personDAO.get(id);

		if (person != null) {
			return personDAO.delete(id);
		}
		return 0;
	}

	@Override
	public List<Person> getAll() {
		return personDAO.getAll();
	}

	@Override
	public int updateSystemProperties(long id) {
		
		Person person = personDAO.get(id);
		if(person!=null)
		{
			return personDAO.updateSystemProperties(person);
		}
		return 0;
	}
}
