

package sicce.api.util;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import com.toedter.calendar.JYearChooser;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author TMS - The Microserv S.A
 */
public class FocusHelper
{
    public static void patchTabFocus(final JComponent pComponente)
    {
        Runnable doFocus = new Runnable()
        {
            public void run()
            {
                pComponente.requestFocusInWindow();
            }
        };
        
        SwingUtilities.invokeLater(doFocus);
    }
    
    public static void patchTabFocus(final Component pComponente)
    {
        Runnable doFocus = new Runnable()
        {
            public void run()
            {
                pComponente.requestFocusInWindow();
            }
        };
        SwingUtilities.invokeLater(doFocus);
    }
    
    public static void patchTabFocusYearChooser(final JYearChooser pComponente)
    {
        Runnable doFocus = new Runnable()
        {
            public void run()
            {
                focusYearChooser(pComponente);
            }
        };
        SwingUtilities.invokeLater(doFocus);
    }
    
    public static JTextFieldDateEditor getTextFieldDateEditor(final JDateChooser pControl)
    {
        Component[] componentes = pControl.getComponents();
        if (componentes == null) return null;
        for(int i = 0; i< componentes.length; i++)
        {
            Component componente = componentes[i];
            if(componente instanceof JTextFieldDateEditor)
                return (JTextFieldDateEditor)componente;
        }
        
        return null;
    }
    
    public static void focusDateChooser(final JDateChooser pControl)
    {
        JTextFieldDateEditor textField  = getTextFieldDateEditor(pControl);
        if (textField == null) return;
        textField.setFocusable(true);
        textField.requestFocusInWindow();
    }
    
    public static void focusYearChooser(final JYearChooser pControl)
    {
        
        Component[] componentes = pControl.getComponents();
        if (componentes == null) return;
        for(int i = 0; i< componentes.length; i++)
        {
            Component componente = componentes[i];
            if(componente instanceof javax.swing.JTextField)
            {
                JTextField textField = (JTextField)componente;
                textField.setFocusable(true);
                textField.requestFocusInWindow();
            }
            
        }
    }
    
    public static void patchTabFocusDateChooser(final JDateChooser pControl)
    {
        Runnable doFocus = new Runnable()
        {
            public void run()
            {
                focusDateChooser(pControl);
            }
        };
        SwingUtilities.invokeLater(doFocus);
    }
    
    
    public static void patchTabFocusTextFieldDateEditor(final JTextFieldDateEditor pControl)
    {
        Runnable doFocus = new Runnable()
        {
            public void run()
            {
                pControl.requestFocusInWindow();
            }
        };
        SwingUtilities.invokeLater(doFocus);
    }
    
}
