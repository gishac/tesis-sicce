/*
 * ReportForm3.java
 *
 * Created on 1 de abril de 2008, 11:43 PM
 */
package sicce.wizard.report.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPage;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.businesslogic.model.SicceComboBoxModel;
import sicce.api.info.Field;
import sicce.wizard.reports.models.FieldHandler;
import sicce.api.businesslogic.renderer.FieldsCellRenderer;
import sicce.ui.manager.listeners.JTableButtonMouseListener;
import sicce.api.businesslogic.renderer.JTableButtonRenderer;
import sicce.api.businesslogic.model.ReportFilterTableModel;
import sicce.api.info.interfaces.IFilter;
import sicce.ui.manager.controls.JOptionPaneExtended;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author  Karu
 */
public class ReportForm4 extends WizardPage {

    private final WizardController controller;
    private final Map wizardData;
    public static final String KEY_FIELD = "KeyField";
    public static final String VALUE_FIELDS = "ValueField";
    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";
    public static final String KEY_BL_CHART = "blChart";
    public static final String KEY_FIELD_CHART = "FieldChart";
    public static final String KEY_BL_IS_LOADED = "isloaded";
    private FieldHandler pFieldHandler = new FieldHandler();
    SicceComboBoxModel selectedComboBoxModel;
    SicceComboBoxModel chartComboBoxModel;
    private ReportFilterTableModel reportTableModel;
    JComboBox operator;
    JButton searchField;
    TableCellRenderer defaultRenderer;
    Boolean isloaded = false;

    public ReportForm4(WizardController controller, Map wizardData, ResourceMap resourceMap) {
        initComponents();
        this.controller = controller;
        this.wizardData = wizardData;
        pFieldHandler = (FieldHandler) wizardData.get(KEY_FIELD);
        List measures = pFieldHandler.getMeasureSelected(pFieldHandler.getSelectedFields());
        isloaded = (wizardData.get(KEY_BL_IS_LOADED)==null?false:(Boolean)wizardData.get(KEY_BL_IS_LOADED));
        pFieldHandler.setLstMeasureFields(measures);
        if (isloaded){
        Date starttmp = (Date) wizardData.get(KEY_BEGIN_DATE);
        Date endtmp = (Date) wizardData.get(KEY_FINISH_DATE);
        Boolean chart = (Boolean) wizardData.get(KEY_BL_CHART);
        Field fieldChart = (Field) wizardData.get(KEY_FIELD_CHART);
        setFromMap(starttmp, endtmp, fieldChart, chart);
        }
        
        List filterList = (List) wizardData.get(KEY_WHERE);
        fillGrid(resourceMap, filterList);
        LoadComboBox();
        addJDateListener();

    }

