package jpastart.common.model;

import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

  private String zipCode;
  private String address1;
  private String address2;

  public Address(final String zipCode, final String address1, final String address2) {
    this.zipCode = zipCode;
    this.address1 = address1;
    this.address2 = address2;
  }

  public Address() {
  }

  public String getZipCode() {
    return zipCode;
  }

  public String getAddress1() {
    return address1;
  }

  public String getAddress2() {
    return address2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(zipCode, address1, address2);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Address address = (Address) obj;
    return Objects.equals(zipCode, address.zipCode) && Objects.equals(address1, address.address1)
        && Objects.equals(address2, address.address2);
  }
}
