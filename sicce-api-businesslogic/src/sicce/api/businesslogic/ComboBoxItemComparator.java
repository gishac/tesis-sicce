/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.Comparator;
import sicce.api.info.ComboBoxItem;

/**
 *
 * @author gish@c
 */
public class ComboBoxItemComparator implements Comparator<ComboBoxItem> {

    public int compare(ComboBoxItem o1, ComboBoxItem o2) {
         return String.valueOf(o1.getId()).compareTo(String.valueOf(o2.getId()));
    }

}
