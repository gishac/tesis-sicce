/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.util;

import java.awt.Toolkit;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author gish@c
 */
public class JTextFieldInteger extends PlainDocument {

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet a) throws BadLocationException {
        try {
            Integer.parseInt(str);
        } catch (Exception ex) {
            if (str.length() > 0) {
                Toolkit.getDefaultToolkit().beep();
                str = str.substring(0, str.length() - 1);
            }
            else
                return;
        }
        try {
            super.insertString(offset, str, a);
        } catch (NumberFormatException exp) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
    }
}
