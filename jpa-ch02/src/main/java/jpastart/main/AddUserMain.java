package jpastart.main;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpastart.reserve.model.User;

public class AddUserMain {

  public static void main(String[] args) {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");
    final EntityManager entityManager = emf.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();

    try {
      transaction.begin();
      final User user = new User("user@user.com", "user", new Date());
      entityManager.persist(user);
      transaction.commit();
    } catch (Exception ex) {
      ex.printStackTrace();
      transaction.rollback();
    } finally {
      entityManager.close();
    }
    emf.close();
  }
}
