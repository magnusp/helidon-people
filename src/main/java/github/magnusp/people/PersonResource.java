
package github.magnusp.people;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.PersonRecord;

import static org.jooq.generated.Tables.PERSON;

@Path("/person")
public class PersonResource {

    @Inject
    DSLContext dslContext;

    @Inject
    PersonMapper personMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListPersonResponse listPerson() {
        var people = dslContext.selectFrom(PERSON).fetch().map(personMapper::mapFromRecord);
        return new ListPersonResponse(people);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Person createPerson(Person prototypePerson) {
        PersonRecord personRecord = dslContext.newRecord(PERSON);
        personRecord.setName(prototypePerson.name());
        personRecord.insert();
        return personMapper.mapFromRecord(personRecord);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePerson(@PathParam("id") Integer id, Person prototypePerson) {
        var person = new Person(id, prototypePerson.name());
        PersonRecord personRecord = personMapper.mapToRecord(person);
        dslContext.attach(personRecord);
        if(personRecord.update() == 0) {
            throw new RuntimeException("Not found");
        }
    }
}
