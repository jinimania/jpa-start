package jpastart.reserve.application;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import jpastart.jpa.EMF;
import jpastart.reserve.model.User;

public class GetUserListService {

  public List<User> getAllUsers() {
    final EntityManager em = EMF.createEntityManager();
    try {
      em.getTransaction().begin();
      final TypedQuery<User> query = em
          .createQuery("select u from User u order by u.name", User.class);
      final List<User> result = query.getResultList();
      em.getTransaction().commit();
      return result;
    } catch (Exception ex) {
      em.getTransaction().rollback();
      throw ex;
    } finally {
      em.close();
    }
  }
}
