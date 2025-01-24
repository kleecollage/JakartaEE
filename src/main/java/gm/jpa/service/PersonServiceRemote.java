package gm.jpa.service;

import gm.jpa.domain.Person;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface PersonServiceRemote {

    public List<Person> listPersons();

    public Person findPersonById(Person person);

    public Person findPersonByEmail(Person person);

    public void registerPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);
}
