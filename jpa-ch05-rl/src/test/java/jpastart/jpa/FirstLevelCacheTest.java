package jpastart.jpa;

import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import jpastart.reserve.model.User;
import org.junit.Test;

public class FirstLevelCacheTest extends JpaTestBase {

  @Test
  public void cache() {
    final EntityManager em = EMF.createEntityManager();
    try {
      final User user1 = em.find(User.class, "madvirus@madvirus.net");
      final User user2 = em.find(User.class, "madvirus@madvirus.net");
      assertThat(user1, sameInstance(user2));
    } finally {
      em.close();
    }
  }
}
