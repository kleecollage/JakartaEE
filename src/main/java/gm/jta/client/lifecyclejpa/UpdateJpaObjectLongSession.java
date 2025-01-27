package gm.jta.client.lifecyclejpa;


import gm.jta.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateJpaObjectLongSession {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // TRANSACTION LIFECYCLE
        // 1. INIT TRANSACTION 1
        tx.begin();
        // 2. EXECUTE SELECT SQL SENTENCE
        Person person1 = em.find(Person.class, 1);
        log.debug("Object found: " + person1);
        // 3. setValue(newValue)
        person1.setEmail("j.smith55@mail.com");
        person1.setEmail("john.smith@mail.com");
        // 4. END TRANSACTION
        tx.commit();
        // Object status: deatached
        log.debug("Object updated: " + person1);
        // CLOSE ENTITY MANAGER
        em.close();
    }
}
