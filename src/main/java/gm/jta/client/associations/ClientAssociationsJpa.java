package gm.jta.client.associations;

import gm.jta.domain.Person;
import gm.jta.domain.User;
import jakarta.persistence.*;
import org.apache.logging.log4j.*;

import java.util.List;

public class ClientAssociationsJpa {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();

        List<Person> persons = em.createNamedQuery("Person.findAll", Person.class).getResultList();
        // close connection
        em.close();
        // Print Objects<Person>
        for (Person person : persons) {
            log.debug("Person: " + person);
            // recover users for each person
            for(User user: person.getUserList()) {
                log.debug("User: " + user);
            }
        }
    }
}















