/*
 * Interfaz a ser implementada por los componentes que van a interactuar con la barra de herramientas
 * El proposito principal, es que esta interfaz sea implementada por los mantenimientos de la aplicacion
 */

package sicce.api.info.interfaces;

import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.ToolBarAction;

/**
 * Define los metodos a ser implementados por las clases que representen a los tabs
 * @author gish@c
 */
public interface ITabbedWindow {

    /**
     * Establece el titulo del tab
     * @param title Titulo del tab
     */
    void setTitle(String title);
    
    /**
     * Devuelve el titulo del tab
     * @return Titulo del tab
     */
    String getTitle();
    
    /**
     * Accion ejecutada al seleccionar la opcion Nuevo del formulario
     */
    void New();
    
    /**
     * Accion ejecutada al seleccionar la opcion Guardar del formulario
     * @return <strong> True </strong> si se ejecuto correctamente el proceso, <strong> False </strong>, si no se ejecuto 
     * correctamente el proceso
     * @throws java.lang.Exception
     */
    boolean Save() throws Exception;
    
    /**
     * Accion ejecutada al seleccionar la opcion Buscar del formulario
     * @return Resultado de la seleccion
     * @see DialogResult
     */
    DialogResult Search();
    
    /**
     * Accion ejecutada al seleccionar la opcion Eliminar del formulario
     * @return <strong> True </strong> si se ejecuto correctamente el proceso, <strong> False </strong>, si no se ejecuto 
     * @throws java.lang.Exception
     */
    boolean Delete() throws Exception;
    
    /***
     * Accion ejecutada al seleccionar la opcion Editar del formulario
     */
    void Edit();
    
    /**
     * Accion ejecutada al seleccionar la opcion Regresar del formulario
     */
    void Back();
    
    /**
     * Accion ejecutada al seleccionar la opcion Actualizar del formulario
     * @return @return <strong> True </strong> si se ejecuto correctamente el proceso, <strong> False </strong>, si no se ejecuto 
     * @throws java.lang.Exception
     */
    boolean Update() throws Exception;
    
    /**
     * Indica si el tab es el tab activo 
     * @return @return <strong> True </strong> si es el tab activo, <strong> False </strong>, si no es el tab activo
     */
    boolean IsActive();
    
    /**
     * Invoca al metodo que carga la tabla de items mostrada en el tab
     */
    void FillGrid();
    
    /**
     * Invoca al metodo que registra el objeto para monitorear la seleccion de items en la tabla del tab
     */
    void RegisterSelectionListener();
    
    /**
     * Invoca al metodo para actualizar los objetos de interfaz con los valores del objeto actual cargado
     */
    void SetUIElements();
    
    /**
     * Invoca al metodo que regitra el objeto para monitorear las actividades del toolbar
     */
    void RegisterToolBarStateInfo();
    
    /**
     * Invoca al metodo que coloca al formulario en el estado inicial
     */
    void SetDefaultState();
    
    /**
     * Invoca al metodo que actualiza los estados del toolbar
     */
    void RefreshToolBarState();
    
    /**
     * Devuelve el estado actual del toolbar
     * @return Estado actual del toolbar 
     * @see ToolBarAction
     */
    ToolBarAction getTabState();
    
    /**
     * Invoca al metodo que borra todos los datos del formulario
     */
    void Clear();
    
    /**
     * Invoca el metodo que cierra el tab
     */
    void Close();
    
    /**
     * Invoca al metodo que se ejecuta luego de grabar los datos del formulario
     */
    void AfterSave();
    
    /**
     * Invoca al metodo que indica que se cancelo la accion de cerrar el formulario
     */
    void CancelSave();
    
    /**
     * Invoca al metodo que indica que se cancelo la seleccion de un item en la tabla del tab
     * @param previousIndexSelected Indice del elemento seleccionado previamente
     */
    void ItemSelectionCanceled(int previousIndexSelected);
}
