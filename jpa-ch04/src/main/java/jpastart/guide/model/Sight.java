package jpastart.guide.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import jpastart.common.model.Address;

@Entity
@SecondaryTable(name = "sight_detail", pkJoinColumns = @PrimaryKeyJoinColumn(name = "sight_id", referencedColumnName = "id"))
public class Sight {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Embedded
  private Address korAddress;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "zipCode", column = @Column(name = "eng_zipcode")),
      @AttributeOverride(name = "address1", column = @Column(name = "eng_addr1")),
      @AttributeOverride(name = "address2", column = @Column(name = "eng_addr2"))
  })
  private Address engAddress;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "hoursOfOperation", column = @Column(name = "hours_op", table = "sight_detail")),
      @AttributeOverride(name = "holidays", column = @Column(table = "sight_detail")),
      @AttributeOverride(name = "facilities", column = @Column(table = "sight_detail"))
  })
  private SightDetail detail;

  public Sight() {
  }

  public Sight(final String name, final Address korAddress, final Address engAddress) {
    this.name = name;
    this.korAddress = korAddress;
    this.engAddress = engAddress;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Address getKorAddress() {
    return korAddress;
  }

  public Address getEngAddress() {
    return engAddress;
  }

  public SightDetail getDetail() {
    return detail;
  }

  public void setDetail(final SightDetail detail) {
    this.detail = detail;
  }
}
