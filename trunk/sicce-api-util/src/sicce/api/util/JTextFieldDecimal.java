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
public class JTextFieldDecimal extends PlainDocument {

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet a) throws BadLocationException {
        try {
            String curText = getText(0, getLength());
            String preOffset = curText.substring(0, offset);
            String postOffset = curText.substring(offset, curText.length());
            String value = preOffset + str + postOffset;
            Double.parseDouble(value);
            super.insertString(offset, str, a);
        } catch (Exception ex) {

            Toolkit.getDefaultToolkit().beep();
        }
    }
}
