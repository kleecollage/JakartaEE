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
        // Read person (get method)
        webTarget = client.target(BASE_URL).path("/persons");
        // Provide a valid idPerson
        person = webTarget.path("/1").request(MediaType.APPLICATION_XML).get(Person.class);
        System.out.println("person recover: " + person);
    }
}













