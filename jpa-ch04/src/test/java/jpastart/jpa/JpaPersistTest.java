package jpastart.jpa;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.guide.model.City;
import jpastart.reserve.model.Grade;
import jpastart.reserve.model.Hotel;
import jpastart.reserve.model.Review;
import org.junit.Test;

public class JpaPersistTest extends JpaTestBase {

  @Test
  public void persist() {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Hotel hotel = new Hotel("KR-S-01", "서울호텔", Grade.STAR5, address);
      entityManager.persist(hotel);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }

  @Test
  public void identify() {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Review review = new Review("KR-S-01", 5, "짱입니다.", new Date());
      entityManager.persist(review);
      final Long id = review.getId();
      assertThat(id, notNullValue());
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }

  @Test
  public void tableGenerator() {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final City city = new City("서울2");
      entityManager.persist(city);
      final Long id = city.getId();
      System.out.println("generated city id = " + id);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }
}
