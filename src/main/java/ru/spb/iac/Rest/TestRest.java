package ru.spb.iac.Rest;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.DAO.DAO;
import ru.spb.iac.Entity.Person;

@RestController
public class TestRest {
	@Autowired
	DAO<Person> personDAO;

	@GetMapping(value = "/get/{id}/new")
	public Optional<Person> getById(@PathVariable(value = "id") Long id) {
		Person p = new Person();
		p.setId(personDAO.getLastId()+1);
		p.setFirstName("Stas");
		p.setMiddleName("Vladimirov");
		p.setLastName("Vadimovich");
		p.setBirthDate(new Date());
		personDAO.create(p);
		return personDAO.get(id);
	}

	@GetMapping(value = "/getLastId")
	public long getLastId() {
		return personDAO.getLastId();
	}
}
