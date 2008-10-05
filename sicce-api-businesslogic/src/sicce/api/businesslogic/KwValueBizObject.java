/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.Date;
import java.util.List;
import sicce.api.dataaccess.KwValueDB;
import sicce.api.info.interfaces.IKwValue;

/**
 *
 * @author gish@c
 */
public class KwValueBizObject {

    /**
     * Devuelve todos los valores de kw/h
     * @return Devuelve todos los valores de kw/h
     */
    public List<IKwValue> GetAllKwValues() {
        return KwValueDB.GetAllKwValues();
    }
    
    /**
     * Devuelve el valor de kw/h para las fechas especificadas
     * @param startDate Fecha de inicio
     * @param endDate Fecha de fin
     * @return Valor de kw/h para las fechas especificadas
     */
    public IKwValue GetKWValueForDates(Date startDate, Date endDate){                
        List<IKwValue> result = KwValueDB.GetKWValueForDates(startDate, endDate);
        if(result.size() > 0)
            return result.get(0);
        return null;
    }
    
}
