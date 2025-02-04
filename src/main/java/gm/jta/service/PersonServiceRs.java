package gm.jta.service;

import gm.jta.domain.Person;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/persons")
@Stateless
public class PersonServiceRs {

    @Inject
    private PersonService personService;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> listPersons() {
        return personService.listPersons();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Person getPerson(@PathParam("id") int id) {
        return personService.findPersonById(new Person(id));
}

    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addPerson(Person person) {
        try {
            personService.registerPerson(person);
            return Response.ok().entity(person).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updatePerson(@PathParam("id") int id, Person personUpdated) {
        try {
            Person person = personService.findPersonById(new Person(id));
            if (person != null) {
                personService.updatePerson(personUpdated);
                return Response.ok().entity(personUpdated).build();
            }
            else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) {
        try {
            personService.deletePerson(new Person(id));
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(404).build();
        }
    }

}












