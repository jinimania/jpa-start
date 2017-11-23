package jpastart.guide.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import jpastart.common.model.Address;

@Embeddable
public class ContactInfo {

  @Column(name = "ct_phone")
  private String phone;
  @Column(name = "ct_email")
  private String email;
  @Embedded
  private Address address;

  public ContactInfo() {
  }

  public ContactInfo(final String phone, final String email, final Address address) {
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public Address getAddress() {
    return address;
  }
}
