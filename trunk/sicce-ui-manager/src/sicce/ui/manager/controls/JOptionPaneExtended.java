/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.controls;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author gish@c
 */
public class JOptionPaneExtended extends JOptionPane {

    private String[] optionsText;
        
    public JOptionPaneExtended(String[] optionsText)
    {
        super(null,JOptionPane.INFORMATION_MESSAGE,JOptionPaneExtended.YES_NO_CANCEL_OPTION,null,optionsText);
        this.optionsText = optionsText;
    }
    
    /**
     * 
     * @param message
     * @param title
     * @return
     */
    public int ShowConfirmDialog(String message,String title)
    {
        JOptionPane pane = new JOptionPane(message,JOptionPaneExtended.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION,null,optionsText);
        JDialog dialog = pane.createDialog(title);
        dialog.setVisible(true);
        return GetResult(pane.getValue().toString());
    }
    
    private int GetResult(String result)
    {
        if(result.equals(optionsText[0]))
        {
            return YES_OPTION;
        }
        if(result.equals(optionsText[1]))
        {
            return NO_OPTION;
        }
        return CANCEL_OPTION;
    }
}













