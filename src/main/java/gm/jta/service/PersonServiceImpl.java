package gm.jta.service;

import gm.jta.data.PersonDao;
import gm.jta.domain.Person;
import jakarta.annotation.Resource;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class PersonServiceImpl implements PersonServiceRemote, PersonService {

    @Inject
    private PersonDao personDao;

    @Resource
    private SessionContext context;

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
        try {
            personDao.updatePerson(person);
        } catch (Throwable t) {
            context.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void deletePerson(Person person) {
        personDao.deletePerson(person);
    }
}
