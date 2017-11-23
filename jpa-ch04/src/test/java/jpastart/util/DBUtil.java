package jpastart.util;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
      Class.forName("org.mariadb.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static void initTestData() {
    initData("/test-data.xml");
  }

  public static void initData(String dataFile) {
    IDatabaseConnection conn = null;
    try {
      conn = getDatabaseConnection();
      final IDataSet data = createDataSet(dataFile);
      DatabaseOperation.CLEAN_INSERT.execute(conn, data);
    } catch (DatabaseUnitException | SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(conn);
    }
  }

  private static void close(final IDatabaseConnection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static void close(final Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static void close(final Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static void close(final ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static IDataSet createDataSet(String resource) throws DataSetException {
    return new XmlDataSet(new InputStreamReader(DBUtil.class.getResourceAsStream(resource)));
  }

  public static IDatabaseConnection getDatabaseConnection()
      throws DatabaseUnitException, SQLException {
    return new DatabaseConnection(getConnection());
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/jpastart?characterEncoding=utf8", "jpauser", "jpapass");
  }
}
