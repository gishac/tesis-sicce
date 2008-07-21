
package sicce.api.util;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * 
 * @author gish@c
 */
public class UtilMath
{
    /**
     * 
     * @param value
     * @return
     */
     public static BigDecimal StringToBigDecimal (String value)
     {   if (value == null) return null;
         
         if (value.length ()>0)
              return (new BigDecimal (value).setScale (2));
         else return null;
     }
     
     /**
      * 
      * @param value
      * @return
      */
     public static BigDecimal Round (BigDecimal value)
     {   if (value == null) return null;
         return value.setScale (2,BigDecimal.ROUND_HALF_UP);
     }
     
     /**
      * 
      * @param value
      * @param decimalPlacesInValue
      * @return
      */
     public static String Parse(String value, int decimalPlacesInValue){
         if(value.length() <= decimalPlacesInValue)
             return value;
         Locale locale = new Locale("es-EC");
         DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
         String integerPart = value.substring(0, value.length() - decimalPlacesInValue);
         String decimalPart = value.substring(integerPart.length(), value.length());
         return NumberFormat.getNumberInstance(locale).format(Double.parseDouble(integerPart + symbols.getDecimalSeparator() + decimalPart));
     }
     
}
