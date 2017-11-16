package jpastart.reserve.model;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jpastart.util.DBUtil;

public class UserDomain {

  public static void assertUserExist(final String email) {
    final int count = countsByEmail(email);
    assertThat("user " + email + " exists", count, equalTo(1));
  }

  public static void assertUserNotFound(final String email) {
    final int count = countsByEmail(email);
    assertThat("user " + email + " not exists", count, equalTo(0));
  }

  private static int countsByEmail(final String email) {
    int count;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = DBUtil.getConnection();
      pstmt = conn.prepareStatement("SELECT count(*) FROM user WHERE email = ?");
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();
      rs.next();
      count = rs.getInt(1);
      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException();
    } finally {
      DBUtil.close(rs);
      DBUtil.close(pstmt);
      DBUtil.close(conn);
    }
    return count;
  }

  public static void assertName(final String email, final String expectedName) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = DBUtil.getConnection();
      pstmt = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();
      assertTrue(rs.next());
      final String realName = rs.getString("name");
      assertThat(expectedName, equalTo(realName));
      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException();
    } finally {
      DBUtil.close(rs);
      DBUtil.close(pstmt);
      DBUtil.close(conn);
    }
  }
}
