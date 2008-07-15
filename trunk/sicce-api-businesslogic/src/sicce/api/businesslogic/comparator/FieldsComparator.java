/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.comparator;

import sicce.api.info.Field;
import java.util.Comparator;

/**
 *
 * @author gish@c
 */
public class FieldsComparator implements Comparator<Field> {

    public int compare(Field o1, Field o2) {
        return String.valueOf(o1.getOrder()).compareTo(String.valueOf(o2.getOrder()));
    }

}
