package github.magnusp.people;

import github.magnusp.people.dao.PersonRepository;
import github.magnusp.people.query.PersonQueries;
import github.magnusp.people.query.PersonQueriesImpl;
import io.helidon.microprofile.testing.junit5.AddBean;
import io.helidon.microprofile.testing.junit5.DisableDiscovery;
import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonResourceTest {
	PersonResource personResource;
	private PersonQueries personQueries;
	private PersonRepository personRepository;

	@BeforeEach
	void setup() {
		this.personQueries = mock(PersonQueries.class);
		this.personRepository = mock(PersonRepository.class);
		this.personResource = new PersonResource(personRepository, personQueries);
	}

	@Test
	void doTest() {
		when(personQueries.all()).thenReturn(emptyList());
		assertThat(personResource.listPerson()).hasFieldOrPropertyWithValue("values", emptyList());
	}

	@Test
	void doAnotherTest() {
		when(personRepository.create(eq("foo"))).thenReturn(new Person(1, "foo"));
		assertThat(personResource.createPerson(new Person(null, "foo"))).satisfies(person -> {
			assertThat(person.getId()).isNotNull();
			assertThat(person).hasFieldOrPropertyWithValue("name", "foo");
		});
	}

	@Test
	void canUpdatePerson() {
		when(personRepository.byId(eq(1))).thenReturn(new Person(1, "blah"));
		when(personRepository.save(any())).thenReturn(true);

		var toUpdate = new Person(null, "Pelle");
		assertThatCode(() -> personResource.updatePerson(1, toUpdate)).doesNotThrowAnyException();
	}
}
