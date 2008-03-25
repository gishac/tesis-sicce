/*
 * SicceuimanagerView.java
 */
package sicce.ui.manager.forms;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
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
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import sicce.api.businesslogic.ClassFactory;
import sicce.api.info.ConstantsProvider.OptionsProvider;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.util.Validator;
import sicce.ui.manager.controls.JOptionPaneExtended;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.JTabbedPaneExtended;
import sicce.ui.manager.handlers.ToolBarHandler;

/**
 * The application's main frame.
 */
public class SicceuimanagerView extends FrameView {

    private IUserSicce currentUser;
    
    public SicceuimanagerView(SingleFrameApplication app, IUserSicce user){        
        this(app);
        this.currentUser = user;
        toolBarHandler = new ToolBarHandler(toolBar,getTabManager());
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();

        //statusAnimationLabel.setIcon(idleIcon);
        //progressBar.setVisible(false);
        OrganizeUIElements();
        CreateUIElements();        
        tabManager.setOptionsText(optionsText);
        tabManager.setResourceMap(resourceMap);
        Validator.Initialize(resourceMap.getString("ApplicationName"));
    }
    
    public SicceuimanagerView(SingleFrameApplication app) {

        super(app);
        ApplyLookAndFeel();
        SetFrameSize();
        initComponents();
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
        toolBar = new javax.swing.JToolBar();
        toolBarItemNew = new javax.swing.JButton();
        toolBarItemSave = new javax.swing.JButton();
        toolBarItemEdit = new javax.swing.JButton();
        toolBarItemDelete = new javax.swing.JButton();
        toolBarItemSearch = new javax.swing.JButton();
        toolBarItemBack = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N

        toolBar.setRollover(true);
        toolBar.setName("toolBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(SicceuimanagerView.class);
        toolBarItemNew.setIcon(resourceMap.getIcon("New.icon")); // NOI18N
        toolBarItemNew.setText(resourceMap.getString("toolBarItemNew.text")); // NOI18N
        toolBarItemNew.setToolTipText(resourceMap.getString("toolBarItemNew.toolTipText")); // NOI18N
        toolBarItemNew.setEnabled(false);
        toolBarItemNew.setFocusable(false);
        toolBarItemNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarItemNew.setIconTextGap(0);
        toolBarItemNew.setInheritsPopupMenu(true);
        toolBarItemNew.setMaximumSize(new java.awt.Dimension(29, 29));
        toolBarItemNew.setMinimumSize(new java.awt.Dimension(29, 29));
        toolBarItemNew.setName("toolBarItemNew"); // NOI18N
        toolBarItemNew.setPreferredSize(new java.awt.Dimension(29, 29));
        toolBarItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarItemNewActionPerformed(evt);
            }
        });
        toolBar.add(toolBarItemNew);

        toolBarItemSave.setIcon(resourceMap.getIcon("toolBarItemSave.icon")); // NOI18N
        toolBarItemSave.setToolTipText(resourceMap.getString("toolBarItemSave.toolTipText")); // NOI18N
        toolBarItemSave.setEnabled(false);
        toolBarItemSave.setFocusable(false);
        toolBarItemSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarItemSave.setIconTextGap(0);
        toolBarItemSave.setInheritsPopupMenu(true);
        toolBarItemSave.setMaximumSize(new java.awt.Dimension(29, 29));
        toolBarItemSave.setMinimumSize(new java.awt.Dimension(29, 29));
        toolBarItemSave.setName("toolBarItemSave"); // NOI18N
        toolBarItemSave.setPreferredSize(new java.awt.Dimension(29, 29));
        toolBarItemSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarItemSaveActionPerformed(evt);
            }
        });
        toolBar.add(toolBarItemSave);

        toolBarItemEdit.setIcon(resourceMap.getIcon("toolBarItemEdit.icon")); // NOI18N
        toolBarItemEdit.setToolTipText(resourceMap.getString("toolBarItemEdit.toolTipText")); // NOI18N
        toolBarItemEdit.setEnabled(false);
        toolBarItemEdit.setFocusable(false);
        toolBarItemEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarItemEdit.setIconTextGap(0);
        toolBarItemEdit.setInheritsPopupMenu(true);
        toolBarItemEdit.setMaximumSize(new java.awt.Dimension(29, 29));
        toolBarItemEdit.setMinimumSize(new java.awt.Dimension(29, 29));
        toolBarItemEdit.setName("toolBarItemEdit"); // NOI18N
        toolBarItemEdit.setPreferredSize(new java.awt.Dimension(29, 29));
        toolBarItemEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarItemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarItemEditActionPerformed(evt);
            }
        });
        toolBar.add(toolBarItemEdit);

        toolBarItemDelete.setIcon(resourceMap.getIcon("toolBarItemDelete.icon")); // NOI18N
        toolBarItemDelete.setToolTipText(resourceMap.getString("toolBarItemDelete.toolTipText")); // NOI18N
        toolBarItemDelete.setEnabled(false);
        toolBarItemDelete.setFocusable(false);
        toolBarItemDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarItemDelete.setIconTextGap(0);
        toolBarItemDelete.setInheritsPopupMenu(true);
        toolBarItemDelete.setMaximumSize(new java.awt.Dimension(29, 29));
        toolBarItemDelete.setMinimumSize(new java.awt.Dimension(29, 29));
        toolBarItemDelete.setName("toolBarItemDelete"); // NOI18N
        toolBarItemDelete.setPreferredSize(new java.awt.Dimension(29, 29));
        toolBarItemDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarItemDeleteActionPerformed(evt);
            }
        });
        toolBar.add(toolBarItemDelete);

        toolBarItemSearch.setIcon(resourceMap.getIcon("toolBarItemSearch.icon")); // NOI18N
        toolBarItemSearch.setToolTipText(resourceMap.getString("toolBarItemSearch.toolTipText")); // NOI18N
        toolBarItemSearch.setEnabled(false);
        toolBarItemSearch.setFocusable(false);
        toolBarItemSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarItemSearch.setIconTextGap(0);
        toolBarItemSearch.setInheritsPopupMenu(true);
        toolBarItemSearch.setMaximumSize(new java.awt.Dimension(29, 29));
        toolBarItemSearch.setMinimumSize(new java.awt.Dimension(29, 29));
        toolBarItemSearch.setName("toolBarItemSearch"); // NOI18N
        toolBarItemSearch.setPreferredSize(new java.awt.Dimension(29, 29));
        toolBarItemSearch.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarItemSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarItemSearchActionPerformed(evt);
            }
        });
        toolBar.add(toolBarItemSearch);

        toolBarItemBack.setIcon(resourceMap.getIcon("toolBarItemBack.icon")); // NOI18N
        toolBarItemBack.setToolTipText(resourceMap.getString("toolBarItemBack.toolTipText")); // NOI18N
        toolBarItemBack.setEnabled(false);
        toolBarItemBack.setFocusable(false);
        toolBarItemBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarItemBack.setIconTextGap(0);
        toolBarItemBack.setInheritsPopupMenu(true);
        toolBarItemBack.setMaximumSize(new java.awt.Dimension(29, 29));
        toolBarItemBack.setMinimumSize(new java.awt.Dimension(29, 29));
        toolBarItemBack.setName("toolBarItemBack"); // NOI18N
        toolBarItemBack.setPreferredSize(new java.awt.Dimension(29, 29));
        toolBarItemBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarItemBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarItemBackActionPerformed(evt);
            }
        });
        toolBar.add(toolBarItemBack);

        mainPanel.add(toolBar);

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents
    private void toolBarItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarItemNewActionPerformed
        try {
            toolBarHandler.ToolBarStateChanged(new ToolBarEventObject(toolBar, ToolBarAction.New));//GEN-LAST:event_toolBarItemNewActionPerformed
        } catch (Exception ex) {
            Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void toolBarItemSaveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ToolBarEventObject saveEventObject = new ToolBarEventObject(toolBar, ToolBarAction.Save);
            toolBarHandler.ToolBarStateChanged(saveEventObject);           
        } catch (Exception ex) {
            Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void toolBarItemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarItemEditActionPerformed
        try {
            toolBarHandler.ToolBarStateChanged(new ToolBarEventObject(toolBar, ToolBarAction.Edit));//GEN-LAST:event_toolBarItemEditActionPerformed
        } catch (Exception ex) {
            Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void toolBarItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarItemDeleteActionPerformed

        int result = joptionPaneExtended.ShowConfirmDialog(getResourceMap().getString("DeleteConfirmDialog"), getResourceMap().getString("ApplicationName"));
        if (result == JOptionPaneExtended.YES_OPTION) {
            try {
                ToolBarEventObject deleteEventObject = new ToolBarEventObject(toolBar, ToolBarAction.Delete);
                toolBarHandler.ToolBarStateChanged(deleteEventObject);
            } catch (Exception ex) {
                Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
}//GEN-LAST:event_toolBarItemDeleteActionPerformed

    private void toolBarItemSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarItemSearchActionPerformed
        try {

            toolBarHandler.ToolBarStateChanged(new ToolBarEventObject(toolBar, ToolBarAction.Search));
        } catch (Exception ex) {
            Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_toolBarItemSearchActionPerformed

    private void toolBarItemBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarItemBackActionPerformed
        try {
            toolBarHandler.ToolBarStateChanged(new ToolBarEventObject(toolBar, ToolBarAction.Back));//GEN-LAST:event_toolBarItemBackActionPerformed
        } catch (Exception ex) {
            Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton toolBarItemBack;
    private javax.swing.JButton toolBarItemDelete;
    private javax.swing.JButton toolBarItemEdit;
    private javax.swing.JButton toolBarItemNew;
    private javax.swing.JButton toolBarItemSave;
    private javax.swing.JButton toolBarItemSearch;
    // End of variables declaration//GEN-END:variables
    private JDialog aboutBox;
    private JTabbedPaneExtended tabManager;
    private JTaskPane taskPaneManager;
    private List<IOptionSicce> options;
    private ToolBarHandler toolBarHandler;
    private JOptionPaneExtended joptionPaneExtended;
    private RolePane rolePane;
    private UserPane userPane;
    private PowerMeterPane pmeterPane;
    private LocationTypePane lTypePane;
    private LocationPane locationPane;
    private ZonePane zonePane;
    private ParameterPane parameterPane;
    private String[] optionsText;
   

    /**
     * gish@c
     * Retorna la instancia del control que administra los tabs
     */
    public JTabbedPaneExtended getTabManager() {
        if (tabManager == null) {
            tabManager = new JTabbedPaneExtended();
        }
        return tabManager;
    }

    /**
     * gish@c
     * Retorna la instancia del control que administra el panel de tareas
     */
    public JTaskPane getTaskPaneManager() {
        if (taskPaneManager == null) {
            taskPaneManager = new JTaskPane();
            taskPaneManager.setPreferredSize(new Dimension(200, Toolkit.getDefaultToolkit().getScreenSize().height));
        }
        return taskPaneManager;
    }

    /**
     * gish@c
     * Crea y agrega las opciones en el panel de tareas
     */
    private void CreateTasks() {
        JTaskPaneGroup mainGroup = new JTaskPaneGroup();
        mainGroup.setTitle(getResourceMap().getString("TaskPane.GroupName", ""));
        mainGroup.setSpecial(true);
        for(IOptionSicce option : currentUser.getRole().getPermissions()){
            ImageIcon icon = null;
            mainGroup.add(getAction(option.getDescription(), option.getActionCommand(), icon));
        }
        getTaskPaneManager().add(mainGroup);
        getTaskPaneManager().revalidate();
        getTaskPaneManager().repaint();
        
        /*IOptionSicce a = ClassFactory.getOptionInstance();
        a.setDescription("Role");

        IOptionSicce b = ClassFactory.getOptionInstance();
        b.setDescription("User");
        
        IOptionSicce c = ClassFactory.getOptionInstance();
        c.setDescription("PowerMeter");

        IOptionSicce d = ClassFactory.getOptionInstance();
        d.setDescription("LocationType");
        
        IOptionSicce e = ClassFactory.getOptionInstance();
        e.setDescription("Location");
        
        IOptionSicce f = ClassFactory.getOptionInstance();
        f.setDescription("Zone");
        
        IOptionSicce g = ClassFactory.getOptionInstance();
        g.setDescription("Parameter");
        
        this.options = new ArrayList<IOptionSicce>();
        options.add(a);
        options.add(b);
        options.add(c);
        options.add(d);
        options.add(e);
        options.add(f);
        options.add(g);
      
        
        for (IOptionSicce option : this.options) {
            ImageIcon icon = null;
            mainGroup.add(getAction(option.getDescription(), option.getDescription(), icon));
        }
        getTaskPaneManager().add(mainGroup);
        getTaskPaneManager().revalidate();
        getTaskPaneManager().repaint();*/
    }

    /**
     * Crea todos los elementos visuales del formulario
     */
    private void CreateUIElements() {
        CreateTasks();
        optionsText = new String[3];
        optionsText[0] = getResourceMap().getString("JOptionPaneYes");
        optionsText[1] = getResourceMap().getString("JOptionPaneNo");
        optionsText[2] = getResourceMap().getString("JOptionPaneCancel");
        joptionPaneExtended = new JOptionPaneExtended(optionsText);
        CreateForms();
    }

    /**
     * gish@c
     * Coloca el formulario del tamaÃ±o maximo de la pantalla
     */
    private void SetFrameSize() throws HeadlessException {
        Dimension dimension = this.getFrame().getToolkit().getScreenSize();
        this.getFrame().setSize(dimension);
    }

    /**
     * gish@c
     * Aplica el estilo visual a todos los controles de UI
     */
    private void ApplyLookAndFeel() {
        try {
           
            /*UIManager.put("TaskPane.useGradient", Boolean.TRUE);
            UIManager.put("TaskPaneGroup.useGradient", Boolean.TRUE);
            UIManager.put("TaskPane.backgroundGradientStart", Color.LIGHT_GRAY);
            UIManager.put("TaskPane.backgroundGradientEnd", Color.WHITE);
            UIManager.put("TaskPaneGroup.backgroundGradientStart", Color.LIGHT_GRAY);
            UIManager.put("TaskPaneGroup.backgroundGradientEnd", Color.WHITE);
            UIManager.put("TaskPaneGroup.background", Color.LIGHT_GRAY);
            UIManager.put("TaskPaneGroup.borderColor", Color.LIGHT_GRAY);
            //UIManager.put("TaskPaneGroup.specialTitleForeground", Color.BLACK);
            //UIManager.put("TaskPaneGroup.specialTitleBackground", Color.LIGHT_GRAY);
            //PlasticLookAndFeel.setPlasticTheme(new com.jgoodies.looks.plastic.theme.DarkStar());
            UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
            LookAndFeelAddons.setAddon(AquaLookAndFeelAddons.class);
            //getTaskPaneManager().revalidate();
            //getTaskPaneManager().repaint();
            */

        } catch (Exception ex) {
            Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gish@c
     * Coloca los paneles principales en orden dentro del panel principal     
     */
    private void OrganizeUIElements() {
        this.getFrame().getContentPane().add(toolBar, BorderLayout.NORTH);
        this.getFrame().getContentPane().add(getTabManager(), BorderLayout.CENTER);
        this.getFrame().getContentPane().add(getTaskPaneManager(), BorderLayout.WEST);
        this.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 
     * @param text
     * @param actionCommand
     * @param icon
     * @return
     */
    private javax.swing.Action getAction(final String text, String actionCommand, ImageIcon icon) {
        javax.swing.Action event = new AbstractAction(text) {

            public void actionPerformed(ActionEvent e) {
                OptionsProvider option = Enum.valueOf(OptionsProvider.class, e.getActionCommand());
                JTabExtended selectedOption = GetForm(option);               
                if (!getTabManager().getTabs().contains(selectedOption)) {
                    selectedOption.setTitle(text);
                    getTabManager().AddTab(selectedOption);
                }
                if (!getTabManager().getCurrentTab().equals(selectedOption)) {
                    try {
                        //getTabManager().setCurrentTab(selectedOption);
                        //toolBarHandler.SetDefaultState();
                        getTabManager().HandleTabChanging(selectedOption);
                    } catch (Exception ex) {
                        Logger.getLogger(SicceuimanagerView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        event.putValue(javax.swing.Action.SHORT_DESCRIPTION, text);
        event.putValue(javax.swing.Action.ACTION_COMMAND_KEY, actionCommand);
        //event.putValue(javax.swing.Action.SMALL_ICON, icon);
        return event;
    }

    /**
     * Crea las instancias de todos los formularios
     */
    private void CreateForms() {
        rolePane = new RolePane();
        userPane = new UserPane();
        pmeterPane = new PowerMeterPane();
        lTypePane = new LocationTypePane();
        locationPane = new LocationPane();
        zonePane = new ZonePane();
        parameterPane = new ParameterPane();
       
        toolBarHandler.AddToolBarStateListener(rolePane);
        toolBarHandler.AddToolBarStateListener(userPane);
        toolBarHandler.AddToolBarStateListener(pmeterPane);
        toolBarHandler.AddToolBarStateListener(lTypePane);
        toolBarHandler.AddToolBarStateListener(locationPane);
        toolBarHandler.AddToolBarStateListener(zonePane);
        toolBarHandler.AddToolBarStateListener(parameterPane);
        
    }

    /**
     * 
     */
    private JTabExtended GetForm(OptionsProvider option) {
        JTabExtended result = null;
        switch (option) {
            case Role:
                result = rolePane;
                break;
            case User:
                result = userPane;
                break;
            case PowerMeter:
                result = pmeterPane;
                break;
            case LocationType:
                result = lTypePane;
                break;
            case Location:
                result = locationPane;
                break;
            case Zone:
                result = zonePane;
                break;
            case Parameter:
                result = parameterPane;
                break;
            
        }
        return result;
    }
   
}
