package gm.jta.client.jpql;

import gm.jta.domain.Person;
import gm.jta.domain.User;
import jakarta.persistence.*;
import org.apache.logging.log4j.*;

import java.util.Iterator;
import java.util.List;

public class TestJpql {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        String jpql = null;
        Query q = null;
        List<Person> persons = null;
        Person person = null;
        Iterator iter = null;
        Object[] tuple = null;
        List<String> names = null;
        List<User> users = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();

        // 1. QUERY ALL OBJECT OF TYPE PERSON
        log.debug("\n1. Query of all persons");
        jpql = "select p from Person p";
        persons = em.createQuery(jpql).getResultList();
        // showPersons(persons);

        // 2. QUERY PERSON BY ID
        log.debug("\n2. Query person by id = 1");
        jpql = "select p from Person p where p.idPerson = 1";
        person = (Person) em.createQuery(jpql).getSingleResult();
        // log.debug(person);

        // 3. QUERY PERSON BY NAME
        log.debug("\n3. Query person by name = john");
        jpql = "select p from Person p where p.name = 'john'";
        persons = em.createQuery(jpql).getResultList();
        // showPersons(persons);

        // 4. QUERY INDIVIDUAL DATA, CREATES AN ARRAY(TUPLE) OF TYPE OBJECT WITH 3 COLUMNS
        log.debug("\n4. Query individual data, creates an array type object of 3 columns");
        jpql = "select p.name as Name, p.surname as Surname, p.email as Email from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tuple = (Object[]) iter.next();
            String name = (String) tuple[0];
            String surname = (String) tuple[1];
            String email = (String) tuple[2];
            // log.debug("name: " + name + ", surname: " + surname + ", email: " + email);
        }

        // 5. GET PERSON OBJECT  AND HIS ID, CREATES AN ARRAY OF TYPE OBJECT WITH 2 COLUMNS
        log.debug("\n5. Get person Object and his ID, it create an array of type Object with 2 colums");
        jpql = "select p, p.idPerson as Id from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tuple = (Object[]) iter.next();
            person = (Person) tuple[0];
            int idPerson = (int) tuple[1];
            // log.debug("Object person: " + person);
            // log.debug("IdPerson: " + idPerson);
        }

        // 6. QUERY ALL PERSONS
        jpql = "select new gm.jta.domain.Person(p.idPerson) from Person p";
        System.out.println("\n6. Return idPerson with the rest of Person object null");
        persons = em.createQuery(jpql).getResultList();
        // showPersons(persons);

        // 7. RETURN MIN AND MAX VALUES OF idPerson (scalar result)
        System.out.println("\n7. Query person by name = john");
        jpql = "select min(p.idPerson) as MinId, max(p.idPerson) as MaxId, count(p.idPerson) as Counter from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tuple = (Object[]) iter.next();
            Integer minId = (Integer) tuple[0];
            Integer maxId = (Integer) tuple[1];
            Long counter = (Long) tuple[2];
            // log.debug("M    in ID: " + minId + ", Max ID: " + maxId + ", Counter: " + counter);
        }

        // 8. COUNT THE NAMES OF PERSONS WHO ARE DIFFERENT
        log.debug("\n8. Count the names of persons who are different");
        jpql = "select count(distinct p.name ) from Person p";
        Long counter = (Long) em.createQuery(jpql).getSingleResult();
        // log.debug("No. of persons with different names: " + counter);

        // 9. CONCAT AND CONVERTS TO UPPERCASE THE NAME AND SURNAME
        log.debug("\n9. Concat and coverts to uppercase the name and surname");
        jpql = "select CONCAT(p.name, ' ', p.surname) as Name from Person p";
        names = em.createQuery(jpql).getResultList();
        for(String completeName : names) {
            // log.debug(completeName);
        }

        // 10. GET PERSON OBJECT WITH ID EQUALS TO PARAMETER PROVIDED
        log.debug("\n10. Get Person object with ID equals to provided param");
        int idPerson = 5;
        jpql = "select p from Person p where p.idPerson = :id";
        q = em.createQuery(jpql);
        q.setParameter("id", idPerson);
        person = (Person) q.getSingleResult();
        // log.debug(person);

        // 11. GET PERSONS WHO CONTAINS THE LETTER 'A' IN THE NAME NO MATTER IF ITS UPPERCASE OR LOWERCASE
        log.debug("\n11. Get persons who container the letter 'A' in the name regardless of whether it is uppercase or lowercase");
        jpql = "select p from Person p where upper(p.name) like upper(:parameter)";
        String stringParameter = "%a%";
        q = em.createQuery(jpql);
        q.setParameter("parameter", stringParameter);
        persons = q.getResultList();
        // showPersons(persons);

        // 12. BETWEEN USE
        log.debug("\n12. Between use");
        // jpql = "select p from Person p where p.idPerson between :param1 and :param2";
        jpql = "select p from Person p where p.idPerson between 1 and 3";
        persons = em.createQuery(jpql).getResultList();
        // showPersons(persons);

        // 13. USE OF ORDER BY
        log.debug("\n13. Use of order by");
        jpql = "select p from Person p where p.idPerson > 2 order by p.name desc, p.surname desc";
        persons = em.createQuery(jpql).getResultList();
        // showPersons(persons);

        // 14. SUBQUERIES
        log.debug("\n14. Use of subqueries");
        jpql = "select p from Person p where p.idPerson in (select min(p1.idPerson) from Person p1)";
        persons = em.createQuery(jpql).getResultList();
        // showPersons(persons);

        // 15. JOIN WITH LAZY LOADING
        log.debug("\n15. Join with Lazy Loading");
        jpql = "select u from User u join u.person p";
        users = em.createQuery(jpql).getResultList();
        // showUsers(users);

        // 16. LEFT JOIN WITH EAGER LOADING
        log.debug("\n16. Left join with Eager loading");
        jpql = "select u from User u left join fetch u.person p";
        users = em.createQuery(jpql).getResultList();
        showUsers(users);
    }


    private static void showPersons(List<Person> persons) {
        for (Person p : persons)  log.debug(p);
    }

    private static void showUsers(List<User> users) {
        for (User u : users)  log.debug(u);
    }
}














