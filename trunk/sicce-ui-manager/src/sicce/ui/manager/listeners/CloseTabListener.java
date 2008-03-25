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
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import sicce.ui.manager.controls.JTabbedPaneExtended;

/**
 *
 * @author gish@c
 */
public class CloseTabListener implements ActionListener, MouseListener {

    JTabbedPaneExtended tabManager;
    JPopupMenu popupMenu;
    private Object source;
    private Point clickPoint;
    
    public CloseTabListener(JTabbedPaneExtended ownerTab)
    {
        this.tabManager = ownerTab;
        popupMenu = new JPopupMenu();
        JMenuItem menuItemClose = new JMenuItem("Cerrar");
        menuItemClose.addActionListener(this);
        popupMenu.add(menuItemClose);
    }
    
    
    public void actionPerformed(ActionEvent e) {
         for(int i = 0; i < tabManager.getTabCount(); i++)
         {
            Rectangle rect = tabManager.getUI().getTabBounds(tabManager, i);
            if(rect.contains(clickPoint)){
                try {
                    boolean cancelClose = tabManager.HandleTabChanging(-1, true);
                    if(cancelClose)
                        return;
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

    public void mouseClicked(MouseEvent e) {
       
    }

    public void mousePressed(MouseEvent e) {
      
            //showPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        clickPoint = e.getPoint();
        for(int i = 0; i < tabManager.getTabCount(); i++)
         {
            Rectangle rect = tabManager.getUI().getTabBounds(tabManager, i);
            if(rect.contains(clickPoint)){
                showPopup(e);
            }
        }
            
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
       
    }

    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            source = e.getSource();
            clickPoint = e.getPoint();
            popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }
}
