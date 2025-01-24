package gm.jpa.service;

import gm.jpa.domain.Person;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface PersonService {
    public List<Person> listPersons();

    public Person findPersonById(Person person);

    public Person findPersonByEmail(Person person);

    public void registerPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);
}
