/*
 * ReportCellRenderer.java
 *
 */
package sicce.api.businesslogic.renderer;


import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Modifica la manera de presentar la informacion.
 * @author Karu
 */
/**
 */
public class ReportCellRenderer extends JButton
                           implements TableCellRenderer {

    private Icon icon;
    public ReportCellRenderer(Icon icon)
    {
        super();
        this.icon = icon;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setIcon(icon);
        return this;
    }

}