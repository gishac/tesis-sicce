/*  The contents of this file are subject to the terms of the Common Development
and Distribution License (the License). You may not use this file except in
compliance with the License.
    You can obtain a copy of the License at http://www.netbeans.org/cddl.html
or http://www.netbeans.org/cddl.txt.
    When distributing Covered Code, include this CDDL Header Notice in each file
and include the License file at http://www.netbeans.org/cddl.txt.
If applicable, add the following below the CDDL Header, with the fields
enclosed by brackets [] replaced by your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]" */
package sicce.wizard.report.panels;

import java.util.Map;
import javax.swing.JRadioButton;
import org.netbeans.spi.wizard.WizardController;

/**
 *
 * @author  Timothy Boudreau
 */
public class SpeciesPanel extends javax.swing.JPanel {
    public static final String KEY_SPECIES = "species";
    public static final String VALUE_CAT = "cat";
    public static final String VALUE_DOG = "dog";
    public static final String VALUE_GERBIL = "gerbil";
    
    
    private final WizardController controller;
    private final Map wizardData;
    
    /** Creates new form SpeciesPanel */
    public SpeciesPanel(WizardController controller, Map wizardData) {
        initComponents();
        this.controller = controller;
        this.wizardData = wizardData;
        
        //By default, nothing is selected
        controller.setProblem ("No species selected");
        
        //Associate the values with the radio buttons, so the event handler
        //can be very simple
        catButton.putClientProperty (KEY_SPECIES, VALUE_CAT);
        dogButton.putClientProperty (KEY_SPECIES, VALUE_DOG);
        gerbilButton.putClientProperty (KEY_SPECIES, VALUE_GERBIL);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        radioButtons = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        catButton = new javax.swing.JRadioButton();
        dogButton = new javax.swing.JRadioButton();
        gerbilButton = new javax.swing.JRadioButton();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setText("What kind of pet would you like?");
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(3, 1));

        jPanel1.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 12, 1, 1)));
        radioButtons.add(catButton);
        catButton.setText("Cat");
        catButton.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
        catButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        catButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speciesSelected(evt);
            }
        });

        jPanel1.add(catButton);

        radioButtons.add(dogButton);
        dogButton.setText("Dog");
        dogButton.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
        dogButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speciesSelected(evt);
            }
        });

        jPanel1.add(dogButton);

        radioButtons.add(gerbilButton);
        gerbilButton.setText("Gerbil");
        gerbilButton.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
        gerbilButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gerbilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speciesSelected(evt);
            }
        });

        jPanel1.add(gerbilButton);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents

    private void speciesSelected(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speciesSelected
        JRadioButton button = (JRadioButton) evt.getSource();
        wizardData.put (KEY_SPECIES, button.getClientProperty(KEY_SPECIES));
        controller.setProblem(null);
        if (button == gerbilButton) {
            controller.setForwardNavigationMode(controller.MODE_CAN_FINISH);
        } else {
            controller.setForwardNavigationMode(controller.MODE_CAN_CONTINUE);
        }
    }//GEN-LAST:event_speciesSelected
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton catButton;
    private javax.swing.JRadioButton dogButton;
    private javax.swing.JRadioButton gerbilButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.ButtonGroup radioButtons;
    // End of variables declaration//GEN-END:variables
    
}
