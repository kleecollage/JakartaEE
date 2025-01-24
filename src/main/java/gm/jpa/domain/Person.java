package gm.jpa.domain;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p ORDER BY p.idPerson")
})
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_person")
    private int idPerson;

    private String name;

    private String surname;

    private String email;

    private String phone;

    // NO ARGS CONSTRUCTOR
    public Person() { }

    public Person(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}















