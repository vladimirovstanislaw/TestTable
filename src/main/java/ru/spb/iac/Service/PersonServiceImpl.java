package ru.spb.iac.Service;

import java.text.SimpleDateFormat;
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

	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public void create(Person person) throws Exception {
		// TODO Auto-generated method stub
		personDAO.create(person);

	}

	@Override
	public int update(Person person) {
		// TODO Auto-generated method stub
		return personDAO.update(person);

	}

	@Override
	public Person getById(long id) {
		// TODO Auto-generated method stub
		return personDAO.get(id);
	}

	@Override
	public int deleteById(long id) {
		Person person = personDAO.get(id);

		if (person != null) {
			return personDAO.delete(id);
		}
		return 0;
		// TODO Auto-generated method stub

	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return personDAO.getAll();
	}

	@Override
	public void updateSomeRow() {
		// TODO Auto-generated method stub

	}
}
