package ru.spb.iac.Rest;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ru.spb.iac.DAO.DAO;
import ru.spb.iac.DAO.PesronDAO;
import ru.spb.iac.Entity.Person;
import ru.spb.iac.Service.PersonDTO;
import ru.spb.iac.Service.PersonService;

@CrossOrigin("*")
@RestController
public class MainRest {
	@Autowired
	PersonService personService;

	@GetMapping(path = "/get/{id}")
	public Optional<PersonDTO> getById(@PathVariable(value = "id") Long id) {

		return Optional.ofNullable(personService.getById(id));
	}

	@GetMapping(path = "/getall")
	public Optional<List<Person>> getAll() {
		return Optional.ofNullable(personService.getAll());
	}

	@DeleteMapping(path = "/deleteone/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePerson(@PathVariable long id) throws ParseException {

		int count = personService.deleteById(id);
		if (count > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("Deleted succesfully.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no that entity with id = " + id);
	}

	@PostMapping(path = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createPerson(@Valid @RequestBody Person person) throws Exception {
		personService.create(person);
		return ResponseEntity.status(HttpStatus.CREATED).body("Created succesfully.");

	}

	@PutMapping(path = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateStudent(@Valid @RequestBody Person person) {
		int count = personService.update(person);
		if (count == 1) {
			return ResponseEntity.status(HttpStatus.OK).body("Updated succesfully. id = " + person.getId());
		}
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
				.body("There is no that entity with id = " + person.getId());
	}

	@PutMapping(path = "/updateSomePersons", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateSomePersons(@Valid @RequestBody Collection<Long> collection) {
		if (!collection.isEmpty()) {
			collection.parallelStream().forEach((p) -> {
				if (personService.getById(p) != null) {
					personService.updateSystemProperties(p);

				}
			});

			return ResponseEntity.status(HttpStatus.OK).body(collection.size()+" persons updated");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No ids");
		}
	}
}
