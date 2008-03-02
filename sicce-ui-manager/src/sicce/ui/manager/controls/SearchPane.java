/*
 * SearchPane.java
 *
 * Created on February 29, 2008, 12:05 AM
 */
package sicce.ui.manager.controls;

import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import sicce.api.businesslogic.SicceTableModel;
import sicce.api.info.ConstantsProvider.DialogResult;

/**
 *
 * @author  gish@c
 */
public class SearchPane<T> extends javax.swing.JPanel {

    private JDialog parentDialog;
    private DialogResult dialogResult;
    private T searchResult;
    SicceTableModel<T> tableModel;
    private final TableRowSorter<TableModel> rowSorter;

    public DialogResult getDialogResult() {
        return dialogResult;
    }

    /** Creates new form SearchPane */
    public SearchPane(SicceTableModel<T> tableModel) {
        initComponents();
        this.tableModel = tableModel;
        tableResults.setModel(tableModel);
        rowSorter = new TableRowSorter<TableModel>(tableModel);
        tableResults.setRowSorter(rowSorter);
        tableResults.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dialogResult = DialogResult.Cancel;
    }

    /** Creates new form SearchPane */
    public SearchPane(JDialog parentDialog, SicceTableModel<T> tableModel) {
        this(tableModel);
        this.parentDialog = parentDialog;

    }

    /**
     * Devuelve el elemento seleccionado en la busqueda
     * @return
     */
    public T getSearchResult() {
        if (searchResult == null) {
            JOptionPaneExtended.showMessageDialog(this, "Debe seleccionar un elemento de la lista");
        }
        return searchResult;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblSearch = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResults = new javax.swing.JTable();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(SearchPane.class);
        btnOk.setText(resourceMap.getString("btnOk.text")); // NOI18N
        btnOk.setName("btnOk"); // NOI18N
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N

        txtFilter.setText(resourceMap.getString("txtFilter.text")); // NOI18N
        txtFilter.setName("txtFilter"); // NOI18N
        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tableResults.setModel(new javax.swing.table.DefaultTableModel(
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
        tableResults.setName("tableResults"); // NOI18N
        jScrollPane1.setViewportView(tableResults);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        dialogResult = DialogResult.Ok;
        if (tableResults.getSelectedRow() > 0) {
            searchResult = ((SicceTableModel<T>) tableResults.getModel()).getRow(tableResults.getSelectedRow());
        }
        parentDialog.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        parentDialog.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        searchResult = null;
        String filter = txtFilter.getText();
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filter));
    }//GEN-LAST:event_txtFilterKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tableResults;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
