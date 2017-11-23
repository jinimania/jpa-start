package jpastart.reserve.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "month_charge")
public class MonthCharge {

  @Id
  private MonChargeId id;
  @Column(name = "charge_amt")
  private int chargeAmount;
  @Column(name = "unpay_amt")
  private int unPayAmount;

  public MonChargeId getId() {
    return id;
  }

  public void setId(final MonChargeId id) {
    this.id = id;
  }

  public int getChargeAmount() {
    return chargeAmount;
  }

  public void setChargeAmount(final int chargeAmount) {
    this.chargeAmount = chargeAmount;
  }

  public int getUnPayAmount() {
    return unPayAmount;
  }

  public void setUnPayAmount(final int unPayAmount) {
    this.unPayAmount = unPayAmount;
  }
}
