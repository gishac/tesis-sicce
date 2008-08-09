SET ROOT_CVS=G:\Mis Documentos\Java Workspace\Tesis Sicce\
SET JAR_PATH_MAIN=%ROOT_CVS%\sicce-ui-manager\dist
#SET JAR_PATH=%ROOT_CVS%\sicce-ui-manager\lib
SET JAR_PATH=.

SET kEY_PATH=%JAR_PATH%\keyStore.key
SET PASSWORD=tesisucsg
SET ALIAS_KEY=sicce

keytool -keystore %kEY_PATH% -storepass %PASSWORD% -selfcert -alias %ALIAS_KEY%  -dname "cn=TesisSicce, ou=SICCE, o=UCSG, c=EC"