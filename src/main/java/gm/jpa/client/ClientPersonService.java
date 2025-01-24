package gm.jpa.client;

import gm.jpa.domain.Person;
import gm.jpa.service.PersonServiceRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

public class ClientPersonService {
    public static void main(String[] args) {
        System.out.println("INIT CALL TO EJB FROM CLIENT\n");
        try {
            // JNDI CONFIG FOR WILDFLY
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

            Context jndi = new InitialContext(props);
            PersonServiceRemote personService = (PersonServiceRemote) jndi.lookup("ejb:/ejb/PersonServiceImpl!gm.ejb.service.PersonServiceRemote");
            List<Person> persons = personService.listPersons();

            for (Person person : persons) {
                System.out.println(person);
            }

            System.out.println("\n END CALL TO EJB FROM CLIENT\n");
        } catch (NamingException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
