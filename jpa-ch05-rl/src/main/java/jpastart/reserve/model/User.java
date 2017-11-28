package jpastart.reserve.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User {

  @Id
  @Basic
  private String email;

  @Basic
  private String name;

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  public User() {
  }

  public User(final String email, final String name, final Date createDate) {
    this.email = email;
    this.name = name;
    this.createDate = createDate;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public Date getCreateDate() {
    return createDate;
  }


}
