/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.controls;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import org.jdesktop.application.ResourceMap;
import sicce.api.businesslogic.ReportCellRenderer;

/**
 *
 * @author gish@c
 */
public class ReportTable extends JTable {

    ResourceMap resourceMap;
    ReportCellRenderer renderer;

    public ResourceMap getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(ResourceMap resourceMap) {
        this.resourceMap = resourceMap;
    }

    public ReportTable(TableModel dm) {
    }

    public ReportTable() {
    }

    public ReportTable(ResourceMap resourceMap) {
        this.resourceMap = resourceMap;
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        if (column == 3) {
            if (renderer == null) {
                renderer = new ReportCellRenderer(resourceMap.getIcon("reportIcon"));
            }
            return renderer;
        }
        return super.getCellRenderer(row, column);
    }
}
