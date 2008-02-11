CREATE TABLE POWER_METER (
  ID_POWER_METER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(255) NULL,
  IP_ADDRESS VARCHAR(16) NULL,
  LOG_STATUS BOOL NULL,
  SERIAL VARCHAR(20) NULL,
  STATUS_2 BOOL NULL,
  PRIMARY KEY(ID_POWER_METER)
);

CREATE TABLE PARAMETER (
  ID_PARAMETER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(45) NULL,
  VALUE DECIMAL NULL,
  PRIMARY KEY(ID_PARAMETER)
);

CREATE TABLE ROLE (
  ID_ROLE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(20) NULL,
  PRIMARY KEY(ID_ROLE)
);

CREATE TABLE ZONE (
  ID_ZONE INTEGER   NOT NULL AUTO_INCREMENT,
  CODE VARCHAR(6) NULL,
  DESCRIPTION VARCHAR(20) NULL,
  PRIMARY KEY(ID_ZONE)
);

CREATE TABLE UNIT_MEASURE (
  ID_UNIT_MEASURE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(45) NULL,
  PRIMARY KEY(ID_UNIT_MEASURE)
);

CREATE TABLE OPTION_SICCE (
  ID_OPTION_SICCE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(30) NULL,
  ICON BLOB NULL,
  PRIMARY KEY(ID_OPTION_SICCE)
);

CREATE TABLE LOCATION_TYPE (
  ID_LOCATION_TYPE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  CODIGO VARCHAR(6) NOT NULL,
  DESCRIPTION VARCHAR(40) NOT NULL,
  PRIMARY KEY(ID_LOCATION_TYPE)
);

CREATE TABLE USER_SICCE (
  ID_USER_SICCE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ROLE_ID_ROLE INTEGER UNSIGNED NOT NULL,
  NAME VARCHAR(20) NULL,
  LASTNAME VARCHAR(20) NULL,
  CODE_UCSG INTEGER UNSIGNED NULL,
  USERNAME_SICCE VARCHAR(20) NULL,
  PASSWORD_SICCE VARCHAR(20) NULL,
  PRIMARY KEY(ID_USER_SICCE),
  INDEX USER_SICCE_FKIndex1(ROLE_ID_ROLE),
  FOREIGN KEY(ROLE_ID_ROLE)
    REFERENCES ROLE(ID_ROLE)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE LOCATION (
  ID_LOCATION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  POWER_METER_ID_POWER_METER INTEGER UNSIGNED NOT NULL,
  PARENT_ID_LOCATION INTEGER UNSIGNED NOT NULL,
  ID_LOCATION_TYPE INTEGER UNSIGNED NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
  LOCATION INTEGER UNSIGNED NOT NULL,
  CODE VARCHAR(6) NOT NULL,
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
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE `option_role` (
  `ID_ROLE` int(10) unsigned NOT NULL,
  `ID_OPTION_SICCE` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`ID_ROLE`,`ID_OPTION_SICCE`),
  KEY `OPTION_ROLE_FKIndex1` (`ID_OPTION_SICCE`),
  KEY `OPTION_ROLE_FKIndex2` (`ID_ROLE`),
  CONSTRAINT `option_role_ibfk_1` FOREIGN KEY (`ID_OPTION_SICCE`) REFERENCES `option_sicce` (`ID_OPTION_SICCE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `option_role_ibfk_2` FOREIGN KEY (`ID_ROLE`) REFERENCES `role` (`ID_ROLE`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

CREATE TABLE `location_zone` (
  `ID_LOCATION` int(10) unsigned NOT NULL,
  `ID_ZONE` int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY  (`ID_LOCATION`,`ID_ZONE`),
  KEY `LOCATION_ZONE_FKIndex1` (`ID_ZONE`),
  KEY `LOCATION_ZONE_FKIndex2` (`ID_LOCATION`),
  CONSTRAINT `location_zone_ibfk_1` FOREIGN KEY (`ID_ZONE`) REFERENCES `zone` (`ID_ZONE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `location_zone_ibfk_2` FOREIGN KEY (`ID_LOCATION`) REFERENCES `location` (`ID_LOCATION`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 

CREATE TABLE MEASURE (
  ID_MEASURE INTEGER UNSIGNED NOT NULL,
  LOCATION_ID_LOCATION INTEGER UNSIGNED NOT NULL,
  ID_POWER_METER INTEGER UNSIGNED NOT NULL,
  VALUE DECIMAL NULL,
  DATE_MEASURE DATETIME NULL,
  PRIMARY KEY(ID_MEASURE),
  INDEX MEASURE_FKIndex1(ID_MEASURE),
  INDEX MEASURE_FKIndex2(ID_POWER_METER),
  INDEX MEASURE_FKIndex3(LOCATION_ID_LOCATION),
  FOREIGN KEY(ID_MEASURE)
    REFERENCES UNIT_MEASURE(ID_UNIT_MEASURE)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(ID_POWER_METER)
    REFERENCES POWER_METER(ID_POWER_METER)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(LOCATION_ID_LOCATION)
    REFERENCES LOCATION(ID_LOCATION)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


