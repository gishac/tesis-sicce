CREATE TABLE POWER_METER (
  ID_POWER_METER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(255) NULL,
  IP_ADDRESS VARCHAR(16) NULL,
  LOG_STATUS BOOL NULL,
  SERIAL VARCHAR(20) NULL,
  DEVICE_ID VARCHAR(20) NULL,
  STATUS_2 BOOL NULL,
  PRIMARY KEY(ID_POWER_METER)
);

CREATE TABLE PARAMETER (
  ID_PARAMETER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  PARAMETER_KEY VARCHAR(100) NULL,
  DESCRIPTION VARCHAR(200) NULL,
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

CREATE TABLE GROUPS (
  ID_GROUP INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(50) NULL,
  PRIMARY KEY(ID_GROUP)
);

CREATE TABLE OPTION_SICCE (
  ID_OPTION_SICCE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(50) NULL,
  ACTION_COMMAND VARCHAR(50) NULL,
  ICON BLOB NULL,
  ID_GROUP INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(ID_OPTION_SICCE),
  INDEX OPTION_SICCE_FKIndex1(ID_GROUP),
  FOREIGN KEY(ID_GROUP)
    REFERENCES GROUPS(ID_GROUP)
      ON DELETE CASCADE
      ON UPDATE NO ACTION
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
  EMAIL VARCHAR(200) NULL,
  PRIMARY KEY(ID_USER_SICCE),
  INDEX USER_SICCE_FKIndex1(ID_ROLE),
  FOREIGN KEY(ID_ROLE)
    REFERENCES ROLE(ID_ROLE)
      ON DELETE CASCADE
      ON UPDATE NO ACTION
);


CREATE TABLE LOCATION (
  ID_LOCATION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  PARENT_ID_LOCATION INTEGER UNSIGNED NULL,
  ID_LOCATION_TYPE INTEGER UNSIGNED NOT NULL,
  DESCRIPTION VARCHAR(255) NOT NULL,
  PRIMARY KEY(ID_LOCATION),
  INDEX Ubication_FKIndex2(PARENT_ID_LOCATION),
  INDEX LOCATION_FKIndex2(ID_LOCATION_TYPE),
  FOREIGN KEY(PARENT_ID_LOCATION)
    REFERENCES LOCATION(ID_LOCATION)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(ID_LOCATION_TYPE)
    REFERENCES LOCATION_TYPE(ID_LOCATION_TYPE)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE LOCATION_POWER_METER
(
    ID_POWER_METER INTEGER UNSIGNED NULL,
    ID_LOCATION INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (ID_POWER_METER, ID_LOCATION),
    KEY `LOCATION_POWER_METER_FKIndex1` (ID_POWER_METER),
    KEY `LOCATION_POWER_METER_FKIndex2` (ID_LOCATION),
    CONSTRAINT location_power_meter_ibfk_1 FOREIGN KEY (ID_POWER_METER) REFERENCES POWER_METER (ID_POWER_METER) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT location_power_meter_ibfk_2 FOREIGN KEY (ID_LOCATION) REFERENCES LOCATION (ID_LOCATION) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE USER_POWER_METER
(
    ID_POWER_METER INTEGER UNSIGNED NULL,
    ID_USER_SICCE INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (ID_POWER_METER, ID_USER_SICCE),
    KEY `USER_POWER_METER_FKIndex1` (ID_POWER_METER),
    KEY `USER_POWER_METER_FKIndex2` (ID_USER_SICCE),
    CONSTRAINT user_power_meter_ibfk_1 FOREIGN KEY (ID_POWER_METER) REFERENCES POWER_METER (ID_POWER_METER) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT user_power_meter_ibfk_2 FOREIGN KEY (ID_USER_SICCE) REFERENCES USER_SICCE (ID_USER_SICCE) ON DELETE CASCADE ON UPDATE NO ACTION
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
  ID_MEASURE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ID_LOCATION INTEGER UNSIGNED NOT NULL,
  ID_POWER_METER INTEGER UNSIGNED NOT NULL,
  DATE_MEASURE DATETIME NULL,
  INSTANTANEOUS_CURRENT_PHASE_1 DOUBLE UNSIGNED NOT NULL,
  INSTANTANEOUS_CURRENT_PHASE_2 DOUBLE UNSIGNED NOT NULL,
  INSTANTANEOUS_CURRENT_PHASE_3 DOUBLE UNSIGNED NOT NULL,
  NEUTRAL_CURRENT DOUBLE UNSIGNED NOT NULL,
  PHASE_TO_PHASE_VOLTAGE_PHASE_1_TO_2 DOUBLE UNSIGNED NOT NULL,
  PHASE_TO_PHASE_VOLTAGE_PHASE_2_TO_3 DOUBLE UNSIGNED NOT NULL,
  PHASE_TO_PHASE_VOLTAGE_PHASE_3_TO_1 DOUBLE UNSIGNED NOT NULL,
  PHASE_TO_NEUTRAL_VOLTAGE_PHASE_1 DOUBLE UNSIGNED NOT NULL,
  PHASE_TO_NEUTRAL_VOLTAGE_PHASE_2 DOUBLE UNSIGNED NOT NULL,
  PHASE_TO_NEUTRAL_VOLTAGE_PHASE_3 DOUBLE UNSIGNED NOT NULL,
  FREQUENCY DOUBLE UNSIGNED NOT NULL,
  TOTAL_ACTIVE_POWER DOUBLE UNSIGNED NOT NULL,
  TOTAL_REACTIVE_POWER DOUBLE UNSIGNED NOT NULL,
  TOTAL_APPARENT_POWER DOUBLE UNSIGNED NOT NULL,
  ACTIVE_POWER_PHASE_1 DOUBLE UNSIGNED NOT NULL,
  ACTIVE_POWER_PHASE_2 DOUBLE UNSIGNED NOT NULL,
  ACTIVE_POWER_PHASE_3 DOUBLE UNSIGNED NOT NULL,
  REACTIVE_POWER_PHASE_1 DOUBLE UNSIGNED NOT NULL,
  REACTIVE_POWER_PHASE_2 DOUBLE UNSIGNED NOT NULL,
  REACTIVE_POWER_PHASE_3 DOUBLE UNSIGNED NOT NULL,
  APPARENT_POWER_PHASE_1 DOUBLE UNSIGNED NOT NULL,
  APPARENT_POWER_PHASE_2 DOUBLE UNSIGNED NOT NULL,
  APPARENT_POWER_PHASE_3 DOUBLE UNSIGNED NOT NULL,
  DEMAND_CURRENT_PHASE_1 DOUBLE UNSIGNED NOT NULL,
  DEMAND_CURRENT_PHASE_2 DOUBLE UNSIGNED NOT NULL,
  DEMAND_CURRENT_PHASE_3 DOUBLE UNSIGNED NOT NULL,
  PUISSANCE_APPARENTE_MOYENNE_TOTALE DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_CURRENT_PHASE_1 DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_CURRENT_PHASE_2 DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_CURRENT_PHASE_3 DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_ACTIVE_POWER_PLUS DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_ACTIVE_POWER_MINUS DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_REACTIVE_POWER_PLUS DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_REACTIVE_POWER_MINUS DOUBLE UNSIGNED NOT NULL,
  MAXIMUM_DEMAND_APPARENT_POWER DOUBLE UNSIGNED NOT NULL,
  OPERATING_TIME_COUNTER DOUBLE UNSIGNED NOT NULL,
  ACTIVE_ENERGY_IN_PLUS DOUBLE UNSIGNED NOT NULL,
  REACTIVE_ENERGY_IN_PLUS DOUBLE UNSIGNED NOT NULL,
  APPARENT_ENERGY DOUBLE UNSIGNED NOT NULL,
  ACTIVE_ENERGY_OUT_MINUS DOUBLE UNSIGNED NOT NULL,
  REACTIVE_ENERGY_OUT_MINUS DOUBLE UNSIGNED NOT NULL,
  INPUT_1_PULSE_COUNTER DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY(ID_MEASURE),
  INDEX MEASURE_FKIndex1(ID_MEASURE),
  INDEX MEASURE_FKIndex2(ID_POWER_METER),
  INDEX MEASURE_FKIndex3(ID_LOCATION),
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
      ON DELETE CASCADE
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

insert into groups (ID_GROUP, DESCRIPTION) values (0, 'Opciones');
insert into groups (ID_GROUP, DESCRIPTION) values (0, 'Alarmas');
insert into groups (ID_GROUP, DESCRIPTION) values (0, 'Reportes');

insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Roles',null,'Role',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Usuarios',null,'User',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Medidores',null,'PowerMeter',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Tipos de Dependencia',null,'LocationType',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Zonas',null,'Zone',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Parámetros',null,'Parameter',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Dependencias',null,'Location',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Alarmas',null,'Alarm',2);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reportes Dinámicos',null,'Wizard',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Medidores',null,'PowerMeterReport',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Tipo de Dependencia',null,'LocationTypeReport',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Dependencias',null,'LocationReport',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Usuarios',null,'UserReport',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Zonas',null,'ZoneReport',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reportes Existentes',null,'SavedReport',1);

insert into role values(0,'Administrador');
insert into user_sicce(ID_USER_SICCE,ID_ROLE,NAME,LASTNAME,CODE_UCSG,USERNAME_SICCE,PASSWORD_SICCE) values(0,1,'adminsicce','adminsicce',null,'adminsicce','BGEYVgYT0cCYB6SDjstB8+09gzaB/ZW/');
insert into option_role values(1,1);
insert into option_role values(1,2);
insert into option_role values(1,3);
insert into option_role values(1,4);
insert into option_role values(1,5);
insert into option_role values(1,6);
insert into option_role values(1,7);
insert into option_role values(1,8);
insert into option_role values(1,9);
insert into option_role values(1,10);
insert into option_role values(1,11);
insert into option_role values(1,12);
insert into option_role values(1,13);
insert into option_role values(1,14);
insert into option_role values(1,15);

insert into location_type values(0,'Edificio');
insert into location_type values(0,'Facultad');

insert into PARAMETER values(0,'DIGI_PORT','Puerto de Comunicación DIGI','2101');
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_COMMAND','Trama Modbus Comando','03');
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES', 'Trama Modbus Dirección Inicial Hi Bytes','03');
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES','Trama Modbus Dirección Inicial Lo Bytes','00');
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES','Trama Modbus Dirección Final Hi Bytes','00');
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES','Trama Modbus Dirección Final Lo Bytes','62');
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_CRC_HI_BYTES','Trama Modbus CRC Hi Bytes','C4');
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_CRC_LO_BYTES','Trama Modbus CRC Lo Bytes','67');
insert into PARAMETER values(0,'READ_INTERVAL','Intervalo de tiempo entre lecturas (seg)','10');
insert into PARAMETER values(0,'SMTP_SERVER','Servidor SMTP','smtp.gmail.com');
insert into PARAMETER values(0,'SMTP_PORT','Puerto SMTP','587');
insert into PARAMETER values(0,'MAIL_SENDER','Usuario de envio de mail','gishac@gmail.com');
insert into PARAMETER values(0,'MAIL_SENDER_PASSWORD','Clave de usuario de envio de mail','gisbert');
insert into PARAMETER values(0,'MAIL_USE_SSL','Usar SSL','true');

