package gm.jta.service;

import gm.jta.data.PersonDao;
import gm.jta.domain.Person;
import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebService;
import java.util.List;

@Stateless
@WebService(endpointInterface = "gm.jta.service.PersonServiceWs")
// @PermitAll
@DeclareRoles({"ROLE_ADMIN", "ROLE_USER"})
@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
public class PersonServiceImpl implements PersonService, PersonServiceWs, PersonServiceRemote {

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
        personDao.updatePerson(person);
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public void deletePerson(Person person) {
        personDao.deletePerson(person);
    }
}
