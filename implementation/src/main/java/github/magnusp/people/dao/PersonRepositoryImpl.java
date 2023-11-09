package github.magnusp.people.dao;

import github.magnusp.people.Person;
import github.magnusp.people.PersonMapper;
import github.magnusp.people.VetoDuringTest;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import org.jooq.DSLContext;
import org.jooq.generated.Tables;
import org.jooq.generated.tables.records.PersonRecord;

import static org.jooq.generated.Tables.PERSON;

@Dependent
@Default
@VetoDuringTest
public class PersonRepositoryImpl implements PersonRepository {

	private final DSLContext dslContext;
	private final PersonMapper personMapper;

	@Inject
	public PersonRepositoryImpl(DSLContext dslContext) {
		this.dslContext = dslContext;
		this.personMapper = new PersonMapper();
	}

	@Override
	public Person create(String name) {
		PersonRecord personRecord = dslContext.newRecord(PERSON);
		personRecord.setName(name);
		personRecord.insert();
		return personMapper.mapFromRecord(personRecord);
	}

	@Override
	public Person byId(Integer id) {
		var f = dslContext.selectFrom(Tables.PERSON).where(PERSON.ID.eq(id)).fetchOne();
		return personMapper.mapFromRecord(f);
	}

	@Override
	public boolean save(Person person) {
		PersonRecord personRecord = personMapper.mapToRecord(person);
		dslContext.attach(personRecord);
		return personRecord.update() != 0;
	}
}
