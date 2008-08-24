/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.controls;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.application.ResourceMap;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.ToolBarStateInfo;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;
import sicce.api.util.ComponentUtil;
import sicce.ui.manager.handlers.ToolBarHandler;

/**
 * Representacion de los tabs de la aplicacion
 * @author gish@c
 */
public class JTabExtended<T> extends JPanel implements ITabbedWindow, IToolBarStateListener {

    /**
     * Titulo del tab
     */
    private String title;
    
    /**
     * Objeto para conocer el estado de foco del tab
     */
    private FocusListener focusEventHandler;
    
    /**
     * Objeto para controlar las selecciones en las tablas de objetos en el tab
     */
    protected ListSelectionListener selectionListener;
    
    /**
     * Panel que contiene al tab
     */
    private JTabbedPaneExtended parentPane;
    
    /**
     * Indica si hay un objeto cargado en modo de edicion en el formulario
     */
    private boolean objectLoaded;
    
    /**
     * Lista de controles que pueden ser inicializados 
     */
    private List<Component> controlsToClear;
    
    /**
     * Lista de controles que se pueden activar o desactivar
     */
    private List<Component> controlsToEnable;
    
    /**
     * Indica si se debe cancelar una accion en especifico
     */
    protected boolean cancelAction;
    
    /**
     * Indica si se deben monitorear los estados del toolbar
     */
    private boolean handleToolBarStates;
    
    /**
     * Objeto donde se establecen las acciones del toolbar que se pueden realizar
     */
    private ToolBarStateInfo toolBarStateInfo;
    
    /**
     * Mantiene el estado de accion del Tab
     */
    private ToolBarAction tabState;
    
    /**
     * Objeto que se encuentra cargado en el tab
     */
    protected T currentObject;
    
    /**
     * Objeto de acceso a recursos
     */
    protected ResourceMap resourceMap;

    /**
     * Devuelve el objeto que se encuentra cargado en el tab
     * @return Objeto que se encuentra cargado en el tab
     */
    public T getCurrentObject() {
        return currentObject;
    }

    /**
     * Establece el objeto que se encuentra cargado en el tab
     * @param currentObject Objeto que se encuentra cargado en el tab
     */
    public void setCurrentObject(T currentObject) {
        this.currentObject = currentObject;
    }

    /**
     * Devuelve el objeto que contiene las acciones del toolbar que se pueden realizar
     * @return Objeto que contiene las acciones del toolbar que se pueden realizar 
     */
    public ToolBarStateInfo getToolBarStateInfo() {
        if (toolBarStateInfo == null) {
            toolBarStateInfo = new ToolBarStateInfo();
        }
        return toolBarStateInfo;
    }

    /**
     * Establece el objeto que contiene las acciones del toolbar que se pueden realizar
     * @param toolBarStateInfo Objeto que contiene las acciones del toolbar que se pueden realizar
     */
    public void setToolBarStateInfo(ToolBarStateInfo toolBarStateInfo) {
        this.toolBarStateInfo = toolBarStateInfo;
    }

    /**
     * Devuelve el panel que contiene al tab
     * @return Panel que contiene el tab
     */
    public JTabbedPaneExtended getParentPane() {
        return parentPane;
    }

    /**
     * Establece el panel que contiene al tab
     * @param parentPane Panel que contiene al tab
     */
    public void setParentPane(JTabbedPaneExtended parentPane) {
        this.parentPane = parentPane;
    }

    
    /**
     * Establece el objeto para monitorear los cambios de seleccion enla tabla de items del grid
     * @param selectionListener Objeto para monitorear los cambios de seleccion enla tabla de items del grid
     */
    public void setListSelectionListener(ListSelectionListener selectionListener) {
        this.selectionListener = selectionListener;

    }

    /**
     * Registra el objeto para monitorear las actividades del toolbar
     */
    public void RegisterToolBarStateInfo() {
        if (this.selectionListener != null) {
            ((ToolBarHandler) selectionListener).setToolBarStateInfo(this.getToolBarStateInfo());
        }
    }

    /**
     * Indica si el tab va a monitorear los estados el toolBar
     * @return <strong>True</strong>, si el tab va a monitorear los estados del toolBar; <strong>False</strong>,
     * si el tab no va a monitorear los estados del toolBar
     */
    public boolean getHandleToolBarStates() {
        return handleToolBarStates;
    }

