package gm.jax.rs.test;

import gm.jax.rs.domain.Person;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

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
        HttpAuthenticationFeature feature = HttpAuthenticationFeature
                .basicBuilder()
                .nonPreemptive()
                .credentials("admin", "admin")
                .build();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(feature);

        client = ClientBuilder.newClient(clientConfig);
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
        newPerson.setName("David");
        newPerson.setSurname("Brown");
        newPerson.setEmail("d.brown4@mail.com");
        newPerson.setPhone("01 54 84 61 32");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.post(Entity.entity(newPerson, MediaType.APPLICATION_XML));
        System.out.println();
        System.out.println(response.getStatus());
        // Recover the new person added to update and delete
        Person personRecover = response.readEntity(Person.class);
        System.out.println("Person added: " + personRecover);

        // Update person (put method)
        personRecover.setSurname("Smith");
        String pathId = "/" + personRecover.getIdPerson();
        invocationBuilder = webTarget.path(pathId).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.put(Entity.entity(personRecover, MediaType.APPLICATION_XML));
        System.out.println("\nresponse: " + response.getStatus());
        System.out.println("personUpdate = " + response.readEntity(Person.class));

        // Remove person (delete method)
        String pathDeleteId =  "/" + personRecover.getIdPerson();
        invocationBuilder = webTarget.path(pathDeleteId).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.delete();
        System.out.println("\nresponse: " + response.getStatus());
        System.out.println("personDelete = " + personRecover);
    }

    private static void printPersons(List<Person> persons) {
        for(Person person : persons) {
            System.out.println("Person: " + person);
        }
    }
}













