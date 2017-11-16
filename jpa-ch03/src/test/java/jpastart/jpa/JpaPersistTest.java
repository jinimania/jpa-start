package jpastart.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.reserve.model.Grade;
import jpastart.reserve.model.Hotel;
import org.junit.Test;

public class JpaPersistTest extends JpaTestBase {

  @Test
  public void persist() {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Hotel hotel = new Hotel("KR-S-01", "서울호텔", Grade.STAR5);
      entityManager.persist(hotel);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }
}
