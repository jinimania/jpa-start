package jpastart.jpa;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import jpastart.reserve.model.Hotel;
import org.junit.Test;

public class JpaGetReferenceTest extends JpaTestBase {

  @Test
  public void accessInTx() {
    Hotel hotel;
    final EntityManager entityManager = EMF.createEntityManager();
    try {
      hotel = entityManager.getReference(Hotel.class, "H100-01");
      final String name = hotel.getName();
    } catch (Exception ex) {
      throw ex;
    } finally {
      entityManager.close();
    }
    final String name = hotel.getName();
    assertThat(name, notNullValue());
  }

  @Test
  public void acceccOutTx() {
    Hotel hotel;
    final EntityManager entityManager = EMF.createEntityManager();
    try {
      hotel = entityManager.getReference(Hotel.class, "H100-01");
    } catch (Exception ex) {
      throw ex;
    } finally {
      entityManager.close();
    }
    try {
      hotel.getName();
      fail("발생해야 함");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
