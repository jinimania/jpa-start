package jpastart.reserve.application;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Date;
import jpastart.jpa.JpaTestBase;
import jpastart.reserve.model.User;
import jpastart.reserve.model.UserDomain;
import org.junit.Test;

public class JoinServiceTest extends JpaTestBase {

  @Test
  public void join() {
    UserDomain.instance().givenNoUser("newuser@newuser.com");
    final JoinService joinService = new JoinService();
    joinService.join(new User("newuser@newuser.com", "새사용자", new Date()));
    assertThat(UserDomain.instance().countsByEmail("newuser@newuser.com"), equalTo(1));
  }
}
