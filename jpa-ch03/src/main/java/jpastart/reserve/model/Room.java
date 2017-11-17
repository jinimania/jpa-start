package jpastart.reserve.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_info")
public class Room {

  @Id
  private String number;
  private String name;
  @Column(name = "description")
  private String desc;
  @Column(name = "id", insertable = false, updatable = false)
  private Long dbId;
  private LocalDateTime createTime;

  public Room() {
  }

  public String getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public Long getDbId() {
    return dbId;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void changeName(final String newName) {
    this.name = newName;
  }
}
