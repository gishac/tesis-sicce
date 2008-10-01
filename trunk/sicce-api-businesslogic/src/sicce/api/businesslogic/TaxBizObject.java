/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.TaxDB;
import sicce.api.info.interfaces.ITax;

/**
 *
 * @author gish@c
 */
public class TaxBizObject {

    /**
     * Devuelve todos los rubros
     * @return Devuelve todos los rubros
     */
    public List<ITax> GetAllTaxes() {
        return TaxDB.GetAllTaxes();
    }
    
}
