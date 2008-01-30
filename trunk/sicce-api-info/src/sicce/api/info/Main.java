package sicce.api.info;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import sicce.api.info.PowerMeterInfo;
import java.util.List;
import org.apache.cayenne.access.DataContext;

/**
 * 
 * @author Karu
 */
public class Main {

    /**
     * Para correr el proyecto tienes que agregar los jar de cayenne y de mysql
     * ademas revisa que esten agregados los tres archivos xml de cayenne.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
/*En este codigo se crea, modifica y elimina un objeto especifico...*/
        
        System.out.println("hi mini");
        /* se instancia el DataContext : para conectarse a la base */
        DataContext con = DataContext.createDataContext();
        /*se crea el objeto*/

//        PowerMeterInfo objPowerMeter = new PowerMeterInfo();
//        /*se setean la informacion del nuevo objeto*/
//                    con.registerNewObject(objPowerMeter);
//                   /* las dos lineas de creacion y registro de objeto pueden ser abreviadas asi:
                    PowerMeterInfo objPowerMeter = (PowerMeterInfo) con.newObject(PowerMeterInfo.class);
                    
                    objPowerMeter.setDescription("PM500");
                    //CORREGIR EL IP_ADRESS :( ESTABA COMO INT.. DEBERIA SER UN VARCHAR
                    objPowerMeter.setIpAddress(23l);
                    objPowerMeter.setLogStatus(false);
                    objPowerMeter.setSerial("15563565656");
                    /*se guarda*/
                    con.commitChanges();
//        /*modificar el objeto*/
//                  /* hago un select vago a la tabla power meter pd:esto debe ser
//                   por id solo q no me acuerdo..jeje y me da perecita buscar ;)*/  
//                   SelectQuery sel = new SelectQuery(PowerMeterInfo.class);
//                  /*guardo los resultados en una lista*/
//                   List listpmeter = con.performQuery(sel);
//
//                   for (int i = 0; i<listpmeter.size();i++)
//                   {
//                    PowerMeterInfo modpmeter= (PowerMeterInfo) listpmeter.get(i);
//                    /*modifico y comiteo :)*/    
//                    modpmeter.setDescription("Power Meter PM500 MG");
//                    con.modifiedObjects();
//                    con.commitChanges();
//                    System.out.println("descripcion cambiada:" + modpmeter.getDescription());
//                    System.out.println("Object Id:" + modpmeter.getObjectId());
//          
////           /*borrar objeto */
////                   /* hago un select a la tabla power meter*/  
////                   
////                   SelectQuery sel1 = new SelectQuery(PowerMeterInfo.class);
////                  /*guardo los resultados en una lista*/
////                   List listpmeterDel = con.performQuery(sel1);
////                   PowerMeterInfo delpmeter= (PowerMeterInfo) listpmeterDel.get(0);
////                   con.deleteObject(delpmeter);
////                   con.commitChanges();
                    

//        }
//
//
//       
//
   }
}
