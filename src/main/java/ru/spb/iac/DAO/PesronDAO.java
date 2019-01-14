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
	public Person get(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Person.class, id);
	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		List<Person> res = entityManager.createQuery("from Person", Person.class).getResultList();
		return res;
	}

	@Override
	public void create(Person t) throws Exception {
		// TODO Auto-generated method stub
		if (t.getId() == 0) {
			t.setId(getLastId() + 1);
			entityManager.persist(t);
			return;
		} else {
			throw new Exception();
		}

	}

	@Override
	public int update(Person t) {
		// TODO Auto-generated method stub
		Person person = entityManager.find(Person.class, t.getId());
		if (person != null) {
			person.setBirthDate(t.getBirthDate());
			person.setComment(t.getComment());
			person.setFirstName(t.getFirstName());
			person.setLastName(t.getLastName());
			person.setMiddleName(t.getMiddleName());
			entityManager.persist(person);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery("DELETE FROM test.person WHERE id=" + id + ";");
		return query.executeUpdate();
	}

	public long getLastId() {
		Query query = entityManager.createNativeQuery("SELECT * FROM test.person ORDER BY id DESC LIMIT 1;",
				Person.class);
		Person p = (Person) query.getSingleResult();
		return p.getId();

	}

	public boolean contains() {
		return false;
	}

}
