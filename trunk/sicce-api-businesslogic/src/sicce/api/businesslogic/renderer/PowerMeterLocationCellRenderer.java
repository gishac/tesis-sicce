/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import sicce.api.businesslogic.model.SicceTableModel;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterLocationCellRenderer extends JLabel implements TableCellRenderer {

    private ILocation currentLocation;

    public ILocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentUser(ILocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public PowerMeterLocationCellRenderer() {
        super();
        this.setOpaque(true);
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        SicceTableModel<IPowerMeter> tableModel = (SicceTableModel<IPowerMeter>) table.getModel();
        IPowerMeter powerMeter = tableModel.getRow(row);
        this.setText(value.toString());
        if (IsAssigned(powerMeter)) {
            ILocation location = powerMeter.getLocations().iterator().next();
            this.setToolTipText("El medidor ya se encuentra asignado a la ubicacion " + location.getDescription());
            this.setBackground(Color.LIGHT_GRAY);
            this.setForeground(Color.BLACK);
            this.setFont(new Font(this.getFont().getName(), Font.BOLD, this.getFont().getSize()));
        } else {
            SetDefaultValues();
        }
        return this;
    }
    
     /**
     * 
     * @param powerMeter
     * @return
     */
    private boolean IsAssigned(IPowerMeter powerMeter) {
        if (powerMeter.getLocations().size() > 0) {
            ILocation location = powerMeter.getLocations().iterator().next();
            if (currentLocation == null || !location.getID().equals(currentLocation.getID())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 
     */
    private void SetDefaultValues() {
        this.setToolTipText(null);
        this.setBackground(Color.WHITE);
        this.setFont(new Font(this.getFont().getName(), Font.PLAIN, this.getFont().getSize()));
    }

}
