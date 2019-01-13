package ru.spb.iac.Service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.PersonDTO;
import ru.spb.iac.DAO.DAO;
import ru.spb.iac.Entity.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	DAO<Person> personDAO;

	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public void create(Person person) {
		// TODO Auto-generated method stub
		personDAO.create(person);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Person getById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSomeRow() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(PersonDTO personDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
