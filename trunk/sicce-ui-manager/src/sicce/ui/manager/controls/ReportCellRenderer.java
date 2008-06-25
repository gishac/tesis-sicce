/*
 * ReportCellRenderer.java
 *
 */
package sicce.ui.manager.controls;


import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.application.ResourceMap;
import sicce.api.info.interfaces.IReport;

/**
 * Modifica la manera de presentar la informacion.
 * @author Karu
 */
/**
 */
public class ReportCellRenderer extends DefaultTableCellRenderer
{

    private IReport temporal;
    private ResourceMap resourceMap;
    
    
    /** Creates a new instance of ExportacionCellRenderer */
    public ReportCellRenderer()
    {
        super();
        ResourceMap resourceMap = getResourceMap();
    }
    
     /**
      * 
      * @param value 
      */
    @Override
    public void setValue(Object value) 
    {
        if (value == null)
          {
               this.setIcon (null);
               this.setText (null);
          }
          else if (value instanceof IReport)
          {
               temporal = (IReport)value;
               
               this.setIcon(getResourceMap().getIcon("report.png"));
               
          }
          else 
          { 
            this.setIcon (null);
            this.setText ((String)value);
          }
              
    }
    
      public ResourceMap getResourceMap() {
        return resourceMap;
    }

}