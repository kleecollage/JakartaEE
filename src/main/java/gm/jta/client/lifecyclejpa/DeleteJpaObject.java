package gm.jta.client.lifecyclejpa;


import gm.jta.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteJpaObject {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // TRANSACTION LIFECYCLE
        // 1. INIT TRANSACTION
        tx.begin();
        // 2. EXECUTE SQL (SELECT)
        Person person1 = em.find(Person.class, 4);
        // 3. END TRANSACTION 1
        tx.commit();
        // Status: detached
        log.debug("Object found (detached) : " + person1);
        // 4. INIT TRANSACTION 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        // 5. EXECUTE SQL (DELETE)
        em.remove(em.merge(person1)); // merge to synchronize db with the object in memory
        // 6. END TRANSACTION 2
        tx2.commit();
        // Object status: detached
        log.debug("Object deleted: " + person1);
        // CLOSE ENTITY MANAGER
        em.close();
    }
}
