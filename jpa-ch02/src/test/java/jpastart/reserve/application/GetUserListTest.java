package jpastart.reserve.application;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import jpastart.reserve.model.User;
import jpastart.util.DBTestResource;
import org.junit.Rule;
import org.junit.Test;

public class GetUserListTest {

  @Rule
  public DBTestResource resource = new DBTestResource();
  private GetUserListService getUserListService = new GetUserListService();

  @Test
  public void userList() {
    final List<User> users = getUserListService.getAllUsers();
    assertThat(users.size(), equalTo(1));
  }
}