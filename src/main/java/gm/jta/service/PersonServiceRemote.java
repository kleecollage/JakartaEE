package gm.jta.service;

import gm.jta.domain.Person;

import java.util.List;

public interface PersonServiceRemote {
    public List<Person> listPersons();

    public Person findPersonById(Person person);

    public Person findPersonByEmail(Person person);

    public void registerPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);
}
