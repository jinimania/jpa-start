package jpastart.reserve.application;

import static org.junit.Assert.*;

import jpastart.reserve.model.UserDomain;
import jpastart.util.DBTestResource;
import org.junit.Rule;
import org.junit.Test;

public class WithdrawServiceTest {

  @Rule
  public DBTestResource resource = new DBTestResource();
  private WithdrawService withdrawService = new WithdrawService();

  @Test
  public void userExists_thenWithdraw() {
    withdrawService.withdraw("madvirus@madvirus.net");
    UserDomain.assertUserNotFound("madvirus@madvirus.net");
  }

  @Test
  public void noUser_thenException() {
    try {
      withdrawService.withdraw("nouser@nouser.net");
      fail("익셉션 발생해야함");
    } catch (UserNotFoundException e) {
      e.printStackTrace();
    }
  }
}