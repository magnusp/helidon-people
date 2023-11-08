package github.magnusp.people;

import github.magnusp.people.query.PersonQueries;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;

import java.util.List;

import static org.mockito.Mockito.mock;

@Dependent
@Priority(100)
public class MockPersonQueries implements PersonQueries {
	private final PersonQueries proxy;

	public MockPersonQueries() {
		this.proxy = mock(PersonQueries.class);
	}

	public PersonQueries getProxy() {
		return proxy;
	}

	@Override
	public List<Person> all() {
		return proxy.all();
	}
}
