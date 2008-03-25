/*Aumenta el tamaño del campo password por la encriptacion*/
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
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


/*Agrega la columna con el action id*/
CREATE TABLE OPTION_SICCE (
  ID_OPTION_SICCE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  DESCRIPTION VARCHAR(50) NULL,
  ACTION_COMMAND VARCHAR(50) NULL,
  ICON BLOB NULL,
  PRIMARY KEY(ID_OPTION_SICCE)
);

/*Valores por default de la aplicacion*/
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
