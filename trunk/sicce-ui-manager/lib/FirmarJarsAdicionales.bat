SET ROOT_CVS=G:\Mis Documentos\Java Workspace\Tesis Sicce\
SET JAR_PATH_MAIN=%ROOT_CVS%\sicce-ui-manager\dist
#SET JAR_PATH=%ROOT_CVS%\\sicce-ui-manager\lib
SET JAR_PATH=.

SET kEY_PATH=%JAR_PATH%\keyStore.key
#SET kEY_PATH=%ROOT_CVS%\sicce-ui-manager\lib\keyStore.key
SET PASSWORD=tesisucsg
SET ALIAS_KEY=sicce

SET NOMBRE_JAR=Reports.jar
jarsigner -keystore %kEY_PATH% -storepass %PASSWORD% %JAR_PATH%\%NOMBRE_JAR% %ALIAS_KEY% -signedjar


PAUSE