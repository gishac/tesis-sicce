/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import sicce.api.info.interfaces.IMeasure;

/**
 *
 * @author gish@c
 */
public class MeasureDB {

    public static void Save(IMeasure measure){
        DataAccessManager.getInstance().getMeasureDB().save(measure);
    }
    
}
