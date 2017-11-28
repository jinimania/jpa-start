package jpastart.reserve.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import jpastart.jpa.EMF;
import jpastart.reserve.model.User;
import jpastart.reserve.repository.UserRepository;

public class WithdrawService {

  private UserRepository userRepository = new UserRepository();

  public void withdraw(final String email) {
    final EntityManager em = EMF.createEntityManager();
    final EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      final User user = userRepository.find(email);
      if (user == null) {
        throw new UserNotFoundException();
      }
      userRepository.remove(user);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
      throw ex;
    } finally {
      EMF.closeCurrentEntityManager();
    }
  }
}
