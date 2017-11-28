package jpastart.reserve.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import jpastart.util.DBUtil;

public class UserDomain {

  private static UserDomain instance = new UserDomain();

  public static UserDomain instance() {
    return instance;
  }

  public UserDomain givenUser(final String email, final String name) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = DBUtil.getConnection();
      statement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?)");
      statement.setString(1, email);
      statement.setString(2, name);
      statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
      statement.executeQuery();
      return this;
    } catch (SQLException e) {
      throw new RuntimeException();
    } finally {
      DBUtil.close(statement);
      DBUtil.close(connection);
    }
  }

  public int countsByEmail(final String email) {
    int count = 0;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBUtil.getConnection();
      statement = connection.prepareStatement("SELECT count(*) FROM user WHERE email = ?");
      statement.setString(1, email);
      resultSet = statement.executeQuery();
      resultSet.next();
      count = resultSet.getInt(1);
      resultSet.close();
      statement.close();
    } catch (SQLException e) {
      throw new RuntimeException();
    } finally {
      DBUtil.close(resultSet);
      DBUtil.close(statement);
      DBUtil.close(connection);
    }
    return count;
  }

  public void givenNoUser(final String email) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = DBUtil.getConnection();
      statement = connection.prepareStatement("DELETE FROM user WHERE email = ?");
      statement.setString(1, email);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException();
    } finally {
      DBUtil.close(statement);
      DBUtil.close(connection);
    }
  }
}
