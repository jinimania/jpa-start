package jpastart.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.reserve.model.Room;
import org.junit.Test;

public class jpaUpdateTest extends JpaTestBase {

  @Test
  public void updateInTx() {
    EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Room room = entityManager.find(Room.class, "R101");
      room.changeName("카프리");
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
    entityManager = EMF.createEntityManager();
    final Room room = entityManager.find(Room.class, "R101");
    entityManager.close();
    assertThat(room.getName(), equalTo("카프리"));

  }
}
