/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.Parameter;
import sicce.api.info.interfaces.IParameter;

/**
 *
 * @author gish@c
 */
public class ParameterDB {

    public static void Save(IParameter parameter){
        DataAccessManager.getInstance().getParameterDB().save((Parameter)parameter);
    }
    
    public static void Update(IParameter parameter){
        DataAccessManager.getInstance().getParameterDB().update((Parameter)parameter);
    }
    
    public static List<IParameter> GetAllParameters(){
        return DataAccessManager.getInstance().getParameterDB().findAll();
    }
    
    public static IParameter GetParameterByKey(String parameterKey) throws Exception{
        List<IParameter> result = DataAccessManager.getInstance().getParameterDB().findByDescription(parameterKey);
        if(result == null || result.size() <= 0)
            throw new Exception("No se encontro el parametro " + parameterKey + ". Por favor comuniquese con el Adminsitrador del Sistema");
        return result.get(0);
    }
    
    
}
