package github.magnusp.people;

import jakarta.enterprise.context.Dependent;
import org.jooq.generated.Tables;
import org.jooq.generated.tables.records.PersonRecord;

@Dependent
public class PersonMapper {

	public PersonRecord mapToRecord(Person person) {
		var personRecord = Tables.PERSON.newRecord();
		personRecord.setId(person.id());
		personRecord.setName(person.name());
		return personRecord;
	}

	public Person mapFromRecord(PersonRecord personRecord) {
		return new Person(personRecord.getId(), personRecord.getName());
	}
}
