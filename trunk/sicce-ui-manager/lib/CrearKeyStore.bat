SET ROOT_CVS=C:\Documents and Settings\Administrador\Mis documentos\NetBeansProjects

SET JAR_PATH_MAIN=%ROOT_CVS%\sicce-ui-manager\dist
#SET JAR_PATH=%ROOT_CVS%\sicce-ui-manager\lib
SET JAR_PATH=.

SET kEY_PATH=%JAR_PATH%\keyStore.key
SET PASSWORD=prueba
SET ALIAS_KEY=SP

keytool -genkey -keystore %kEY_PATH% -alias %ALIAS_KEY% -storepass %PASSWORD%

PAUSE