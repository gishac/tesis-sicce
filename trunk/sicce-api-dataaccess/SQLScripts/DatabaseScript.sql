CREATE TABLE POWER_METER (
  ID_POWER_METER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(255) NULL,
  IP_ADDRESS VARCHAR(16) NULL,
  SERIAL VARCHAR(20) NULL,
  DEVICE_ID VARCHAR(20) NULL,
  PRIMARY KEY(ID_POWER_METER)
);

CREATE TABLE PARAMETER (
  ID_PARAMETER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  PARAMETER_KEY VARCHAR(100) NULL,
  DESCRIPTION VARCHAR(200) NULL,
  VALUE VARCHAR(200) NULL,
  CONFIGURABLE BOOL default true,
  PRIMARY KEY(ID_PARAMETER)
);

CREATE TABLE ROLE (
  ID_ROLE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(150) NULL,
  PRIMARY KEY(ID_ROLE)
);

CREATE TABLE ZONE (
  ID_ZONE INTEGER UNSIGNED  NOT NULL AUTO_INCREMENT,
  CODE VARCHAR(6) NULL,
  DESCRIPTION VARCHAR(50) NULL,
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
    ID_USER_POWER_METER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    ID_POWER_METER INTEGER UNSIGNED NULL,
    ID_USER_SICCE INTEGER UNSIGNED NOT NULL,
    ASSIGNED BOOL default false,
    MONITOR BOOL default false,
    PRIMARY KEY (ID_USER_POWER_METER),
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
  CONSTRAINT location_zone_ibfk_1 FOREIGN KEY (ID_ZONE) REFERENCES zone (ID_ZONE) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT location_zone_ibfk_2 FOREIGN KEY (ID_LOCATION) REFERENCES location (ID_LOCATION) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE MEASURE (
  ID_MEASURE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ID_LOCATION INTEGER UNSIGNED NOT NULL,
  ID_POWER_METER INTEGER UNSIGNED NOT NULL,
  DATE_MEASURE DATETIME NULL,
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
  ACTIVE_ENERGY_IN DOUBLE UNSIGNED NOT NULL,
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
  DESCRIPTION VARCHAR(255) NULL,
  MEASURE INTEGER,
  MEASURE_DESCRIPTION VARCHAR(255),
  MIN_VALUE_ALLOWED INTEGER,
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

CREATE TABLE EXCEPTION(
  ID_EXCEPTION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ID_POWER_METER INTEGER UNSIGNED NOT NULL,
  DATE_EXCEPTION DATETIME NOT NULL,
  STACK_TRACE TEXT,
  MESSAGE TEXT,
  PRIMARY KEY(ID_EXCEPTION)
);

CREATE TABLE TAX(
  ID_TAX INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(255) NOT NULL,
  START_DATE DATETIME NOT NULL,
  END_DATE DATETIME NOT NULL,
  TAX_VALUE DOUBLE NOT NULL,
  PRIMARY KEY(ID_TAX)
);

CREATE TABLE KW_VALUE(
  ID_KW_VALUE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  START_DATE DATETIME NOT NULL,
  END_DATE DATETIME NOT NULL,
  KW_VALUE_1 DOUBLE NOT NULL,
  KW_VALUE_2 DOUBLE NOT NULL,
  PRIMARY KEY(ID_KW_VALUE)
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
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Rubros',null,'Taxes',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Valores Kw/h',null,'KwValue',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Alarmas',null,'Alarm',2);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reportes Dinámicos',null,'Wizard',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reporte de Medidores',null,'UserPowerMeterReport',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reporte de Excepciones',null,'UserPowerMeterException',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reporte de Alarmas',null,'UserPowerMeterAlarm',3);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reportes Existentes',null,'SavedReport',1);
insert into option_sicce (ID_OPTION_SICCE,DESCRIPTION,ICON,ACTION_COMMAND,ID_GROUP) values(0,'Reportes Financieros',null,'ConsumptionReport',3);

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
insert into option_role values(1,16);

insert into location_type values(0,'Edificio');
insert into location_type values(0,'Facultad');

insert into PARAMETER values(0,'DIGI_PORT','Puerto de Comunicación DIGI','2101',1);
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_COMMAND','Trama Modbus Comando','03',0);
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES', 'Trama Modbus Dirección Inicial Hi Bytes','03',0);
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES','Trama Modbus Dirección Inicial Lo Bytes','00',0);
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES','Trama Modbus Dirección Final Hi Bytes','00',0);
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES','Trama Modbus Dirección Final Lo Bytes','78',0);
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_CRC_HI_BYTES','Trama Modbus CRC Hi Bytes','45',0);
insert into PARAMETER values(0,'READ_HOLDING_REGISTERS_CRC_LO_BYTES','Trama Modbus CRC Lo Bytes','AC',0);
insert into PARAMETER values(0,'READ_INTERVAL','Intervalo de tiempo entre lecturas (seg)','10',1);
insert into PARAMETER values(0,'SERVER_READ_TIMEOUT_INTERVAL','Intervalo de tiempo entre monitoreo de clientes en el servidor (seg)','20',1);
insert into PARAMETER values(0,'SERVER_MAX_INACTIVITY_ALLOWED','Máximo tiempo de inactividad permitido a los clientes (seg)','30',1);
insert into PARAMETER values(0,'SMTP_SERVER','Servidor SMTP','smtp.gmail.com',1);
insert into PARAMETER values(0,'SMTP_PORT','Puerto SMTP','587',1);
insert into PARAMETER values(0,'MAIL_SENDER','Usuario de envio de mail','gishac@gmail.com',1);
insert into PARAMETER values(0,'MAIL_SENDER_PASSWORD','Clave de usuario de envio de mail','gisbert',1);
insert into PARAMETER values(0,'MAIL_USE_SSL','Usar SSL','true',1);
insert into PARAMETER values(0,'KWH_VALUE_1','Valor Kw/h 7/22','0.06',1);
insert into PARAMETER values(0,'KWH_VALUE_2','Valor Kw/h 22/7','0.06',1);
insert into PARAMETER values(0,'FEE_STREET_LIGHTNING','Tasa Alumbrado Público','0',1);
insert into PARAMETER values(0,'FEE_GARBAGE_COLLECT','Tasa Recolección Basura','0',1);
insert into PARAMETER values(0,'FEE_FIRE_DEPARTMENT','Tasa Cuerpo de Bomberos','0',1);
insert into PARAMETER values(0,'SERVER_IP','Dirección IP del servidor de monitoreo','192.168.8.198',1);
insert into PARAMETER values(0,'SERVER_PORT','Puerto utilizado por el servidor de monitoreo','5500',1);

DELIMITER $$

DROP PROCEDURE IF EXISTS `sicce`.`sp_getConsumption` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getConsumption`(IN  startDate DATE, IN  endDate DATE)
BEGIN

DROP TABLE IF EXISTS tmp_start_date;
create table tmp_start_date AS
select id_measure, id_power_meter, id_location, date_measure, total_active_power
from measure where date_measure in (select max(date_measure)
from measure
where date_measure between startDate and startDate + 1
group by id_power_meter, id_location);


DROP TABLE IF EXISTS tmp_end_date;
create table tmp_end_date AS select id_measure, id_power_meter, id_location, date_measure, total_active_power
from measure where date_measure in (select max(date_measure)
from measure
where date_measure between endDate and endDate + 1
group by id_power_meter, id_location);

select "exito";
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS `sicce`.`getConsumptionPerday` $$
CREATE PROCEDURE `getConsumptionPerday`(IN  startDate DATE, IN  endDate DATE)
BEGIN
DECLARE done INT DEFAULT 0;
DECLARE TMP_DATE  DATE;
DECLARE TMP_START_DATE DATE;
DECLARE TMP_END_DATE DATE;
DECLARE VAR_ID_POWER_METER INTEGER;
DECLARE START_READ DOUBLE;
DECLARE LAST_READ DOUBLE;
DECLARE LAST_READ_PREVIOUS DOUBLE;
DECLARE CONSUMPTION_PER_DAY_PR1 DOUBLE;
DECLARE CONSUMPTION_PER_DAY_PR2 DOUBLE;
DECLARE VAR_ID_LOCATION INTEGER;

DECLARE CUR_POWER_METER CURSOR FOR SELECT ID_POWER_METER FROM POWER_METER;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

drop table if exists tmp_consumption ;

CREATE TABLE tmp_consumption
(ID_LOCATION INTEGER, ID_POWER_METER INTEGER, TMP_DATE DATE, CONSUMPTION_PER_DAY_PR1 DOUBLE, CONSUMPTION_PER_DAY_PR2 DOUBLE);



SET TMP_START_DATE  = startDate;
SET TMP_END_DATE  = endDate;

OPEN CUR_POWER_METER;

REPEAT

  FETCH CUR_POWER_METER INTO VAR_ID_POWER_METER;

    IF NOT done THEN
      SET TMP_DATE  = TMP_START_DATE;
      REPEAT
                SET CONSUMPTION_PER_DAY_PR1 = 0;
                SET START_READ = 0;
                SET LAST_READ = 0;
                SET LAST_READ_PREVIOUS = 0;

                select VAR_ID_POWER_METER,TMP_DATE;

                SELECT total_active_power into LAST_READ_PREVIOUS
                FROM measure WHERE date_measure IN (  SELECT max(date_measure)
                FROM measure
                WHERE HOUR(date_measure) < 22 AND DATE(date_measure) = DATE(DATE_ADD(TMP_DATE,INTERVAL -1 DAY))
                AND id_power_meter = VAR_ID_POWER_METER) AND id_power_meter = VAR_ID_POWER_METER;


                SELECT total_active_power into START_READ
                FROM measure WHERE date_measure IN (  SELECT max(date_measure)
                FROM measure
                WHERE HOUR(date_measure) < 7 AND DATE(date_measure) = TMP_DATE AND id_power_meter = VAR_ID_POWER_METER)
                AND id_power_meter = VAR_ID_POWER_METER;


                SELECT total_active_power INTO LAST_READ
                FROM measure WHERE date_measure IN (  SELECT MAX(date_measure)
                FROM measure WHERE HOUR(date_measure) < 22  AND DATE(date_measure) = TMP_DATE AND id_power_meter = VAR_ID_POWER_METER)
                AND id_power_meter = VAR_ID_POWER_METER;

                SELECT id_location INTO VAR_ID_LOCATION
                FROM location_power_meter WHERE id_power_meter = VAR_ID_POWER_METER;

                set done = 0;
                SET CONSUMPTION_PER_DAY_PR1 = START_READ - LAST_READ_PREVIOUS;
                SET CONSUMPTION_PER_DAY_PR2 = LAST_READ - START_READ;
                INSERT INTO  tmp_consumption VALUES (VAR_ID_LOCATION, VAR_ID_POWER_METER, TMP_DATE, CONSUMPTION_PER_DAY_PR1,CONSUMPTION_PER_DAY_PR2);

                --

                SET TMP_DATE = DATE(DATE_ADD(TMP_DATE,INTERVAL 1 DAY));



      UNTIL TMP_DATE > endDate END REPEAT;

    END IF;

UNTIL done END REPEAT;


CLOSE CUR_POWER_METER;

select * from tmp_consumption;

END $$
DELIMITER ;

