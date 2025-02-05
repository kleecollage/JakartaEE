package web.security.test;

import com.sun.enterprise.security.ee.authentication.ProgrammaticLogin;
import gm.jta.domain.Person;
import gm.jta.service.PersonServiceRemote;

import javax.naming.*;
import java.util.List;
import java.util.Properties;

public class ClientPersonService {
    public static void main(String[] args) {
        System.out.println("Init call to EJB from client");

        String authFile = "login.conf";
        System.setProperty("java.security.auth.login.config", authFile);
        ProgrammaticLogin programmaticLogin = new ProgrammaticLogin();
        programmaticLogin.login("admin", "admin".toCharArray());
        try {
            // JNDI CONFIG FOR WILDFLY //
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            // ADD EXPLICIT CREDENTIALS
            props.put(Context.SECURITY_PRINCIPAL, "admin");
            props.put(Context.SECURITY_CREDENTIALS, "admin");
            Context jndi = new InitialContext(props);

            PersonServiceRemote personService = (PersonServiceRemote) jndi.lookup("ejb:/jta-1.0-SNAPSHOT/PersonServiceImpl!gm.jta.service.PersonServiceRemote");
            List<Person> persons = personService.listPersons();
            for (Person person : persons) {
                System.out.println("Person: " + person);
            }
            System.out.println("\nEnd call to EJB from client");
        } catch (NamingException e) {
            e.printStackTrace(System.out);
        }
    }
}














