/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.listeners;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import sicce.ui.manager.controls.JTabbedPaneExtended;

/**
 * Listener para controlar el cierre de los tabs
 * @author gish@c
 */
public class CloseTabListener implements ActionListener, MouseListener {

    /**
     * Objeto para administrar los tabs
     */
    private JTabbedPaneExtended tabManager;
    
    /**
     * Menu a mostrar para cerrar los tabs
     */
    private JPopupMenu popupMenu;
   
    /**
     * Punto de la pantalla donde se realiza el click
     */
    private Point clickPoint;

    
    /**
     * Constructor
     * @param ownerTab Tab que envia el evento
     */
    public CloseTabListener(JTabbedPaneExtended ownerTab) {
        this.tabManager = ownerTab;
        popupMenu = new JPopupMenu();
        JMenuItem menuItemClose = new JMenuItem("Cerrar");
        menuItemClose.addActionListener(this);
        popupMenu.add(menuItemClose);
    }

    /**
     * Accion que cierra el tab
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < tabManager.getTabCount(); i++) {
            Rectangle rect = tabManager.getUI().getTabBounds(tabManager, i);
            if (rect.contains(clickPoint)) {
                try {
                    boolean cancelClose = tabManager.HandleTabChanging(-1, true);
                    if (cancelClose) {
                        return;
                    }
                    if (tabManager.getCurrentTab() != null) {
                        tabManager.getCurrentTab().Close();
                    }
                    tabManager.RemoveTab(i);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(CloseTabListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Evento ejecutado cuando se da click con el mouse
     * @param e
     */
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Evento ejecutado cuando se presiona el mouse
     * @param e
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Evento ejecutado cuando se suelta el mouse luego de haber presionado un boton
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
        clickPoint = e.getPoint();
        for (int i = 0; i < tabManager.getTabCount(); i++) {
            Rectangle rect = tabManager.getUI().getTabBounds(tabManager, i);
            if (rect.contains(clickPoint)) {
                showPopup(e);
            }
        }

    }

    /**
     * Indica cuando el mouse entra sobre algun area
     * @param e
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * 
     * @param e
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Muestra el menu en el lugar en el que se hizo click
     * @param e
     */
    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            clickPoint = e.getPoint();
            popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }
}
