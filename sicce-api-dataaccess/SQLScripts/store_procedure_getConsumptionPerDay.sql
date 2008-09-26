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

                SELECT active_energy_in into LAST_READ_PREVIOUS
                FROM measure WHERE date_measure IN (  SELECT max(date_measure)
                FROM measure
                WHERE HOUR(date_measure) < 22 AND DATE(date_measure) = DATE(DATE_ADD(TMP_DATE,INTERVAL -1 DAY))
                AND id_power_meter = VAR_ID_POWER_METER) AND id_power_meter = VAR_ID_POWER_METER;


                SELECT active_energy_in into START_READ
                FROM measure WHERE date_measure IN (  SELECT max(date_measure)
                FROM measure
                WHERE HOUR(date_measure) < 7 AND DATE(date_measure) = TMP_DATE AND id_power_meter = VAR_ID_POWER_METER)
                AND id_power_meter = VAR_ID_POWER_METER;


                SELECT active_energy_in INTO LAST_READ
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