package gm.ejb.service;

import gm.ejb.domain.Person;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonServiceImpl implements PersonServiceRemote {

    @Override
    public List<Person> listPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "John", "Doe", "jdoe@mail.com", "01 66 74 22 65"));
        persons.add(new Person(2, "Jane", "Smith", "jsmith@mail.com", "01 23 33 54 77"));
        return persons;
    }

    @Override
    public Person findPersonById(Person person) {
        return null;
    }

    @Override
    public Person findPersonByEmail(Person person) {
        return null;
    }

    @Override
    public void registerPerson(Person person) {
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

    }
}
