package jpastart.reserve.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import jpastart.common.model.Address;

@Entity
public class Hotel {

  @Id
  private String id;
  private String name;
  @Enumerated(EnumType.STRING)
  private Grade grade;
  @Embedded
  private Address address;

  public Hotel() {
  }

  public Hotel(final String id, final String name, final Grade grade, final Address address) {
    this.id = id;
    this.name = name;
    this.grade = grade;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Grade getGrade() {
    return grade;
  }

  public void changeAddress(final Address newAddress) {
    this.address = newAddress;
  }
}
