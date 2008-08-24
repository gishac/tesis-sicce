/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.controls;

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
 * Contenedor de los tabs de la aplicacion
 * @author gish@c
 */
public class JTabbedPaneExtended extends JTabbedPane {

    /**
     * Mantiene la instancia del tab actual
     */
    private ITabbedWindow currentTab;
    
    /**
     * Manejador de recursos
     */
    private ResourceMap resourceMap;
    
    /**
     * Opciones de Si, No, Cancelar a mostrar en los cuadros de dialogo
     */
    private String[] optionsText;
    
    /**
     * Indica si alguna operacion fue producto
     */
    private boolean internalEvent;

    /**
     * Devuelve las opciones de Si, No, Cancelar a mostrar en los cuadros de dialogo
     * @return Opciones de Si, No, Cancelar a mostrar en los cuadros de dialogo
     */
    public String[] getOptionsText() {
        return optionsText;
    }

    /**
     * Establece las opciones de Si, No, Cancelar a mostrar en los cuadros de dialogo
     * @param optionsText Opciones de Si, No, Cancelar a mostrar en los cuadros de dialogo
     */
    public void setOptionsText(String[] optionsText) {
        this.optionsText = optionsText;
    }

    /**
     * Devuelve el manejador de recursos
     * @return Manejador de recursos
     */
    public ResourceMap getResourceMap() {
        return resourceMap;
    }

    /**
     * Establece el manejador de recursos
     * @param resourceMap Manejador de recursos
     */
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
     * @param newTabIndex -1 si el evento no es un cambio de tab
     * @param tabClosing <strong> True </strong>, si se esta cerrando el tab
     * @return
     * @throws java.lang.Exception
     */
    public boolean HandleTabChanging(int newTabIndex, boolean tabClosing) throws Exception {
        boolean result = HandleTabChanging();
        if(result)
            return result;
        if (!tabClosing)
            setCurrentTab(newTabIndex);
        if(getCurrentTab() != null)
            getCurrentTab().RefreshToolBarState();
        return result;
    }
    
    /**
     * Administra el cambio de tabs
     * @param newSelectedTab Nuevo tab seleccionado
     * @return
     * @throws java.lang.Exception
     */
    public boolean HandleTabChanging(ITabbedWindow newSelectedTab) throws Exception {
        boolean result = HandleTabChanging();
        if(result)
            return result;
        internalEvent = true;
        setCurrentTab(newSelectedTab);
        internalEvent = false;
        newSelectedTab.RefreshToolBarState();
        return result;
    }
    
    /**
     * Administra el cambio de tabs
     * @return
     * @throws java.lang.Exception
     */
    public boolean HandleTabChanging() throws Exception{
        boolean result = false;
        if (getCurrentTab() != null && (getCurrentTab().getTabState() == ToolBarAction.Edit || getCurrentTab().getTabState() == ToolBarAction.New)) {
            JOptionPaneExtended confirmDialog = new JOptionPaneExtended(optionsText);
            int dialogResult = confirmDialog.ShowConfirmDialog(getResourceMap().getString("SaveConfirmDialog"), getResourceMap().getString("ApplicationName"));
            switch (dialogResult) {
                case JOptionPaneExtended.YES_OPTION:
                    boolean cancelAction = getCurrentTab().Save();                    
                    if (cancelAction) {
                        CancelTabChanging();
                        result = true;
                        break;
                    }
                    getCurrentTab().AfterSave();
                    result = false;
                    break;
                case JOptionPaneExtended.NO_OPTION:
                    getCurrentTab().CancelSave();
                    getCurrentTab().RefreshToolBarState();
                    result = false;
                    break;
                case JOptionPaneExtended.CANCEL_OPTION:
                    CancelTabChanging();
                    result = true;
                    break;
            }
        }
        return result;
    }
    
    
    
    /**
     * Lista de tabs alojados en el control
     */
    private List<ITabbedWindow> tabs;

    /**
     * Devuelve la lista de tabs alojados en el control
     * @return Lista de tabs alojados en el control
     */
    public List<ITabbedWindow> getTabs() {
        if (tabs == null) {
            tabs = new ArrayList<ITabbedWindow>();
        }
        return tabs;
    }

    /**
     * Agrega un tab al panel
     * @param tab Tab a ser agregado
     */
    public void AddTab(JTabExtended tab) {
        tab.setParentPane(this);
        getTabs().add(tab);
        this.addTab(tab.getTitle(), tab);

    }

    /**
     * Remueve un tab del panel
     * @param index Indice del tab a remover
     */
    public void RemoveTab(int index) {
        getTabs().remove(index);
        this.remove(index);
    }

    /**
     * Devuelve el tab que se encuentra visible
     * @return Tab que se encuentra visible
     */
    public ITabbedWindow getCurrentTab() {
        return currentTab;
    }

    /**
     * Establece el tab actual
     * @param currentTab Tab seleccionado
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
     * Establece el tab actual
     * @param index Indice del tab seleccionado
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
     * @return Indice del tab actual
     */ 
    public int getCurrentTabIndex() {
        return getTabIndex(this.currentTab);
    }
    
    /**
     * Devuelve el indice de un tab en el panel
     * @param tab Tab del cual se busca el indice
     * @return Indice de un tab en el panel
     */
    public int getTabIndex(ITabbedWindow tab){
        for (int i = 0; i <= this.getComponentCount() - 1; i++) {
            if (this.getComponent(i).equals(tab)) {
                return i;
            }
        }
        return -1;
    }

}





