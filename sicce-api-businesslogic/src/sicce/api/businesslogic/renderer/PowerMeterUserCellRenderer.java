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
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class PowerMeterUserCellRenderer extends JLabel implements TableCellRenderer {

    private IUserSicce currentUser;

    public IUserSicce getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(IUserSicce currentUser) {
        this.currentUser = currentUser;
    }

    public PowerMeterUserCellRenderer() {
        super();
        this.setOpaque(true);
    }

    /**
     * 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        SicceTableModel<IPowerMeter> tableModel = (SicceTableModel<IPowerMeter>) table.getModel();
        IPowerMeter powerMeter = tableModel.getRow(row);
        this.setText(value.toString());
        if (IsAssigned(powerMeter)) {
            IUserSicce user = powerMeter.getUsers().iterator().next();
            this.setToolTipText("El medidor ya se encuentra asignado al usuario " + user.getName() + " " + user.getLastname());
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
        if (powerMeter.getUsers().size() > 0) {
            IUserSicce user = powerMeter.getUsers().iterator().next();
            if (currentUser == null || !user.getID().equals(currentUser.getID())) {
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
