
package github.magnusp.people;

import github.magnusp.people.dao.PersonRepository;
import github.magnusp.people.query.PersonQueries;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/person")
public class PersonResource {

    PersonRepository personRepository;

    PersonQueries personQueries;

    @Inject
    public PersonResource(PersonRepository personRepository, PersonQueries personQueries) {
        this.personRepository = personRepository;
        this.personQueries = personQueries;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListPersonResponse listPerson() {
        List<Person> people = personQueries.all();
        return new ListPersonResponse(people);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Person createPerson(Person prototypePerson) {
        return personRepository.create(prototypePerson.getName());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePerson(@PathParam("id") Integer id, Person prototypePerson) {
        Person person = personRepository.byId(id);
        person.setName(prototypePerson.getName());
        boolean result = personRepository.save(person);
        if(!result) {
            throw new RuntimeException("Not found");
        }
    }
}
