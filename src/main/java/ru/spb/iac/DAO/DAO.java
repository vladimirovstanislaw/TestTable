package ru.spb.iac.DAO;

import java.util.List;
import ru.spb.iac.Entity.Person;

public interface DAO<T> {
	Person get(long id);

	List<Person> getAll();

	int update(T t);

	int delete(long t);

	public long getLastId();

	void create(Person t) throws Exception;
	
	int updateSystemProperties(T t);
}