package jpastart.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import jpastart.reserve.model.MonChargeId;
import jpastart.reserve.model.MonthCharge;
import org.junit.Test;

public class CompositKeyTest extends JpaTestBase {

  @Test
  public void find() {
    final EntityManager em = EMF.createEntityManager();
    try {
      final MonthCharge monthCharge = em
          .find(MonthCharge.class, new MonChargeId("H100-01", "201608"));
      assertThat(monthCharge, notNullValue());
      assertThat(monthCharge.getId().getHotelId(), equalTo("H100-01"));
      assertThat(monthCharge.getId().getYearMon(), equalTo("201608"));
      assertThat(monthCharge.getChargeAmount(), equalTo(1000));
      assertThat(monthCharge.getUnPayAmount(), equalTo(0));
    } finally {
      em.close();
    }
  }

  @Test
  public void persist() {
    final EntityManager em = EMF.createEntityManager();
    try {
      em.getTransaction().begin();
      final MonthCharge monthCharge = new MonthCharge();
      monthCharge.setId(new MonChargeId("H101-01", "201607"));
      monthCharge.setChargeAmount(30000);
      monthCharge.setUnPayAmount(10000);
      em.persist(monthCharge);
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction();
      throw ex;
    } finally {
      em.close();
    }
  }
}
