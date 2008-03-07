/*
 * ParameterPane.java
 *
 * Created on March 4, 2008, 11:58 PM
 */

package sicce.ui.manager.forms;

import java.util.List;
import sicce.api.businesslogic.ParameterBizObject;
import sicce.api.businesslogic.ParameterTableModel;
import sicce.api.info.ToolBarStateInfo;
import sicce.api.info.interfaces.IParameter;
import sicce.ui.manager.controls.JTabExtended;

/**
 *
 * @author  gish@c
 */
public class ParameterPane extends JTabExtended {
    
    private ParameterTableModel parameterTableModel;
    private ParameterBizObject parameterBizObject;
    private List<IParameter> parameters;
    
    /** Creates new form ParameterPane */
    public ParameterPane() {
        initComponents();
        parameterBizObject = new ParameterBizObject();
        this.setHandleToolBarStates(false);
        this.setToolBarStateInfo(new ToolBarStateInfo(false, true, false, false, false, false));
        this.getToolBarStateInfo().setAlwaysEnabledSave(true);
        FillGrid();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        parameterTable = new javax.swing.JTable();

        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        parameterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        parameterTable.setName("parameterTable"); // NOI18N
        jScrollPane1.setViewportView(parameterTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable parameterTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void FillGrid() {
        parameters = parameterBizObject.GetAllParameters();
        parameterTableModel = new ParameterTableModel(parameters);
        parameterTable.setModel(parameterTableModel);
    }

    @Override
    public boolean Save() throws Exception {        
        parameterBizObject.Update(parameters);
        return true; //Envia true para cancelar el edit
    }
    
}