    /**
     * Establece si el tab va a monitorear los estados el toolBar
     * @param handleToolBarStates <strong>True</strong>, si el tab va a monitorear los estados del toolBar; <strong>False</strong>,
     * si el tab no va a monitorear los estados del toolBar
     */
    public void setHandleToolBarStates(boolean handleToolBarStates) {
        this.handleToolBarStates = handleToolBarStates;
    }

    /**
     * Indica si hay un objeto cargado en el tab
     * @return <strong>True</strong>, si hay un objeto cargado en el tab; <strong>False</strong>,
     * si no hay un objeto cargado en el tab
     */
    public boolean IsObjectLoaded() {
        return objectLoaded;
    }

    /**
     * Devuelve la lista de controles que van a ser inicializados
     * @return Lista de controles que van a ser inicializados
     */
    public List<Component> getControlsToClear() {
        if (controlsToClear == null) {
            controlsToClear = new ArrayList<Component>();
        }
        return controlsToClear;
    }

    /**
     * Devuelve la lista de controles que van a ser habilitados o deshabilitados
     * @return Lista de controles que van a ser habilitados o deshabilitados
     */
    public List<Component> getControlsToEnable() {
        if (controlsToEnable == null) {
            controlsToEnable = new ArrayList<Component>();
        }
        return controlsToEnable;
    }

