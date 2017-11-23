package jpastart.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.reserve.model.Room;
import org.junit.Test;

public class JpaRemoveTest extends JpaTestBase {

  @Test
  public void removeEntity() {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Room r101 = entityManager.find(Room.class, "R101");
      entityManager.remove(r101);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }

  @Test
  public void removeReferencedEntity() {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();

    try {
      transaction.begin();
      final Room r102 = entityManager.getReference(Room.class, "R102");
      entityManager.remove(r102);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }
}
