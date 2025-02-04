package gm.jax.rs.test;

import gm.jax.rs.domain.Person;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;

import java.util.List;

public class TestPersonServiceRs {
    // variables we use
    private static final String BASE_URL = "http://localhost:8080/jta-1.0-SNAPSHOT/webservice";
    private static Client client;
    private static WebTarget webTarget;
    private static Person person;
    private static List<Person> persons;
    private static Invocation.Builder invocationBuilder;
    private static Response response;

    public static void main(String[] args) {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URL).path("/persons");

        // Read one person (get method)
        person = webTarget.path("/2").request(MediaType.APPLICATION_XML).get(Person.class);
        System.out.println("person recover: " + person);

        // Read all persons (get method with readEntity of List<> type
        persons = webTarget.request(MediaType.APPLICATION_XML).get(Response.class).readEntity(new GenericType<List<Person>>() {});
        System.out.println("\nPERSONS RECOVER: ");
        printPersons(persons);

        // Add one person (post method)
        Person newPerson = new Person();
        newPerson.setName("Test54");
        newPerson.setSurname("Test5");
        newPerson.setEmail("test35@mail.com");
        newPerson.setPhone("01 54846132");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.post(Entity.entity(newPerson, MediaType.APPLICATION_XML));
        System.out.println();
        System.out.println(response.getStatus());
        String responseString = response.readEntity(String.class);
        System.out.println("Raw response: " + responseString);
        // Recover the new person added to update and delete
        // Person personRecover = response.readEntity(Person.class);
        // System.out.println("Person added: " + personRecover);

        // Update person (put method)
        Person personUpdate = person;
        personUpdate.setSurname("Smith");
        String pathId = "/" + personUpdate.getIdPerson();
        invocationBuilder = webTarget.path(pathId).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.put(Entity.entity(personUpdate, MediaType.APPLICATION_XML));
        System.out.println("\nresponse: " + response.getStatus());
        System.out.println("personUpdate = " + response.readEntity(Person.class));

        // Remove person (delete method)
        Person personDelete = person;
        String pathDeleteId =  "/" + personDelete.getIdPerson();
        invocationBuilder = webTarget.path(pathDeleteId).request(MediaType.APPLICATION_XML);
        // response = invocationBuilder.delete();
        System.out.println("\nresponse: " + response.getStatus());
        System.out.println("personDelete = " + personDelete);
    }

    private static void printPersons(List<Person> persons) {
        for(Person person : persons) {
            System.out.println("Person: " + person);
        }
    }
}













