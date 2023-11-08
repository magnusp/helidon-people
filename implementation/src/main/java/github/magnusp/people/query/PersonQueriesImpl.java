package github.magnusp.people.query;

import github.magnusp.people.Person;
import github.magnusp.people.PersonMapper;
import github.magnusp.people.infrastructure.VetoDuringTest;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Vetoed;
import jakarta.inject.Inject;
import org.jooq.DSLContext;

import java.util.List;

import static org.jooq.generated.Tables.PERSON;

@Dependent
@VetoDuringTest
@Vetoed
public class PersonQueriesImpl implements PersonQueries {
	private final DSLContext dslContext;
	private final PersonMapper personMapper;

	@Inject
	public PersonQueriesImpl(DSLContext dslContext) {
		this.dslContext = dslContext;
		this.personMapper = new PersonMapper();
	}

	@Override
	public List<Person> all() {
		return dslContext.selectFrom(PERSON).fetch().map(personMapper::mapFromRecord);
	}
}