    /**
     * Constructor
     */
    public JTabExtended() {
        super();
        this.handleToolBarStates = true;
        this.tabState = ToolBarAction.None;
        this.addFocusListener(focusEventHandler = new FocusListener() {

            public void focusGained(FocusEvent e) {
                parentPane.setCurrentTab((ITabbedWindow) e.getComponent());
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    /**
     * Constructor
     * @param title Titulo del tab
     */
    public JTabExtended(String title) {
        this();
        setTitle(title);
    }

    /**
     * Constructor
     * @param resourceMap Objeto para manejar archivos de recursos
     */
    public JTabExtended(ResourceMap resourceMap) {
        this();
        this.resourceMap = resourceMap;
    }

    /**
     * Establece el titulo del tab
     * @param title Titulo del tab
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Devuelve el titulo del tab
     * @return Titulo del tab
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accion ejecutada al seleccionar la opcion Nuevo del formulario
     */
    public void New() {
        objectLoaded = false;
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(true, getControlsToEnable());
    }

    /**
     * Accion ejecutada al seleccionar la opcion Guardar del formulario
     * @return <strong> True </strong> si se ejecuto correctamente el proceso, <strong> False </strong>, si no se ejecuto 
     * correctamente el proceso
     * @throws java.lang.Exception
     */
    public boolean Save() throws Exception {
        tabState = ToolBarAction.Save;
        return true;
    }

    /**
     * Metodo que se ejecuta luego de grabar los datos del formulario
     */
    public void AfterSave() {
        tabState = ToolBarAction.RegistryLoaded;
        ComponentUtil.SetState(false, this.getControlsToEnable());
    }

     /**
     * Accion ejecutada al seleccionar la opcion Buscar del formulario
     * @return Resultado de la seleccion
     */
    public DialogResult Search() {
        return DialogResult.Cancel;
    }

    /**
     * Accion ejecutada al seleccionar la opcion Eliminar del formulario
     * @return <strong> True </strong> si se ejecuto correctamente el proceso, <strong> False </strong>, si no se ejecuto 
     * @throws java.lang.Exception
     */
    public boolean Delete() throws Exception {
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(false, getControlsToEnable());
        objectLoaded = false;
        currentObject = null;
        return false;
    }

    /***
     * Accion ejecutada al seleccionar la opcion Editar del formulario
     */
    public void Edit() {
        objectLoaded = true;
        ComponentUtil.SetState(true, getControlsToEnable());
    }

    /**
     * Accion ejecutada al seleccionar la opcion Regresar del formulario
     */
    public void Back() {
        Clear();
    }

    /**
     * Accion ejecutada al seleccionar la opcion Actualizar del formulario
     * @return @return <strong> True </strong> si se ejecuto correctamente el proceso, <strong> False </strong>, si no se ejecuto 
     * @throws java.lang.Exception
     */
    public boolean Update() throws Exception {
        return true;
    }

    /**
     * Accion ejecutada cuando se seleccion un objeto en la tabla de items del tab
     * @param selectedIndex Indice seleccionado
     */
    public void ItemSelected(int selectedIndex) {
        objectLoaded = true;
        ComponentUtil.SetState(false, getControlsToEnable());
    }

    /**
     * Registra el objeto para monitorear la seleccion de items en la tabla del tab
     */
    public void RegisterSelectionListener() {
    }

    
    /**
     * Actualiza los objetos de interfaz con los valores del objeto actual cargado
     */
    public void SetUIElements() {

    }

    /**
     * Carga la tabla de items mostrada en el tab
     */
    public void FillGrid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Indica si el tab es el tab activo 
     * @return @return <strong> True </strong> si es el tab activo, <strong> False </strong>, si no es el tab activo
     */
    public boolean IsActive() {
        if (getParentPane() == null) {
            return false;
        }
        return getParentPane().getCurrentTab().equals(this);
    }

    /**
     * Evento generado cuando cambia el estado del toolbar
     * @param event Argumento con la informacion del estado del toolbar
     */
    public void ToolBarStateChanged(ToolBarEventObject event) throws Exception {
        ToolBarAction toolbarAction = event.getToolBarState();
        event.setHandleToolBarStates(getHandleToolBarStates());
        switch (toolbarAction) {
            case New:
                New();
                tabState = ToolBarAction.New;
                break;
            case Edit:
                Edit();
                tabState = ToolBarAction.Edit;
                break;
            case Save:
                event.setCancelEvent(Save());
                if (!event.getCancelEvent()) {
                    AfterSave();
                }
                break;
            case Delete:
                event.setCancelEvent(Delete());
                tabState = ToolBarAction.None;
                break;
            case Search:
                event.setSearchDialogResult(Search());
                if (event.getSearchDialogResult() == DialogResult.Ok) {
                    ComponentUtil.SetState(false, getControlsToEnable());
                    tabState = ToolBarAction.RegistryLoaded;
                }
                break;
            case Back:
                Back();
                tabState = ToolBarAction.None;
                break;
            case RegistryLoaded:
                if (tabState != ToolBarAction.New) {
                    ItemSelected(event.getSelectedIndex());
                }
                tabState = ToolBarAction.RegistryLoaded;
                break;
        }
    }

    /**
     * Devuelve el estado actual del toolbar
     * @return Estado actual del toolbar
     */
    public ToolBarAction getTabState() {
        return this.tabState;
    }

    /**
     * Coloca al formulario en el estado inicial
     */
    public void SetDefaultState() {
        tabState = ToolBarAction.None;
        ComponentUtil.SetState(false, getControlsToEnable());
        ((ToolBarHandler) selectionListener).SetDefaultState();
    }

    /**
     * Actualiza los estados del toolbar
     */
    public void RefreshToolBarState() {
        //Reemplaza los estado de edicion por estados neutrales
        //ya que los estados de edicion no se mantienen
        if (tabState == ToolBarAction.Edit) {
            tabState = ToolBarAction.RegistryLoaded;
        }
        if (tabState == ToolBarAction.New) {
            tabState = ToolBarAction.None;
        }
        SetUIElements();
        ComponentUtil.SetState(false, getControlsToEnable());
        ((ToolBarHandler) selectionListener).RefreshState(tabState);
    }

    /**
     * Borra todos los datos del formulario
     */
    public void Clear() {
        tabState = ToolBarAction.None;
        this.currentObject = null;
        objectLoaded = false;
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(false, getControlsToEnable());
    }

    /**
     * Cierra el tab
     */
    public void Close() {
        Clear();
        FillGrid();
        RefreshToolBarState();
    }

    /**
     * Metodo que indica que se cancelo la accion de cerrar el formulario
     */
    public void CancelSave() {

    }

    /**
     * Realiza las validaciones sobre los campos del formulario
     * @return <strong>True</strong>, si las validaciones son correctas; <strong>False</strong>,
     * si falla alguna validacion
     */
    public boolean CheckFields() {
        return true;
    }

    /**
     * Metodo que indica que se cancelo la seleccion de un item en la tabla del tab
     * @param previousIndexSelected Indice del elemento seleccionado previamente
     */
    public void ItemSelectionCanceled(int previousIndexSelected) {

    }
}
