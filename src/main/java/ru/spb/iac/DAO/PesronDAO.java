package ru.spb.iac.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.Entity.Person;

@Transactional
@Repository
public class PesronDAO implements DAO<Person> {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Optional<Person> get(long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(entityManager.find(Person.class, id));
	}

	public Person getpure(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Person.class, id);
	}

	@Override
	public Optional<Collection<Person>> getAll() {
		// TODO Auto-generated method stub
		return Optional.ofNullable((new ArrayList<Person>()));
	}

	@Override
	public void create(Person t) {
		// TODO Auto-generated method stub
		entityManager.persist(t);
	}

	@Override
	public int update(Person t) {
		// TODO Auto-generated method stub
		
		entityManager.refresh(t);
		return 5;

	}

	@Override
	public int delete(Person t) {
		// TODO Auto-generated method stub
		Query query=entityManager.createNativeQuery("DELETE FROM test.person WHERE id=" + t.getId() + ";");
		 return query.executeUpdate();
	}

	public long getLastId() {
		Query query = entityManager.createNativeQuery("SELECT * FROM test.person ORDER BY id DESC LIMIT 1;",
				Person.class);
		Person p = (Person) query.getSingleResult();
		return p.getId();

	}

}
