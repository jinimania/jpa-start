package jpastart.reserve.application;

import static org.junit.Assert.*;

import java.util.Date;
import jpastart.reserve.model.User;
import jpastart.reserve.model.UserDomain;
import jpastart.util.DBTestResource;
import org.junit.Rule;
import org.junit.Test;

public class JoinServiceTest {

  @Rule
  public DBTestResource resource = new DBTestResource();
  private JoinService joinService = new JoinService();

  @Test
  public void newUser_join() {
    final User newUser = new User("user@user.net", "1234", new Date());
    joinService.join(newUser);
    UserDomain.assertUserExist(newUser.getEmail());
  }

  @Test
  public void duplicateEmail_ExceptionThrown() {
    try {
      joinService.join(new User("user@user.com", "user", new Date()));
      fail("익셉션 발생해야 함");
    } catch (DuplicateEmailException ex) {

    }
  }
}