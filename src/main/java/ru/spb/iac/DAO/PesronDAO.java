package ru.spb.iac.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.Entity.Person;

@Transactional
@Repository
public class PesronDAO implements DAO<Person> {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private EntityManager entityManager;

	@Override
	public Person get(long id) {
		Person p=entityManager.find(Person.class, id);
		if(p!=null) {
			return p;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public List<Person> getAll() {
		List<Person> res = entityManager.createQuery("from Person", Person.class).getResultList();
		return res;
	}

	@Override
	public void create(Person t) throws Exception {
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
		Person person = entityManager.find(Person.class, t.getId());
		if (person != null) {
			person.setBirthDate(t.getBirthDate());
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
		Query query = entityManager.createNativeQuery("DELETE FROM test.person WHERE id=" + id + ";");
		return query.executeUpdate();
	}

	public long getLastId() {
		Query query = entityManager.createNativeQuery("SELECT * FROM test.person ORDER BY id DESC LIMIT 1;",
				Person.class);
		Person p = (Person) query.getSingleResult();
		return p.getId();

	}

	@Override
	public int updateSystemProperties(Person t) {
		Person person = entityManager.find(Person.class, t.getId());
		if (person != null) {
			person.setBirthDate(t.getBirthDate());
			person.setComment("Обработано [" + sdf.format(new Date()) + "]");
			person.setFirstName(t.getFirstName());
			person.setLastName(t.getLastName());
			person.setMiddleName(t.getMiddleName());
			person.setUpdateDate((new Date()));
			entityManager.persist(person);
			return 1;
		} else {
			return 0;
		}
	}

}
