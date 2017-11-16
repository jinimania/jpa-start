package jpastart.jpa;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpastart.reserve.model.User;
import org.junit.Test;

public class JpaTest {

  @Test
  public void createEntityManagerFactory() {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");
    final EntityManager entityManager = emf.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      entityManager.persist(new User("user2@user.com", "user", new Date()));
      transaction.commit();
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
    } finally {
      entityManager.close();
    }
    emf.close();
  }
}
