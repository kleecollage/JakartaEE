package gm.jta.client;

import gm.jta.domain.Person;
import gm.jta.service.PersonServiceRemote;
import org.apache.logging.log4j.*;
import javax.naming.*;
import java.util.Properties;

public class TestHandleTransactions {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        try {
            // JNDI CONFIG FOR WILDFLY
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context jndi = new InitialContext(props);
            PersonServiceRemote personService = (PersonServiceRemote) jndi.lookup("ejb:/jta/PersonServiceImpl!gm.jta.service.PersonServiceRemote");
            log.debug("INIT TEST TRANSACTIONAL HANDLE PersonServiceRemote...");
            // Look for Person object
            Person person1 = personService.findPersonById(new Person(1));
            log.debug("Person recovered: " + person1);
            // CREATE A CONTROLLED ERROR
            person1.setSurname("update with error..............................");
            // person1.setSurname("Doe");

            personService.updatePerson(person1);
            log.debug("Object updated: " + person1);
            log.debug("End test EJB Transactional");
        } catch (NamingException e) {
            log.debug(e.getMessage());
        }
    }
}
