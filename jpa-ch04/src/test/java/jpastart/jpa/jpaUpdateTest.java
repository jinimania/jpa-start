package jpastart.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.common.model.Address;
import jpastart.reserve.model.Hotel;
import org.junit.Test;

public class jpaUpdateTest extends JpaTestBase {

  @Test
  public void updateInTx() {
    EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Hotel hotel = entityManager.find(Hotel.class, "H100-01");
      hotel.changeAddress(new Address("08393", "서울시 구로구", "디지털로32길 72"));
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
    entityManager = EMF.createEntityManager();
    final Hotel hotel = entityManager.find(Hotel.class, "H100-01");
    entityManager.close();
    assertThat(hotel.getAddress().getAddress2(), equalTo("디지털로32길 72"));

  }
}
