/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.controls;

import javax.swing.JPanel;
import sicce.api.info.interfaces.ITabbedWindow;

/**
 *
 * @author gish@c
 */
public class JTabExtended extends JPanel implements ITabbedWindow{

    private String title;
    
    public JTabExtended()
    {
        super();
    }
    
    public JTabExtended(String title)
    {
        this();
        setTitle(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
