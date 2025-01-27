package gm.jta.data;

import gm.jta.domain.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

@Stateless
public class UserDaoImp implements UserDao {

    // @PersistenceContext(unitName = "SmsPU") // this is in case of JTA TRANSACTIONS
    @PersistenceUnit(unitName = "SmsPU") // THIS IS FOR .RESOURCE LOCAL TRANSACTIONS
    EntityManager em;

    @Override
    public List<User> findAllUsers() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    public User findUserById(User user) {
        return em.find(User.class, user.getId());
    }

    @Override
    public User findUserByUsername(User user) {
        return em.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", user.getUsername()).getSingleResult();
    }

    @Override
    public void insertUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(em.merge(user));
    }
}
