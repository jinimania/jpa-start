package jpastart.reserve.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hotel_review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "hotel_id")
  private String hotelId;
  private int rate;
  private String comment;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "rating_date")
  private Date ratingDate;

  public Review() {
  }

  public Review(final String hotelId, final int rate, final String commnent,
      final Date ratingDate) {
    this.hotelId = hotelId;
    this.rate = rate;
    this.comment = commnent;
    this.ratingDate = ratingDate;
  }

  public Long getId() {
    return id;
  }

  public String getHotelId() {
    return hotelId;
  }

  public int getRate() {
    return rate;
  }

  public String getComment() {
    return comment;
  }

  public Date getRatingDate() {
    return ratingDate;
  }
}
