/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import sicce.api.info.interfaces.ISimpleQueryResult;

/**
 * Representacion de los resultados de una consulta simple
 * @author karu
 */
public class SimpleQueryResult implements ISimpleQueryResult {
    
    /**
     * Resultado de la consulta
     */
    private String result;

    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
