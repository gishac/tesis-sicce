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

    private int maxLength = 9999;
    private int maxValue = 99999999;
    private int minValue = 0;

    public JTextFieldInteger() {

    }

    public JTextFieldInteger(int maxLength) {
        this.maxLength = maxLength;
    }

    public JTextFieldInteger(int minValue, int maxValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet a) throws BadLocationException {
        try {
            String curText = getText(0, getLength());
            String preOffset = curText.substring(0, offset);
            String postOffset = curText.substring(offset, curText.length());
            String newStr = preOffset + str + postOffset;


            int value = Integer.parseInt(newStr);
            if ((getLength() + str.length() > maxLength) || (value > maxValue || value < minValue)) {
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
        } catch (Exception ex) {
            if (str.length() > 0) {
                Toolkit.getDefaultToolkit().beep();
                str = str.substring(0, str.length() - 1);
                super.insertString(offset, str, a);
            } else {
                return;
            }
        }
    }
}
