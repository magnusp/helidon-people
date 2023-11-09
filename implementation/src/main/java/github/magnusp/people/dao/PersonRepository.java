package github.magnusp.people.dao;

import github.magnusp.people.Person;

public interface PersonRepository {
	Person create(String name);

	Person byId(Integer id);

	boolean save(Person person);
}
