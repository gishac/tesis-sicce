/*
 * SicceuimanagerView.java
 */
package sicce.ui.manager.forms;

import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.AbstractSkyTheme;
import com.jgoodies.looks.plastic.theme.DarkStar;
import com.jgoodies.looks.plastic.theme.DesertBlue;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.plaf.LookAndFeelAddons;
import com.l2fprod.common.swing.plaf.aqua.AquaLookAndFeelAddons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;
import sicce.api.info.TaskInfo;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.JTabbedPaneExtended;

/**
 * The application's main frame.
 */
public class SicceuimanagerView extends FrameView {

    public SicceuimanagerView(SingleFrameApplication app) {
        super(app);

        ApplyLookAndFeel();
        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            //statusMessageLabel.setText("");
            }
        });

        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
            //statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        //statusAnimationLabel.setIcon(idleIcon);
        //progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        //statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                //progressBar.setVisible(true);
                //progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                //statusAnimationLabel.setIcon(idleIcon);
                //progressBar.setVisible(false);
                //progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    //String text = (String) (evt.getNewValue());
                    //statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                //int value = (Integer) (evt.getNewValue());
                //progressBar.setVisible(true);
                //progressBar.setIndeterminate(false);
                //progressBar.setValue(value);
                }
            }
        });
        OrganizeUIElements();
        CreateUIElements();
        SetFrameSize();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = SicceuimanagerApp.getApplication().getMainFrame();
            aboutBox = new SicceuimanagerAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        SicceuimanagerApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JMenuBar();
        toolBarItemNew = new javax.swing.JMenu();
        toolBarItemSave = new javax.swing.JMenu();
        toolBarItemEdit = new javax.swing.JMenu();
        toolBarItemDelete = new javax.swing.JMenu();
        toolBarItemSearch = new javax.swing.JMenu();
        toolBarItemBack = new javax.swing.JMenu();

        mainPanel.setName("mainPanel"); // NOI18N

        toolBar.setName("toolBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(SicceuimanagerView.class);
        toolBarItemNew.setIcon(resourceMap.getIcon("toolBarItemNew.icon")); // NOI18N
        toolBarItemNew.setText(resourceMap.getString("toolBarItemNew.text")); // NOI18N
        toolBarItemNew.setToolTipText(resourceMap.getString("toolBarItemNew.toolTipText")); // NOI18N
        toolBarItemNew.setEnabled(false);
        toolBarItemNew.setName("toolBarItemNew"); // NOI18N
        toolBar.add(toolBarItemNew);

        toolBarItemSave.setIcon(resourceMap.getIcon("toolBarItemSave.icon")); // NOI18N
        toolBarItemSave.setText(resourceMap.getString("toolBarItemSave.text")); // NOI18N
        toolBarItemSave.setToolTipText(resourceMap.getString("toolBarItemSave.toolTipText")); // NOI18N
        toolBarItemSave.setEnabled(false);
        toolBarItemSave.setName("toolBarItemSave"); // NOI18N
        toolBar.add(toolBarItemSave);

        toolBarItemEdit.setIcon(resourceMap.getIcon("toolBarItemEdit.icon")); // NOI18N
        toolBarItemEdit.setText(resourceMap.getString("toolBarItemEdit.text")); // NOI18N
        toolBarItemEdit.setToolTipText(resourceMap.getString("toolBarItemEdit.toolTipText")); // NOI18N
        toolBarItemEdit.setEnabled(false);
        toolBarItemEdit.setName("toolBarItemEdit"); // NOI18N
        toolBar.add(toolBarItemEdit);

        toolBarItemDelete.setIcon(resourceMap.getIcon("toolBarItemDelete.icon")); // NOI18N
        toolBarItemDelete.setText(resourceMap.getString("toolBarItemDelete.text")); // NOI18N
        toolBarItemDelete.setToolTipText(resourceMap.getString("toolBarItemDelete.toolTipText")); // NOI18N
        toolBarItemDelete.setEnabled(false);
        toolBarItemDelete.setName("toolBarItemDelete"); // NOI18N
        toolBar.add(toolBarItemDelete);

        toolBarItemSearch.setIcon(resourceMap.getIcon("toolBarItemSearch.icon")); // NOI18N
        toolBarItemSearch.setText(resourceMap.getString("toolBarItemSearch.text")); // NOI18N
        toolBarItemSearch.setToolTipText(resourceMap.getString("toolBarItemSearch.toolTipText")); // NOI18N
        toolBarItemSearch.setEnabled(false);
        toolBarItemSearch.setName("toolBarItemSearch"); // NOI18N
        toolBar.add(toolBarItemSearch);

        toolBarItemBack.setIcon(resourceMap.getIcon("toolBarItemBack.icon")); // NOI18N
        toolBarItemBack.setText(resourceMap.getString("toolBarItemBack.text")); // NOI18N
        toolBarItemBack.setToolTipText(resourceMap.getString("toolBarItemBack.toolTipText")); // NOI18N
        toolBarItemBack.setName("toolBarItemBack"); // NOI18N
        toolBar.add(toolBarItemBack);

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar toolBar;
    private javax.swing.JMenu toolBarItemBack;
    private javax.swing.JMenu toolBarItemDelete;
    private javax.swing.JMenu toolBarItemEdit;
    private javax.swing.JMenu toolBarItemNew;
    private javax.swing.JMenu toolBarItemSave;
    private javax.swing.JMenu toolBarItemSearch;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private JTabbedPaneExtended tabManager;
    private JTaskPane taskPaneManager;
    private List<TaskInfo> tasks;
    

    
    /**
     * gish@c
     * Retorna la instancia del control que administra los tabs
     */
    public JTabbedPaneExtended getTabManager() {
        if(tabManager == null)
            tabManager = new JTabbedPaneExtended();
        return tabManager;
    }
    
    /**
     * gish@c
     * Retorna la instancia del control que administra el panel de tareas
     */
    public JTaskPane getTaskPaneManager() {
        if(taskPaneManager == null)
        {
            taskPaneManager = new JTaskPane();
            taskPaneManager.setPreferredSize(new Dimension(200,Toolkit.getDefaultToolkit().getScreenSize().height));
        }
        return taskPaneManager;
    }
    
    /**
     * gish@c
     * Crea y agrega las opciones en el panel de tareas
     */
    private void CreateTasks()
    {
        this.tasks = TaskInfo.getTasks();
        JTaskPaneGroup mainGroup = new JTaskPaneGroup();
        mainGroup.setTitle(getResourceMap().getString("TaskPane.GroupName", ""));
        mainGroup.setSpecial(true);
        for(TaskInfo task : this.tasks)
        {
            ImageIcon icon = null;
            task.setDescription(getResourceMap().getString(task.getDescription(), ""));
            mainGroup.add(getAction(task.getDescription(), task.getName(), icon));
        }
        getTaskPaneManager().add(mainGroup);
        getTaskPaneManager().revalidate();
        getTaskPaneManager().repaint();
    }
    
    /**
     * Crea todos los elementos visuales del formulario
     */
    private void CreateUIElements()
    {
        CreateTasks();
    }
    
    /**
     * gish@c
     * Coloca el formulario del tamaño maximo de la pantalla
     */
    private void SetFrameSize() throws HeadlessException {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();
        SicceuimanagerApp.getApplication().getMainFrame().setPreferredSize(dimension);
    }

    
    /**
     * gish@c
     * Aplica el estilo visual a todos los controles de UI
     */
    private void ApplyLookAndFeel() {
        try {
            
            UIManager.put("TaskPane.useGradient", Boolean.TRUE);
            UIManager.put("TaskPaneGroup.useGradient", Boolean.TRUE);
            UIManager.put("TaskPane.backgroundGradientStart",  Color.LIGHT_GRAY);
            UIManager.put("TaskPane.backgroundGradientEnd", Color.WHITE);
            UIManager.put("TaskPaneGroup.backgroundGradientStart", Color.LIGHT_GRAY);
            UIManager.put("TaskPaneGroup.backgroundGradientEnd", Color.WHITE);
            UIManager.put("TaskPaneGroup.background", Color.LIGHT_GRAY);
            UIManager.put("TaskPaneGroup.borderColor", Color.DARK_GRAY);
            UIManager.put("TaskPaneGroup.specialTitleForeground", Color.BLACK);
            UIManager.put("TaskPaneGroup.specialTitleBackground", Color.gray.brighter());
            PlasticLookAndFeel.setPlasticTheme(new com.jgoodies.looks.plastic.theme.DarkStar());            
            UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
            LookAndFeelAddons.setAddon(AquaLookAndFeelAddons.class);
            getTaskPaneManager().revalidate();
            getTaskPaneManager().repaint();
            
            
        } catch (Exception ex) {
            Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gish@c
     * Coloca los paneles principales en orden dentro del panel principal     
     */
    private void OrganizeUIElements() {
       this.getFrame().getContentPane().add(toolBar,BorderLayout.NORTH);
       this.getFrame().getContentPane().add(getTabManager(),BorderLayout.CENTER);
       this.getFrame().getContentPane().add(getTaskPaneManager(),BorderLayout.WEST);
       this.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }       
    
    /**
     * 
     * @param text
     * @param actionCommand
     * @param icon
     * @return
     */
    private javax.swing.Action getAction(String text,String actionCommand,ImageIcon icon)
    {
         javax.swing.Action event = new AbstractAction(text) {
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(null, "You clicked: " + e.getActionCommand(),"Message",JOptionPane.INFORMATION_MESSAGE);
            }
        };
        event.putValue(javax.swing.Action.SHORT_DESCRIPTION, text);     
        event.putValue(javax.swing.Action.ACTION_COMMAND_KEY, actionCommand);
        //event.putValue(javax.swing.Action.SMALL_ICON, icon);
        return event;
    }
          
}
