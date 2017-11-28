package jpastart.reserve.application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import jpastart.jpa.JpaTestBase;
import jpastart.reserve.model.UserDomain;
import org.junit.Test;

public class WithdrawServiceTest extends JpaTestBase {

  @Test
  public void withdrawTest() {
    final String email = "newuser@jpaworld.net";
    UserDomain.instance().givenUser(email, "새사용자");
    final WithdrawService withdrawService = new WithdrawService();
    withdrawService.withdraw(email);
    assertThat(UserDomain.instance().countsByEmail(email), equalTo(0));
  }
}
