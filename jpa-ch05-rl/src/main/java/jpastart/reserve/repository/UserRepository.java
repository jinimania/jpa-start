package jpastart.reserve.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import jpastart.jpa.EMF;
import jpastart.reserve.model.User;

public class UserRepository {

  public User find(final String email) {
    final EntityManager em = getEntityManager();
    return em.find(User.class, email);
  }

  public void save(final User user) {
    final EntityManager em = getEntityManager();
    em.persist(user);
  }

  public void remove(final User user) {
    final EntityManager em = getEntityManager();
    em.remove(user);
  }

  private EntityManager getEntityManager() {
    return EMF.currentEntityManager();
  }

  public List<User> findAll() {
    return getEntityManager()
        .createQuery("select u from User u order by u.name", User.class).getResultList();
  }
}
