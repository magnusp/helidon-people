package github.magnusp.people;

import github.magnusp.people.dao.PersonRepository;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;

import static org.mockito.Mockito.mock;

@Dependent
@Priority(100)
public class MockPersonRepository implements PersonRepository {
	private final PersonRepository proxy;

	public MockPersonRepository() {
		this.proxy = mock(PersonRepository.class);
	}

	public PersonRepository getProxy() {
		return proxy;
	}

	@Override
	public Person create(String name) {
		return null;
	}

	@Override
	public Person byId(Integer id) {
		return null;
	}

	@Override
	public boolean save(Person person) {
		return false;
	}
}
