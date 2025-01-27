package gm.jta.client.lifecyclejpa;


import gm.jta.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateJpaObject {
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
        // 3. END OF TRANSACTION 1
        tx.commit();
        // Status: detached
        log.debug("Person recovered - status detached : " + person1);
        // 4. setValue(new value)
        person1.setSurname("Smith");
        // 5. INIT TRANSACTION 2.
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        // 6. UPDATE OBJECT
        em.merge(person1);
        // 7. END OF TRANSACTION 2
        tx2.commit();
        // Status: detached and updated
        log.debug("Object recovered - status detached : " + person1);
        // CLOSE ENTITY MANAGER
        em.close();
    }
}
