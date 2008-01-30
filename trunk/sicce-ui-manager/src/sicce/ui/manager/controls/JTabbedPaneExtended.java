/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.controls;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.ui.manager.listeners.CloseTabListener;

/**
 *
 * @author gish@c
 */
public class JTabbedPaneExtended extends JTabbedPane{
    
    /**
     * Mantiene la instancia del tab actual
     */
    private ITabbedWindow currentTab;
    
    /** 
     *  Constructor
     */
    public JTabbedPaneExtended()
    {
        super();
        this.addMouseListener(new CloseTabListener(this));
    }
    
    /**
     * Contiene la lista de tabs que aloja el control
     */
    private List<ITabbedWindow> tabs;
    public List<ITabbedWindow> getTabs() {
        if(tabs == null)
        {
            tabs = new ArrayList<ITabbedWindow>();
        }
        return tabs;
    }

   
    /**
     * Agrega un tab al panel
     * @param tab
     */
    public void AddTab(JTabExtended tab)
    {
        tab.setParentPane(this);
        this.addTab(tab.getTitle(), tab);
        getTabs().add(tab);
    }
    
    /**
     * Remueve un tab del panel
     * @param index
     */
    public void RemoveTab(int index)
    {
        JTabExtended tab = (JTabExtended)this.getTabComponentAt(index);
        getTabs().remove(tab);
        this.remove(index);
    }
    
    /**
     * Devuelve el tab actualmente visible
     * @return
     */
    public ITabbedWindow getCurrentTab()
    {
        return currentTab;
    }
    
    /**
     * Setea el tab actual
     * @param currentTab
     */
    public void setCurrentTab(ITabbedWindow currentTab)
    {
        this.currentTab = currentTab;
        for(int i=0; i<=this.getComponentCount()-1;i++)
        {
            if(this.getComponent(i).equals(this.currentTab))
            {
                this.setSelectedIndex(i);
            }
        }
    }
}





