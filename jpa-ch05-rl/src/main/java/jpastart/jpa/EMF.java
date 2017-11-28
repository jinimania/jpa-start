package jpastart.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {

  private static EntityManagerFactory emf;
  private static ThreadLocal<EntityManager> currentEm = new ThreadLocal<>();

  public static void init() {
    emf = Persistence.createEntityManagerFactory("jpastart");
  }

  public static void close() {
    emf.close();
  }

  public static EntityManager createEntityManager() {
    return emf.createEntityManager();
  }
}
