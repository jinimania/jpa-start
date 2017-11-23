package jpastart.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.common.model.Address;
import jpastart.guide.model.Sight;
import org.junit.Test;

public class AttributeOverridingEntityTest extends JpaTestBase {

  @Test
  public void overrideAttribute() {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Sight sight = new Sight("경복궁",
          new Address("03045", "서울시 종로구", "세종로 1-1"),
          new Address("03045", "Jongno-gu, Seoul", "161, Sajik-ro"));
      entityManager.persist(sight);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }
}
