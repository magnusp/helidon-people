package github.magnusp.people;

import jakarta.enterprise.context.Dependent;
import org.jooq.generated.Tables;
import org.jooq.generated.tables.records.PersonRecord;

import java.util.Objects;

public class PersonMapper {

	public PersonRecord mapToRecord(Person person) {
		var personRecord = Tables.PERSON.newRecord();
		personRecord.setId(person.getId());
		personRecord.setName(person.getName());
		return personRecord;
	}

	public Person mapFromRecord(PersonRecord personRecord) {
		Objects.requireNonNull(personRecord);
		return new Person(personRecord.getId(), personRecord.getName());
	}
}
