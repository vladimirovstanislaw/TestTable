package ru.spb.iac.Rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.DAO.DAO;
import ru.spb.iac.DAO.PesronDAO;
import ru.spb.iac.Entity.Person;
import ru.spb.iac.Service.PersonService;

@RestController
public class MainRest {
	@Autowired
	DAO<Person> personDAO;

	@Autowired
	PersonService personService;

	@GetMapping(value = "/get/{id}")
	public Optional<Person> getById(@PathVariable(value = "id") Long id) {
		return personDAO.get(id);
	}

	@PostMapping("/add")
	public Person newPerson(@RequestBody Person person) {
		System.out.println(person);
		System.out.println("======================================");
		if (person != null) {
			personService.create(person);
		}

		return person;
	}

}
