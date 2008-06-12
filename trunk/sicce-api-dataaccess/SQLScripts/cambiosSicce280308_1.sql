CREATE TABLE LOCATION (
  ID_LOCATION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  POWER_METER_ID_POWER_METER INTEGER UNSIGNED NULL,
  PARENT_ID_LOCATION INTEGER UNSIGNED NULL,
  ID_LOCATION_TYPE INTEGER UNSIGNED NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
  PRIMARY KEY(ID_LOCATION),
  INDEX Ubication_FKIndex2(PARENT_ID_LOCATION),
  INDEX LOCATION_FKIndex2(ID_LOCATION_TYPE),
  INDEX LOCATION_FKIndex3(POWER_METER_ID_POWER_METER),
  FOREIGN KEY(PARENT_ID_LOCATION)
    REFERENCES LOCATION(ID_LOCATION)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(ID_LOCATION_TYPE)
    REFERENCES LOCATION_TYPE(ID_LOCATION_TYPE)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(POWER_METER_ID_POWER_METER)
    REFERENCES POWER_METER(ID_POWER_METER)
      ON DELETE SET NULL
      ON UPDATE NO ACTION
);

CREATE TABLE USER_SICCE (
  ID_USER_SICCE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ID_ROLE INTEGER UNSIGNED NOT NULL,
  NAME VARCHAR(20) NULL,
  LASTNAME VARCHAR(20) NULL,
  CODE_UCSG INTEGER UNSIGNED NULL,
  USERNAME_SICCE VARCHAR(20) NULL,
  PASSWORD_SICCE VARCHAR(100) NULL,
  PRIMARY KEY(ID_USER_SICCE),
  INDEX USER_SICCE_FKIndex1(ID_ROLE),
  FOREIGN KEY(ID_ROLE)
    REFERENCES ROLE(ID_ROLE)
      ON DELETE CASCADE
      ON UPDATE NO ACTION
);