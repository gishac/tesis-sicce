SET ROOT_CVS=D:\§ish@c's Documents\NetBeans Projects\Tesis\
SET JAR_PATH_MAIN=%ROOT_CVS%\sicce-ui-manager\dist
#SET JAR_PATH=%ROOT_CVS%\sicce-ui-manager\lib
SET JAR_PATH=.

SET kEY_PATH=%JAR_PATH%\keyStore.key
SET PASSWORD=tesissicce
SET ALIAS_KEY=sicce

keytool -genkey -keystore %kEY_PATH% -alias %ALIAS_KEY% -storepass %PASSWORD%

PAUSE