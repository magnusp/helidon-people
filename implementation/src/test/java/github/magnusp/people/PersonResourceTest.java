package github.magnusp.people;

import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import org.jooq.DSLContext;
import org.jooq.generated.Tables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@HelidonTest
public class PersonResourceTest {
	@Inject
	PersonResource personResource;

	@Inject
	DSLContext dslContext;

	@BeforeEach
	void setup() {
		dslContext.truncate(Tables.PERSON).execute();
	}

	@Test
	void doTest() {
		assertThat(personResource.listPerson()).hasFieldOrPropertyWithValue("values", emptyList());
	}

	@Test
	void doAnotherTest() {
		assertThat(personResource.createPerson(new Person(null, "foo"))).satisfies(person -> {
			assertThat(person.id()).isNotNull();
			assertThat(person).hasFieldOrPropertyWithValue("name", "foo");
		});
	}

	@Test
	void canUpdatePerson() {
		var created = personResource.createPerson(new Person(null, "Foo"));
		var toUpdate = new Person(null, "Pelle");
		assertThatCode(() -> personResource.updatePerson(created.id(), toUpdate)).doesNotThrowAnyException();
		assertThat(dslContext.selectFrom(Tables.PERSON)
			.where(Tables.PERSON.ID.eq(created.id()))
			.fetchOne()
		)
			.hasFieldOrPropertyWithValue("name", "Pelle");
	}
}
