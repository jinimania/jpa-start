package jpastart.jpa;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.reserve.model.Room;
import org.junit.Test;

public class JpaFindTest extends JpaTestBase {

  @Test
  public void find() {
    assertThat(findRoomById("R101"), notNullValue());
    assertThat(findRoomById("NO_ID"), nullValue());
  }

  private Room findRoomById(final String roomId) {
    Room room;
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      room = entityManager.find(Room.class, roomId);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
    return room;
  }
}
