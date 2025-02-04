package gm.jta.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "jpa_db", uniqueConstraints = {
        @UniqueConstraint(name = "email_UNIQUE", columnNames = {"email"})
})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson"),
        @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
        @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.surname = :surname"),
        @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
        @NamedQuery(name = "Person.findByPhone", query = "SELECT p FROM Person p WHERE p.phone = :phone")
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    private Integer idPerson;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    // @Size(max = 40)
    @Column(name = "surname", length = 100)
    private String surname;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    // This @OneToMany association is eager, which triggers multiple SELECT queries
    // (one for each Person entity) to load the associated User entities.
    // Consider using FetchType.LAZY or optimizing with JOIN FETCH for better performance.
    // @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    @XmlTransient
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<User> userslist;

    public Person() { }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Person(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public List<User> getUserList() {
        return userslist;
    }

    public void setUserList(List<User> userslist) {
        this.userslist = userslist;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer id) {
        this.idPerson = id;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return idPerson.equals(person.idPerson) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(email, person.email) && Objects.equals(phone, person.phone) && Objects.equals(userslist, person.userslist);
    }

    @Override
    public int hashCode() {
        int result = idPerson.hashCode();
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(surname);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(phone);
        result = 31 * result + Objects.hashCode(userslist);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", idPerson=" + idPerson +
                '}';
    }
}