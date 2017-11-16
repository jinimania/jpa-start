package jpastart.util;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DBUtil {

  static {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException();
    }
  }

  public static void initTestDate() {
    initData("/test-data.xml");
  }

  public static void initData(final String dataFile) {
    IDatabaseConnection conn = null;
    try {
      conn = getDatabaseConnection();
      final IDataSet data = createDataSet(dataFile);
      DatabaseOperation.CLEAN_INSERT.execute(conn, data);
    } catch (DatabaseUnitException | SQLException e) {
      e.printStackTrace();
    } finally {
      close(conn);
    }
  }

  private static IDataSet createDataSet(final String resource) throws DataSetException {
    return new XmlDataSet(new InputStreamReader(DBUtil.class.getResourceAsStream(resource)));
  }

  private static void close(final IDatabaseConnection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static DatabaseConnection getDatabaseConnection()
      throws DatabaseUnitException, SQLException {
    return new DatabaseConnection(getConnection());
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager
        .getConnection("jdbc:mysql://localhost:3306/jpastart?characterEncoding=utf8",
            "jpauser", "jpapass");
  }

  public static void close(final Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void close(final PreparedStatement pstmt) {
    try {
      if (pstmt != null) {
        pstmt.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void close(final ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
