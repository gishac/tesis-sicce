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
  VALUE VARCHAR(200) NULL,
  PRIMARY KEY(ID_PARAMETER)
);

CREATE TABLE ROLE (
  ID_ROLE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(20) NULL,
  PRIMARY KEY(ID_ROLE)
);

CREATE TABLE ZONE (
  ID_ZONE INTEGER UNSIGNED  NOT NULL AUTO_INCREMENT,
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
  DESCRIPTION VARCHAR(50) NULL,
  ACTION_COMMAND VARCHAR(50) NULL,
  ICON BLOB NULL,
  PRIMARY KEY(ID_OPTION_SICCE)
);

CREATE TABLE LOCATION_TYPE (
  ID_LOCATION_TYPE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(40) NOT NULL,
  PRIMARY KEY(ID_LOCATION_TYPE)
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

CREATE TABLE option_role (
  ID_ROLE int(10) unsigned NOT NULL,
  ID_OPTION_SICCE int(10) unsigned NOT NULL,
  PRIMARY KEY  (ID_ROLE,ID_OPTION_SICCE),
  KEY `OPTION_ROLE_FKIndex1` (ID_OPTION_SICCE),
  KEY `OPTION_ROLE_FKIndex2` (ID_ROLE),
  CONSTRAINT option_role_ibfk_1 FOREIGN KEY (ID_OPTION_SICCE) REFERENCES option_sicce (ID_OPTION_SICCE) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT option_role_ibfk_2 FOREIGN KEY (ID_ROLE) REFERENCES role (ID_ROLE) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE location_zone (
  ID_LOCATION int(10) unsigned NOT NULL,
  ID_ZONE int(10) unsigned NOT NULL,
  PRIMARY KEY  (ID_LOCATION,ID_ZONE),
  KEY `LOCATION_ZONE_FKIndex1` (ID_ZONE),
  KEY `LOCATION_ZONE_FKIndex2` (ID_LOCATION),
  CONSTRAINT location_zone_ibfk_1 FOREIGN KEY (ID_ZONE) REFERENCES zone (ID_ZONE) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT location_zone_ibfk_2 FOREIGN KEY (ID_LOCATION) REFERENCES location (ID_LOCATION) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE MEASURE (
  ID_MEASURE INTEGER UNSIGNED NOT NULL,
  ID_LOCATION INTEGER UNSIGNED NOT NULL,
  ID_POWER_METER INTEGER UNSIGNED NOT NULL,
  VALUE DECIMAL NULL,
  DATE_MEASURE DATETIME NULL,
  PRIMARY KEY(ID_MEASURE),
  INDEX MEASURE_FKIndex1(ID_MEASURE),
  INDEX MEASURE_FKIndex2(ID_POWER_METER),
  INDEX MEASURE_FKIndex3(ID_LOCATION),
  FOREIGN KEY(ID_MEASURE)
    REFERENCES UNIT_MEASURE(ID_UNIT_MEASURE)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(ID_POWER_METER)
    REFERENCES POWER_METER(ID_POWER_METER)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(ID_LOCATION)
    REFERENCES LOCATION(ID_LOCATION)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


CREATE TABLE ALARM(
  ID_ALARM INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  SCHEDULE_TYPE INTEGER,
  ALARM_TYPE INTEGER,
  DESCRIPTION VARCHAR(255) NULL,
  MAX_VALUE_ALLOWED INTEGER,
  PRIMARY KEY(ID_ALARM)
);

CREATE TABLE SCHEDULE_DAY (
  ID_SCHEDULE_DAY INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ID_ALARM INTEGER UNSIGNED NOT NULL,
  DAY_SCHEDULED INTEGER,
  START_TIME INTEGER,
  END_TIME INTEGER,
  PRIMARY KEY(ID_SCHEDULE_DAY),
  INDEX ALARM_FKIndex1(ID_ALARM),
  FOREIGN KEY(ID_ALARM)
    REFERENCES ALARM(ID_ALARM)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE ALARM_USER(
  ID_ALARM INTEGER UNSIGNED NOT NULL,
  ID_USER INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY  (ID_ALARM,ID_USER),
  KEY `ALARM_USER_FKIndex1` (ID_ALARM),
  KEY `ALARM_USER_FKIndex2` (ID_USER),
  CONSTRAINT alarm_user_ibfk_1 FOREIGN KEY (ID_ALARM) REFERENCES ALARM (ID_ALARM) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT alarm_user_ibfk_2 FOREIGN KEY (ID_USER) REFERENCES USER_SICCE (ID_USER_SICCE) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE ALARM_POWER_METER(
  ID_ALARM INTEGER UNSIGNED NOT NULL,
  ID_POWER_METER INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY  (ID_ALARM,ID_POWER_METER),
  KEY `ALARM_PMETER_FKIndex1` (ID_ALARM),
  KEY `ALARM_PMETER_FKIndex2` (ID_POWER_METER),
  CONSTRAINT alarm_pmeter_ibfk_1 FOREIGN KEY (ID_ALARM) REFERENCES ALARM (ID_ALARM) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT alarm_pmeter_ibfk_2 FOREIGN KEY (ID_POWER_METER) REFERENCES POWER_METER (ID_POWER_METER) ON DELETE CASCADE ON UPDATE NO ACTION
);

insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND) values(0,'Roles',null,'Role');
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND) values(0,'Usuarios',null,'User');
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND) values(0,'Medidores',null,'PowerMeter');
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND) values(0,'Tipos de Dependencia',null,'LocationType');
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND) values(0,'Zonas',null,'Zone');
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND) values(0,'Parámetros',null,'Parameter');
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND) values(0,'Dependencias',null,'Location');

insert into role values(0,'Administrador');
insert into user_sicce(ID_USER_SICCE,ID_ROLE,NAME,LASTNAME,CODE_UCSG,USERNAME_SICCE,PASSWORD_SICCE) values(0,1,'adminsicce','adminsicce',null,'adminsicce','BGEYVgYT0cCYB6SDjstB8+09gzaB/ZW/');
insert into option_role values(1,1);
insert into option_role values(1,2);
insert into option_role values(1,3);
insert into option_role values(1,4);
insert into option_role values(1,5);
insert into option_role values(1,6);
insert into option_role values(1,7);

insert into location_type values(0,'Edificio');

