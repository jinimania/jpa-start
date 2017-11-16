package jpastart.reserve.application;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import jpastart.reserve.model.UserDomain;
import jpastart.util.DBTestResource;
import org.junit.Rule;
import org.junit.Test;

public class ChangeNameServiceTest {

  @Rule
  public DBTestResource resource = new DBTestResource();
  private ChangeNameService changeNameService = new ChangeNameService();

  @Test
  public void userFound_thenNameChanged() {
    changeNameService.changeName("madvirus@madvirus.net", "범균");
    UserDomain.assertName("madvirus@madvirus.net", "범균");
  }

  @Test
  public void noUser_throwEx() {
    try {
      changeNameService.changeName("nouser@madvirus.net", "범균");
      fail("익셉션 발생해야 함");
    } catch (UserNotFoundException e) {
      e.printStackTrace();
    }
  }
}