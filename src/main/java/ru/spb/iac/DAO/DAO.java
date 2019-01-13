package ru.spb.iac.DAO;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import ru.spb.iac.Entity.Person;

public interface DAO<T> {
	Optional<Person> get(long id);

	Optional<Collection<Person>> getAll();

	int update(T t);

	int delete(T t);

	public long getLastId();

	void create(Person t);
}