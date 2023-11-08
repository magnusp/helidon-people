package github.magnusp.people;

import github.magnusp.people.query.PersonQueries;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;

@ApplicationScoped
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
