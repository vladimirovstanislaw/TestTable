package ru.spb.iac.Service;

import java.util.List;
import ru.spb.iac.Entity.Person;

public interface PersonService {
	public void create(Person person) throws Exception;

	public int deleteById(long id);

	public List<Person> getAll();

	public void updateSomeRow();

	Person getById(long id);

	int update(Person person);

}
