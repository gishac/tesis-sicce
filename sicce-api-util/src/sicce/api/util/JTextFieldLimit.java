/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.util;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author gish@c
 */
public class JTextFieldLimit extends PlainDocument {

    private int maxLength = 9999;

    public JTextFieldLimit(int maxLength) {
        super();
        this.maxLength = maxLength;
    }

    @Override
       public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        if (getLength() + str.length() > maxLength) {
            Toolkit.getDefaultToolkit().beep();
            return;
        } else {
            try {
                super.insertString(offset, str, a);
            } catch (NumberFormatException exp) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }
        }
        return;
    }
}
