package gm.jta.service;

import java.util.List;

import gm.jta.domain.Person;
import jakarta.ejb.Local;

@Local
public interface PersonService {
    public List<Person> listPersons();

    public Person findPersonById(Person person);

    public Person findPersonByEmail(Person person);

    public void registerPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);
}
