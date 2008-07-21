package sicce.api.info;

// Generated by MyEclipse Persistence Tools
import java.util.Set;

import sicce.api.info.auto.AbstractPowerMeter;
import sicce.api.info.interfaces.IMeasure;

/**
 * PowerMeter generated by MyEclipse Persistence Tools
 */
public class PowerMeter extends AbstractPowerMeter implements
        java.io.Serializable {

    // Constructors
    /** default constructor */
    public PowerMeter() {
    }

    /** minimal constructor */
    public PowerMeter(Integer idPowerMeter) {
        super(idPowerMeter);
    }

    /** full constructor **/
    public PowerMeter(Integer idPowerMeter, String description,
            String ipAddress, Byte logStatus, String serial, Byte status2,
            Set<IMeasure> measures) {
        super(idPowerMeter, description, ipAddress, logStatus, serial, status2,
                measures);
    }

    
}
