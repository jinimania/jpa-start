package jpastart.reserve.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MonChargeId implements Serializable {

  @Column(name = "hotel_id")
  private String hotelId;
  @Column(name = "year_mon")
  private String yearMon;

  public MonChargeId() {
  }

  public MonChargeId(final String hotelId, final String yearMon) {
    if (hotelId == null) {
      throw new IllegalArgumentException("illegal hotelId");
    }
    if (yearMon == null) {
      throw new IllegalArgumentException("illegal yearMon");
    }
    this.hotelId = hotelId;
    this.yearMon = yearMon;
  }

  public String getHotelId() {
    return hotelId;
  }

  public String getYearMon() {
    return yearMon;
  }

  @Override
  public int hashCode() {
    int result = hotelId.hashCode();
    result = 31 * result + yearMon.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    MonChargeId that = (MonChargeId) obj;
    return hotelId.equals(that.hotelId) && yearMon.equals(that.yearMon);
  }
}
