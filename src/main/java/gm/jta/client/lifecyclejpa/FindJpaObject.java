package gm.jta.client.lifecyclejpa;


import gm.jta.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindJpaObject {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // TRANSACTION LIFECYCLE
        // 1. INIT TRANSACTION
        tx.begin();
        // 2. EXECUTE SQL
        Person person1 = em.find(Person.class, 1);
        // 3. COMMIT / ROLLBACK
        tx.commit(); // <- here the object is saved on db
        // Status: detached
        log.debug("Person recovered - status detached : " + person1);
        // CLOSE ENTITY MANAGER
        em.close();
    }
}
