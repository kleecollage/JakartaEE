package gm.jta.client.cascade;

import gm.jta.domain.Person;
import gm.jta.domain.User;
import jakarta.persistence.*;
import org.apache.logging.log4j.*;

public class CascadePersistenceJpa {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // 1. CREATE NEW OBJECT
        // status: Transitive
        Person person1 = new Person("Emily", "Willis", "e.willis@mail.com", "01 33 15 44 00");
        // CREATE USER OBJECT (HAS DEPENDENCY WITH PERSON OBJECT)
        User user1 = new User("eWillis55", "123456", person1);
        // 3. PERSIST ONLY USER OBJECT
        em.persist(user1); // automatically person1 persist eider due to cascadeType = all
        // 4. COMMIT TRANSACTION
        tx.commit();
        // Objects detached
        log.debug("Object persisted person1: " + person1);
        log.debug("Object persisted user1: " + user1);

        em.close();

    }
}
