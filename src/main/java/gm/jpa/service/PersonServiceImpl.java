package gm.jpa.service;

import gm.jpa.domain.Person;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonServiceImpl implements PersonServiceRemote, PersonService {

    @Override
    public List<Person> listPersons() {
        return null;
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
