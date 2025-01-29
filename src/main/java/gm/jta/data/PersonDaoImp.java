package gm.jta.data;

import gm.jta.domain.Person;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class PersonDaoImp implements PersonDao {

    // @PersistenceUnit(unitName = "SmsPU") // THIS IS FOR RESOURCE_LOCAL TRANSACTIONS
    @PersistenceContext(unitName = "SmsPU") // THIS IS FOR JTA TRANSACTIONS
    EntityManager em;

    @Override
    public List<Person> findAllPersons() {
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    @Override
    public Person findPersonById(Person person) {
        return em.find(Person.class, person.getIdPerson());
    }

    @Override
    public Person findPersonByEmail(Person person) {
        Query query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email");
        query.setParameter("email", person.getEmail());
        return (Person) query.getSingleResult();
    }

    @Override
    public void insertPerson(Person person) {
        em.persist(person);
    }

    @Override
    public void updatePerson(Person person) {
        em.merge(person);
    }

    @Override
    public void deletePerson(Person person) {
        em.remove(em.merge(person));
    }

}
