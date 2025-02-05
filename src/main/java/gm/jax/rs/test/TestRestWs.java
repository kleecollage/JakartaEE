package gm.jax.rs.test;

import gm.jax.rs.domain.Person;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class TestRestWs {
    public static void main(String[] args) {
        HttpAuthenticationFeature feature = HttpAuthenticationFeature
                .basicBuilder()
                .nonPreemptive()
                .credentials("admin", "admin")
                .build();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(feature);

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget webTarget = client.target("http://localhost:8080/jta-1.0-SNAPSHOT/webservice").path("/persons");
        // Provide a valid ID
        Person person = webTarget.path("/1").request(MediaType.APPLICATION_XML).get(Person.class);
        System.out.println("person recovered: " + person);


    }
}











