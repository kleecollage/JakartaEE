package gm.jpa.service;

import gm.jpa.data.PersonDao;
import gm.jpa.domain.Person;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class PersonServiceImpl implements PersonServiceRemote, PersonService {

    @Inject
    private PersonDao personDao;

    @Override
    public List<Person> listPersons() {
        return personDao.findAllPersons();
    }

    @Override
    public Person findPersonById(Person person) {
        return personDao.findPersonById(person);
    }

    @Override
    public Person findPersonByEmail(Person person) {
        return personDao.findPersonByEmail(person);
    }

    @Override
    public void registerPerson(Person person) {
        personDao.insertPerson(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDao.updatePerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        personDao.deletePerson(person);
    }
}
