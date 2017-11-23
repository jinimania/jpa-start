package jpastart.jpa;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.reserve.model.Hotel;
import org.junit.Test;

public class JpaFindTest extends JpaTestBase {

  @Test
  public void findNotNullEmbeddedValue() {
    final Hotel hotel = findHotelById("H100-01");
    assertThat(hotel.getAddress(), notNullValue());
  }

  @Test
  public void findNullEmbeddedValue() {
    final Hotel hotel = findHotelById("H100-02");
    assertThat(hotel.getAddress(), nullValue());
  }

  private Hotel findHotelById(final String hotelId) {
    Hotel hotel = null;
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      hotel = entityManager.find(Hotel.class, hotelId);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
    return hotel;
  }
}
