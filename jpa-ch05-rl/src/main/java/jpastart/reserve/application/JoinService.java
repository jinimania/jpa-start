package jpastart.reserve.application;

import javax.persistence.EntityManager;
import jpastart.jpa.EMF;
import jpastart.reserve.model.User;
import jpastart.reserve.repository.UserRepository;

public class JoinService {

  private UserRepository userRepository = new UserRepository();

  public void join(final User user) {
    final EntityManager em = EMF.createEntityManager();
    try {
      em.getTransaction().begin();
      final User found = userRepository.find(user.getEmail());
      if (found != null) {
        throw new DuplicationEmailException();
      }
      userRepository.save(user);
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction().rollback();
      throw ex;
    } finally {
      EMF.closeCurrentEntityManager();
    }
  }
}
