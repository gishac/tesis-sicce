
package sicce.api.util;

import java.math.BigDecimal;
import javax.swing.table.TableColumn;

/**
 *
 * @author TMS - The Microserv S.A
 */
public class UtlMath
{
     public static final int NUMERO_DECIMALES = 2;
     
     public static double round (double val, int places)
     {
          long factor = (long)Math.pow (10,places);
          
          // Shift the decimal the correct number of places
          // to the right.
          val = val * factor;
          
          // Round to the nearest integer.
          long tmp = Math.round (val);
          
          // Shift the decimal the correct number of places
          // back to the left.
          return (double)tmp / factor;
     }
     
     /**
      * Round a float value to a specified number of decimal
      * places.
      *
      * @param val the value to be rounded.
      * @param places the number of decimal places to round to.
      * @return val rounded to places decimal places.
      */
     public static float round (float val, int places)
     {
          return (float)round ((double)val, places);
     }
     
     public static BigDecimal strToBigDecimal (String pNumeroDecimal)
     {   if (pNumeroDecimal == null) return null;
         
         if (pNumeroDecimal.length ()>0)
              return (new BigDecimal (pNumeroDecimal).setScale (2));
         else return null;
     }
     
     public static Long strToLong (String pNumeroLong)
     {   if (pNumeroLong == null) return null;
         
         if (pNumeroLong.length ()>0)
              return (new Long (pNumeroLong));
         else return null;
     }
     
     public static Integer strToInteger (String pNumeroInteger)
     {   if (pNumeroInteger == null) return null;
         
         if (pNumeroInteger.length ()>0)
              return (new Integer (pNumeroInteger));
         else return null;
     }
     
     public static BigDecimal zeroToNull (BigDecimal pValor,boolean pAplicarCorrecion)
     {   if (pValor == null) return null;
         if (pValor.floatValue () == 0)
         {
              if (pAplicarCorrecion) return null;
         }
         return pValor;
     }
     
     
     public static BigDecimal zeroToNull (Double pValor, boolean pAplicarCorrecion)
     {   if (pValor == null) return null;
         
         if (pValor.floatValue () == 0)
         {
              if (pAplicarCorrecion) return null;
         }
         return new BigDecimal (pValor.doubleValue ()).setScale (2,BigDecimal.ROUND_HALF_UP);
     }
     
     public static BigDecimal AplicarRedondeo (BigDecimal pValor)
     {   if (pValor == null) return null;
         return pValor.setScale (2,BigDecimal.ROUND_HALF_UP);
     }
     
     public static BigDecimal AplicarRedondeo (double pValor)
     {
          return (new BigDecimal (pValor)).setScale (2,BigDecimal.ROUND_HALF_UP);
     }
     
     public static void ColumnaAjustar (TableColumn pColumna, int pTamanio)
     {
          pColumna.setMinWidth (pTamanio);
          pColumna.setPreferredWidth (pTamanio);
          pColumna.setResizable (true);
     };
     
   
     
}
