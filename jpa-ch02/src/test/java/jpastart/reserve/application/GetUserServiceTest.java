package jpastart.reserve.application;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Optional;
import jpastart.reserve.model.User;
import jpastart.util.DBTestResource;
import org.junit.Rule;
import org.junit.Test;

public class GetUserServiceTest {

  @Rule
  public DBTestResource resource = new DBTestResource();
  private GetUserService getUserService = new GetUserService();

  @Test
  public void userFound_thenReturn() {
    final Optional<User> userOpt = getUserService.getUser("madvirus@madvirus.net");
    assertTrue(userOpt.isPresent());
    final User user = userOpt.get();
    assertThat(user.getEmail(), equalTo("madvirus@madvirus.net"));
    assertThat(user.getName(), equalTo("최범균"));
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    assertThat(dateFormat.format(user.getCreateDate()), equalTo("2016-06-05 01:02:03"));
  }

  @Test
  public void noUser_returnNull() {
    final Optional<User> userOpt = getUserService.getUser("madvirus2@madvirus.net");
    assertFalse(userOpt.isPresent());
  }
}