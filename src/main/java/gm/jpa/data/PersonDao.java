package gm.jpa.data;

import gm.jpa.domain.Person;

import java.util.List;

public interface PersonDao {
    public List<Person> findAllPersons();

    public Person findPersonById(Person person);

    public Person findPersonByEmail(Person person);

    public void insertPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);
}
