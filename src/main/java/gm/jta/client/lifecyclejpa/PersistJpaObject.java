package gm.jta.client.lifecyclejpa;


import gm.jta.domain.Person;
import jakarta.persistence.*;
import org.apache.logging.log4j.*;

public class PersistJpaObject {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // TRANSACTION LIFECYCLE
        // 1. CREATE NEW OBJECT
        //  Status: transitive
        Person person1 = new Person("Emily", "Willis", "e.willis@mail.com", "01 64 11 25 78");
        // 2. INIT TRANSACTION
        tx.begin();
        // 3. EXECUTE SQL
        em.persist(person1);
        // 4. COMMIT / ROLLBACK
        tx.commit(); // <- here the object is saved on db
        // Status: detached
        log.debug("Person persisted - status detached : " + person1);
        // CLOSE ENTITY MANAGER
        em.close();
    }
}
