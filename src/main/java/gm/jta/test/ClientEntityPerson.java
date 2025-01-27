package gm.jta.test;

import gm.jta.domain.Person;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientEntityPerson {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // INIT TRANSACTION
        tx.begin();
        // ID FROM DB MUST BE NOT SPECIFIED
        Person person1 = new Person("Alex", "Johnson", "a.johnson@mail.com", "01 63 11 45 77");
        log.debug("Object to persist: " + person1);
        // OBJECT PERSISTANCE
        em.persist(person1);
        // END TRANSACTION
        tx.commit();
        log.debug("Object persisted: " + person1);
        em.close();
    }
}
