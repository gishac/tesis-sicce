/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.controls;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.ui.listeners.CloseTabListener;

/**
 *
 * @author gish@c
 */
public class JTabbedPaneExtended extends JTabbedPane{
    
    public JTabbedPaneExtended()
    {
        super();
        this.addMouseListener(new CloseTabListener(this));
    }
    
    private List<ITabbedWindow> tabs;

    public List<ITabbedWindow> getTabs() {
        if(tabs == null)
        {
            tabs = new ArrayList<ITabbedWindow>();
        }
        return tabs;
    }

    public void setTabs(List<ITabbedWindow> tabs) {
        this.tabs = tabs;
    }
    
    public void AddTab(ITabbedWindow tab)
    {
        JPanel pane = new JPanel();
        JLabel l = new JLabel(tab.getTitle());
        pane.add(l);
        this.addTab(tab.getTitle(), pane);
    }
}
