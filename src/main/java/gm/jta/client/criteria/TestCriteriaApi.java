package gm.jta.client.criteria;

import gm.jta.domain.Person;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.*;

import java.util.ArrayList;
import java.util.List;

public class TestCriteriaApi {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmsPU");
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = null;
        Root<Person> fromPerson = null;
        TypedQuery<Person> query = null;
        Person person = null;
        List<Person> persons = null;

        // QUERY USING CRITERIA API //
        // 1. QUERY ALL PERSONS
        // STEP 1. THE OBJECT EntityManager CREATE AN INSTANCE OF CriteriaBuilder
        cb = em.getCriteriaBuilder();
        // STEP 2. CREATE A CriteriaQuery OBJECT
        criteriaQuery = cb.createQuery(Person.class);
        // STEP 3. CREATE ROOT OBJECT OF THE QUERY
        fromPerson = criteriaQuery.from(Person.class);
        // STEP 4. SELECT THE NECESSARY FROM THE 'from'
        criteriaQuery.select(fromPerson);
        // STEP 5. CREATE TypedSafe QUERY
        query = em.createQuery(criteriaQuery);
        // STEP 6. EXECUTE THE QUERY
        persons = query.getResultList();
        // showPersons(persons);

        // 2-a. QUERY PERSON WITH ID = 1
        // jpql = "select p from Person p where p.idPerson = 1
        log.debug("\n2-a. Query Person with ID = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Person.class);
        fromPerson = criteriaQuery.from(Person.class);
        criteriaQuery.select(fromPerson).where(cb.equal(fromPerson.get("idPerson"), 1));
        person = em.createQuery(criteriaQuery).getSingleResult();
        log.debug(person);

        // 2-b. QUERY PERSON WITH ID = 1
        log.debug("\n2-b. Query Person with ID = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Person.class);
        fromPerson = criteriaQuery.from(Person.class);
        criteriaQuery.select(fromPerson);
        // CLASS Predicate ALLOWS TO ADD MULTIPLE CRITERIA DYNAMICALLY
        List<Predicate> criteria = new ArrayList<Predicate>();
        // VERIFY CRITERIA TO ADD
        Integer idPersonParam = 1;
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class, "idPerson");
        criteria.add(cb.equal(fromPerson.get("idPerson"), parameter));
        // ADD CRITERIA
        if(criteria.isEmpty())
            throw new RuntimeException("Empty Criteria");
        else if (criteria.size() == 1)
            criteriaQuery.where(criteria.get(0));
        else
            criteriaQuery.where(cb.and(criteria.toArray(new Predicate[0])));

        query = em.createQuery(criteriaQuery);
        query.setParameter("idPerson", idPersonParam);
        // EXECUTE QUERY
        person = query.getSingleResult();
        log.debug(person);
    }

    private static void showPersons(List<Person> persons) {
        for (Person p : persons) log.info(p);
    }
}

















