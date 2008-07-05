/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import sicce.api.info.interfaces.ISimpleQueryResult;

/**
 *
 * @author karu
 */
public class SimpleQueryResult implements ISimpleQueryResult {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