    public void addJDateListener() {
        dtpBeginDate.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

                if (validateDates(dtpBeginDate.getDate(), dtpFinishDate.getDate())) {
                    controller.setForwardNavigationMode(WizardController.MODE_CAN_FINISH);
                    controller.setProblem(null);
                } else {
                    controller.setProblem("Defina la fecha de fin del reporte...");
                }
            }
        });

        dtpFinishDate.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

                if (validateDates(dtpBeginDate.getDate(), dtpFinishDate.getDate())) {
                    controller.setForwardNavigationMode(WizardController.MODE_CAN_FINISH);
                    controller.setProblem(null);
                } else {
                    controller.setProblem("Defina correctamente las fechas del reporte.");
                }
            }
        });
    }

    private void LoadComboBox() {
        selectedComboBoxModel = new SicceComboBoxModel(pFieldHandler.fillfilterFields());
        cboWhereItems.setModel(selectedComboBoxModel);
        cboWhereItems.setRenderer(new FieldsCellRenderer());
    }

    public void fillGrid(ResourceMap resourceMap, List filterList) {
        Icon imgSearch = resourceMap.getIcon("search");
        reportTableModel = new ReportFilterTableModel(filterList, imgSearch);
        grdSearchFields.setModel(reportTableModel);
        TableColumn operatorColumn = grdSearchFields.getColumnModel().getColumn(1);
        TableColumn searchColumn = grdSearchFields.getColumnModel().getColumn(3);
        operatorColumn.setCellEditor(new DefaultCellEditor(getComboBox()));
        searchColumn.setPreferredWidth(10);

        defaultRenderer = grdSearchFields.getDefaultRenderer(JButton.class);
        grdSearchFields.setDefaultRenderer(JButton.class,
                new JTableButtonRenderer(defaultRenderer));

        grdSearchFields.addMouseListener(new JTableButtonMouseListener(grdSearchFields));


    }

    public void setFromMap(Date starttmp, Date endtmp, Field fieldChart, Boolean chart) {
        if (starttmp != null) {
            dtpBeginDate.setDate(starttmp);
        }
        if (endtmp != null) {
            dtpFinishDate.setDate(endtmp);  
        }  
        if (chart != null) {
            chkChart.setSelected(chart);
        }
        if (fieldChart != null) {
            chartComboBoxModel = new SicceComboBoxModel(pFieldHandler.getListGroupFields());
            cboGroupChart.setModel(chartComboBoxModel);
            cboGroupChart.setRenderer(new FieldsCellRenderer());
            cboGroupChart.setSelectedItem(fieldChart);
        }
       

    }

    @Override
    protected String validateContents(Component component, Object event) {

        if(isloaded){
         return null;
        }
        
        if (component == null) {
            return null;
        }
        
            wizardData.put(KEY_WHERE, reportTableModel.getDataSource());
            wizardData.put(KEY_FIELD_CHART, cboGroupChart.getSelectedItem());
            wizardData.put(KEY_BL_CHART, chkChart.isSelected());
        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboWhereItems = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btnAddFilter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdSearchFields = new javax.swing.JTable();
        btndelFilter = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dtpBeginDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        dtpFinishDate = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        chkChart = new javax.swing.JCheckBox();
        cboGroupChart = new javax.swing.JComboBox();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportForm4.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboWhereItems.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboWhereItems.setName("cboWhereItems"); // NOI18N
        jPanel1.add(cboWhereItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 170, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        btnAddFilter.setIcon(resourceMap.getIcon("btnAddFilter.icon")); // NOI18N
        btnAddFilter.setText(resourceMap.getString("btnAddFilter.text")); // NOI18N
        btnAddFilter.setName("btnAddFilter"); // NOI18N
        btnAddFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFilterActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 40, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        grdSearchFields.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Campo", "Operador", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grdSearchFields.setName("grdSearchFields"); // NOI18N
        jScrollPane1.setViewportView(grdSearchFields);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 410, 130));

        btndelFilter.setIcon(resourceMap.getIcon("btndelFilter.icon")); // NOI18N
        btndelFilter.setText(resourceMap.getString("btndelFilter.text")); // NOI18N
        btndelFilter.setName("btndelFilter"); // NOI18N
        btndelFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelFilterActionPerformed(evt);
            }
        });
        jPanel1.add(btndelFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 40, 25));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 230));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rango de Fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 204)));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        dtpBeginDate.setName("dtpBeginDate"); // NOI18N
        jPanel2.add(dtpBeginDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 100, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, 20));

        dtpFinishDate.setName("dtpFinishDate"); // NOI18N
        jPanel2.add(dtpFinishDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 100, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 450, 50));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Gráficos")));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkChart.setText(resourceMap.getString("chkChart.text")); // NOI18N
        chkChart.setName("chkChart"); // NOI18N
        chkChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkChartActionPerformed(evt);
            }
        });
        jPanel3.add(chkChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cboGroupChart.setEnabled(false);
        cboGroupChart.setName("cboGroupChart"); // NOI18N
        cboGroupChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGroupChartActionPerformed(evt);
            }
        });
        jPanel3.add(cboGroupChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 160, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 450, 50));
    }// </editor-fold>//GEN-END:initComponents
    private void btnAddFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFilterActionPerformed


        if (cboWhereItems != null) {
            Field fieldSelected = (Field) cboWhereItems.getSelectedItem();

            IFilter filter = ClassFactory.getFilterInstance();
            filter.setField(fieldSelected);
            if (!reportTableModel.addFilter(filter)) {
                JOptionPaneExtended.showMessageDialog(null, "El campo ya fue agregado");
            }

        }
            
}//GEN-LAST:event_btnAddFilterActionPerformed

    private void btndelFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelFilterActionPerformed

        if (grdSearchFields.getSelectedRow() >= 0) {
            reportTableModel.deleteFilter(grdSearchFields.getSelectedRow());
        }
    }//GEN-LAST:event_btndelFilterActionPerformed

    private void chkChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkChartActionPerformed
        if (chkChart.isSelected()) {
            cboGroupChart.setEnabled(true);
            chartComboBoxModel = new SicceComboBoxModel(pFieldHandler.getListGroupFields());
            cboGroupChart.setModel(chartComboBoxModel);
            cboGroupChart.setRenderer(new FieldsCellRenderer());
            wizardData.put(KEY_BL_CHART, chkChart.isSelected());
            wizardData.put(KEY_FIELD_CHART, cboGroupChart.getSelectedItem());
        } else {
            cboGroupChart.setEnabled(false);
            wizardData.put(KEY_BL_CHART, chkChart.isSelected());
        }
    }//GEN-LAST:event_chkChartActionPerformed

    private void cboGroupChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGroupChartActionPerformed
       
    }//GEN-LAST:event_cboGroupChartActionPerformed

    private JComboBox getComboBox() {
        if (operator == null) {
            operator = new JComboBox();
            operator.addItem("igual");
            operator.addItem("menor");
            operator.addItem("mayor");
            operator.addItem("diferente");
            operator.addItem("entre");
            operator.addItem("contiene");
        }

        return operator;
    }

    private boolean validateDates(Date begin, Date finish) {
        if (begin != null && finish != null) {
            if (begin.after(finish)) {
                controller.setProblem("La fecha de inicio no puede ser mayor a la fecha de fin...");
                return false;
            }

            if (finish.before(begin)) {
                controller.setProblem("La fecha de fin no puede ser menor a la fecha de inicio...");
                return false;
            }
            wizardData.put(KEY_BEGIN_DATE, dtpBeginDate.getDate());
            wizardData.put(KEY_FINISH_DATE, dtpFinishDate.getDate());
            return true;
        }


        return false;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFilter;
    private javax.swing.JButton btndelFilter;
    private javax.swing.JComboBox cboGroupChart;
    private javax.swing.JComboBox cboWhereItems;
    private javax.swing.JCheckBox chkChart;
    private com.toedter.calendar.JDateChooser dtpBeginDate;
    private com.toedter.calendar.JDateChooser dtpFinishDate;
    private javax.swing.JTable grdSearchFields;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
