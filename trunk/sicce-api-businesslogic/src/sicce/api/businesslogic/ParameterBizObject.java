/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.ArrayList;
import java.util.List;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.info.interfaces.IParameter;

/**
 *
 * @author gish@c
 */
public class ParameterBizObject {

    
    
    private List<String> parametersCodesForMail;
    public List<String> getParameterCodesForMail() {
        return parametersCodesForMail;
    }

    public ParameterBizObject() {
        parametersCodesForMail = new ArrayList<String>();
        parametersCodesForMail.add("SMTP_SERVER");
        parametersCodesForMail.add("SMTP_PORT");
        parametersCodesForMail.add("MAIL_SENDER");
        parametersCodesForMail.add("MAIL_SENDER_PASSWORD");
        parametersCodesForMail.add("MAIL_USE_SSL");
    }

    public List<IParameter> GetAllParameters() {
        return ParameterDB.GetAllParameters();
    }

    public void Save(IParameter parameter) {
        ParameterDB.Save(parameter);
    }

    public void Save(List<IParameter> parameters) {
        for (IParameter parameter : parameters) {
            Save(parameter);
        }
    }

    public void Update(IParameter parameter) {
        ParameterDB.Update(parameter);
    }

    public void Update(List<IParameter> parameters) {
        for (IParameter parameter : parameters) {
            Update(parameter);
        }
    }
}
