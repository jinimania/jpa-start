package jpastart.guide.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SightDetail {

  @Column(name = "hours_op")
  private String hoursOfOperation;
  private String holidays;
  private String facilities;

  public SightDetail() {
  }

  public SightDetail(final String hoursOfOperation, final String holidays,
      final String facilities) {
    this.hoursOfOperation = hoursOfOperation;
    this.holidays = holidays;
    this.facilities = facilities;
  }

  public String getHoursOfOperation() {
    return hoursOfOperation;
  }

  public void setHoursOfOperation(final String hoursOfOperation) {
    this.hoursOfOperation = hoursOfOperation;
  }

  public String getHolidays() {
    return holidays;
  }

  public void setHolidays(final String holidays) {
    this.holidays = holidays;
  }

  public String getFacilities() {
    return facilities;
  }

  public void setFacilities(final String facilities) {
    this.facilities = facilities;
  }
}
