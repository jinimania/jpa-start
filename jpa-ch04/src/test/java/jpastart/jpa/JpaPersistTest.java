package jpastart.jpa;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.guide.model.City;
import jpastart.reserve.model.Grade;
import jpastart.reserve.model.Hotel;
import jpastart.reserve.model.Review;
import jpastart.util.DBUtil;
import org.junit.Test;

public class JpaPersistTest extends JpaTestBase {

  @Test
  public void persistNullEmbeddalbe() throws SQLException {
    final EntityManager entityManager = EMF.createEntityManager();
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      final Hotel hotel = new Hotel("KR-S-01", "서울호텔", Grade.STAR5, null);
      entityManager.persist(hotel);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw ex;
    } finally {
      entityManager.close();
    }

    final Connection conn = DBUtil.getConnection();
    Statement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT * FROM hotel WHERE id = 'KR-S-01'");
      rs.next();
      assertThat(rs.getString("zipcode"), nullValue());
      assertThat(rs.getString("address1"), nullValue());
      assertThat(rs.getString("address2"), nullValue());
    } finally {
      DBUtil.close(rs);
      DBUtil.close(stmt);
      DBUtil.close(conn);
    }
  }

}
