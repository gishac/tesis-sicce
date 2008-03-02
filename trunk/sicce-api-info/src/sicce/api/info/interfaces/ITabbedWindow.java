/*
 * Interfaz a ser implementada por los componentes que van a interactuar con la barra de herramientas
 * El proposito principal, es que esta interfaz sea implementada por los mantenimientos de la aplicacion
 */

package sicce.api.info.interfaces;

import sicce.api.info.ConstantsProvider.DialogResult;

/**
 *
 * @author gish@c
 */
public interface ITabbedWindow {

    void setTitle(String title);
    String getTitle();
    void New();
    boolean Save() throws Exception;
    DialogResult Search();
    boolean Delete() throws Exception;
    void Edit();
    void Back();
    boolean Update() throws Exception;
    boolean IsActive();
    void FillGrid();
    void RegisterSelectionListener();
    void SetUIElements();
}
