/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.controls;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdesktop.application.ResourceMap;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.ui.manager.listeners.CloseTabListener;

/**
 *
 * @author gish@c
 */
public class JTabbedPaneExtended extends JTabbedPane {

    /**
     * Mantiene la instancia del tab actual
     */
    private ITabbedWindow currentTab;
    private ResourceMap resourceMap;
    private String[] optionsText;
    private boolean internalEvent;

    public String[] getOptionsText() {
        return optionsText;
    }

    public void setOptionsText(String[] optionsText) {
        this.optionsText = optionsText;
    }

    public ResourceMap getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(ResourceMap resourceMap) {
        this.resourceMap = resourceMap;
    }

    /** 
     *  Constructor
     */
    public JTabbedPaneExtended() {
        super();
        this.addMouseListener(new CloseTabListener(this));
        this.getModel().addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                try {
                    if (internalEvent) {
                        return;
                    }
                    DefaultSingleSelectionModel selectionModel = (DefaultSingleSelectionModel) e.getSource();
                    int tabIndex = selectionModel.getSelectedIndex();
                    HandleTabChanging(tabIndex,false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    /**
     * Cancela el cambio del tab
     */
    private void CancelTabChanging() {
        int previousTab = getCurrentTabIndex();
        internalEvent = true;
        setCurrentTab(previousTab);
        internalEvent = false;
    }

    /**
     * Administra el cambio de tabs
     * @param newTabIndex
     * @return
     * @throws java.lang.Exception
     */
    public boolean HandleTabChanging(int newTabIndex, boolean tabClosing) throws Exception {

        if (getCurrentTab() != null && (getCurrentTab().getTabState() == ToolBarAction.Edit || getCurrentTab().getTabState() == ToolBarAction.New)) {
            JOptionPaneExtended confirmDialog = new JOptionPaneExtended(optionsText);
            int result = confirmDialog.ShowConfirmDialog(getResourceMap().getString("SaveConfirmDialog"), getResourceMap().getString("ApplicationName"));
            switch (result) {
                case JOptionPaneExtended.YES_OPTION:
                    boolean cancelAction = getCurrentTab().Save();
                    if (cancelAction) {
                        CancelTabChanging();
                        return true;
                    }
                    getCurrentTab().SetDefaultState();
                    break;
                case JOptionPaneExtended.NO_OPTION:
                    getCurrentTab().RefreshToolBarState();
                    break;
                case JOptionPaneExtended.CANCEL_OPTION:
                    CancelTabChanging();
                    return true;
            }
        }
        if (!tabClosing)
            setCurrentTab(newTabIndex);
        if(getCurrentTab() != null)
            getCurrentTab().RefreshToolBarState();
        return false;
    }
    /**
     * Contiene la lista de tabs que aloja el control
     */
    private List<ITabbedWindow> tabs;

    public List<ITabbedWindow> getTabs() {
        if (tabs == null) {
            tabs = new ArrayList<ITabbedWindow>();
        }
        return tabs;
    }

    /**
     * Agrega un tab al panel
     * @param tab
     */
    public void AddTab(JTabExtended tab) {
        tab.setParentPane(this);
        getTabs().add(tab);
        this.addTab(tab.getTitle(), tab);

    }

    /**
     * Remueve un tab del panel
     * @param index
     */
    public void RemoveTab(int index) {
        //JTabExtended tab = (JTabExtended)this.getTabComponentAt(index);        
        getTabs().remove(index);
        this.remove(index);
    }

    /**
     * Devuelve el tab actualmente visible
     * @return
     */
    public ITabbedWindow getCurrentTab() {
        return currentTab;
    }

    /**
     * Setea el tab actual
     * @param currentTab
     */
    public void setCurrentTab(ITabbedWindow currentTab) {
        this.currentTab = currentTab;
        for (int i = 0; i <= this.getComponentCount() - 1; i++) {
            if (this.getComponent(i).equals(this.currentTab)) {
                this.setSelectedIndex(i);
            }
        }
        this.currentTab.RegisterToolBarStateInfo();
    }

    /**
     * Setea el tab actual
     * @param index
     */
    public void setCurrentTab(int index) {
        if (getTabs().size() > index && index >= 0) {
            this.setSelectedIndex(index);
            this.currentTab = getTabs().get(index);
            this.currentTab.RegisterToolBarStateInfo();
        }
    }

    /**
     * Devuelve el indice del tab actual
     * @return
     */
    public int getCurrentTabIndex() {
        for (int i = 0; i <= this.getComponentCount() - 1; i++) {
            if (this.getComponent(i).equals(this.currentTab)) {
                return i;
            }
        }
        return -1;
    }

}





