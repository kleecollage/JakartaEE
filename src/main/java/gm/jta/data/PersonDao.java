package gm.jta.data;

import gm.jta.domain.Person;

import java.util.List;

public interface PersonDao {
    public List<Person> findAllPersons();

    public Person findPersonById(Person person);

    public Person findPersonByEmail(Person person);

    public void insertPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);
}
