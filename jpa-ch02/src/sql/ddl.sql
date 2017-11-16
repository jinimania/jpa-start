CREATE TABLE jpastart.user (
  email       VARCHAR(50) NOT NULL PRIMARY KEY,
  name        VARCHAR(50),
  create_date DATETIME
)
  ENGINE innodb
  CHARACTER SET utf8;