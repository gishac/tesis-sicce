
package sicce.api.util;


import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Component;
import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;


/**
 * Define metodos para realizar Validaciones sobre controles swing y los datos que contienen.
 * @author gish@c
 */
public class Validator
{
    
    private static String productName;
    
    public static void Initialize(String applicationName){
        productName = applicationName;
    }
    
    /**
     * Valida el ingreso de Información aplicable a un JTextComponent
     * @param componentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pTxt El Objeto que se quiere Validar.
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTabbedPane pPanel,int pIndiceTab, JTextComponent pTxt,IEnumComponents pField)
    {
        if (pField.isNumeric())
            return ValidateField(masterComponent,pPanel,pIndiceTab,pTxt, pField.isRequired(),pField.getFieldDescription(),1,pField.isNumericMoreThan0());
        else   return ValidateField(masterComponent,pPanel,pIndiceTab,pTxt, pField.isRequired(),pField.getFieldDescription(),pField.getMinRequired());
    }
    
    /**
     * Valida el ingreso de Información aplicable a un JYearChooser
     * @param componentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pDtp El Objeto JYearChooser que se quiere Validar.
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTabbedPane pPanel,int pIndiceTab, JYearChooser pDtp ,IEnumComponents  pField)
    {
        return ValidateField(masterComponent,pPanel,pIndiceTab, pDtp,pField.isRequired(),pField.getFieldDescription());
    }
    
    /**
     * Valida el ingreso de Información aplicable a un JTextComponent
     * @param componentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pTxt El Objeto JTextComponent que se quiere Validar.
     * @param pObligatorio Define si el campo que se esta Validando es Obligatorio o No.
     * @param pDescripcionCampo Define la descripción del campo,
     * a fin de Mostrar un mensaje Descriptivo cuando la validaci�n no se cumpla.
     * @param pMinimoRequerido Minimo de Caracteres Requeridos, si se define 0, no se Valida un M�nimo.
     * @param pCeroExcluido El Cero no es un Valor Valido.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTabbedPane pPanel,int pIndiceTab,JTextComponent pTxt, boolean pRequired, String pFieldDescription, int pMinRequired,boolean pZeroExcluded)
    {
        if (!ValidateField(masterComponent,pPanel,pIndiceTab,pTxt, pRequired, pFieldDescription, pMinRequired))
            return false;
        
        if (pZeroExcluded && pTxt.getText().length()>0 )
        {
            BigDecimal tmp = UtilMath.StringToBigDecimal(pTxt.getText());
            
            if (tmp.doubleValue()<=0.0 )
            {
                
                JOptionPane.showMessageDialog(masterComponent, pFieldDescription + " debe ser Mayor a Cero.",productName,JOptionPane.WARNING_MESSAGE);
                if (pPanel!=null)
                { pPanel.setSelectedIndex(pIndiceTab);
                  FocusHelper.patchTabFocus(pTxt);
                }
                else
                    pTxt.requestFocusInWindow();
                
                return false;
                
            }
            
        }
        return true;
    }
    
      /**
     * Valida el ingreso de Información aplicable a un JTextComponent
     * @param componentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje. 
     * @param pTxt El Objeto JTextComponent que se quiere Validar.
     * @param pObligatorio Define si el campo que se esta Validando es Obligatorio o No.
     * @param pDescripcionCampo Define la descripción del campo,
     * a fin de Mostrar un mensaje Descriptivo cuando la validaci�n no se cumpla.
     * @param pMinimoRequerido Minimo de Caracteres Requeridos, si se define 0, no se Valida un M�nimo.
     * @param pCeroExcluido El Cero no es un Valor Valido.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTextComponent pTxt, boolean pRequired, String pFieldDescription, int pMinRequired,boolean pZeroExcluded)
    {
         if (!ValidateField(masterComponent,pTxt, pRequired, pFieldDescription, pMinRequired))
            return false;
               
        if (pZeroExcluded && pTxt.getText().length()>0 )
        {
            BigDecimal tmp = UtilMath.StringToBigDecimal(pTxt.getText());
            
            if (tmp.doubleValue()<=0.0 )
            { 
                JOptionPane.showMessageDialog(masterComponent, pFieldDescription + " debe ser Mayor a Cero." ,productName,JOptionPane.WARNING_MESSAGE);
                return false;    
            }
            
        }
        return true;
    }
    
    /**
     * Valida el ingreso de Información aplicable a un JTextComponent
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pTxt El Objeto JTextComponent que se quiere Validar.
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTextComponent pTxt,IEnumComponents pField)
    {
        if (pField.isNumeric())
            return ValidateField(masterComponent,null,0,pTxt, pField.isRequired(),pField.getFieldDescription(),1,pField.isNumericMoreThan0());
        else   return ValidateField(masterComponent,null,0,pTxt, pField.isRequired(),pField.getFieldDescription(),pField.getMinRequired());
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JTextComponent
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pTxt Objeto a verificar
     * @param pObligatorio Es pObligatorio o no su ingreso
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje
     * @param MinimoRequerido Tama�o m�inimo requerido para este campo
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTextComponent pTxt, boolean pRequired, String pFieldDescription, int pMinRequired)
    {
        return ValidateField(masterComponent,null,0,pTxt, pRequired, pFieldDescription, pMinRequired);
    }
    
    
    /**
     * Valida el ingreso de Información aplicable para un JTextComponent
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pTxt Objeto a verificar
     * @param pObligatorio Es pObligatorio o no su ingreso
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTextComponent pTxt, boolean pRequired, IEnumComponents pField)
    {
        if (pField.isNumeric())
            return ValidateField(masterComponent,null,0,pTxt,pRequired,pField.getFieldDescription(),1,pField.isNumericMoreThan0());
        else   return ValidateField(masterComponent,null,0,pTxt,pRequired,pField.getFieldDescription(),pField.getMinRequired());
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JTextComponent
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pTxt Objeto a verificar
     * @param pObligatorio Es pObligatorio o no su ingreso
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validacion
     */
    public static boolean ValidateField(Component masterComponent,JTabbedPane pPanel,int pTabIndex,JTextComponent pTxt,boolean pRequired,IEnumComponents pField)
    {
        if (pField.isNumeric())
            return ValidateField(masterComponent,pPanel,pTabIndex,pTxt,pRequired,pField.getFieldDescription(),1,pField.isNumericMoreThan0());
        else   return ValidateField(masterComponent,pPanel,pTabIndex,pTxt,pRequired,pField.getFieldDescription(),pField.getMinRequired());
    }
    
    
    /**
     * Valida el ingreso de Información aplicable para un JTextComponent
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pTxt Objeto a verificar
     * @param pObligatorio Es pObligatorio o no su ingreso
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje
     * @param pMinimoRequerido Tama�o m�inimo requerido para este campo
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent,JTabbedPane pPanel,int pTabIndex,JTextComponent pTxt, boolean pRequired, String pFieldDescription, int pMinRequired)
    {
        int textSize;
        
        //OBTENGO EL TAMANIO DEL CONTENIDO DEL TEXTO..
        if (pTxt.getText() == null)
            textSize = 0;
        else
        {
            pTxt.setText(pTxt.getText().trim());
            textSize = pTxt.getText().length();
        }
        
        if (textSize==0)
        {
            
            if (pRequired)
            {   //SI EL CAMPO ES OBLIGATORIO.. MUESTRO MENSAJE
                JOptionPane.showMessageDialog(masterComponent,"Debe ingresar " + pFieldDescription ,productName,JOptionPane.WARNING_MESSAGE);
                
                if (pPanel!=null)
                { pPanel.setSelectedIndex(pTabIndex);
                  FocusHelper.patchTabFocus(pTxt);
                }
                else
                    pTxt.requestFocusInWindow();
                
                return false;
            }
            
        } //SI EL TAMANIO ES MENOR AL MINIMO REQUERIDO
        else if (textSize < pMinRequired)
        {
            JOptionPane.showMessageDialog(masterComponent, pFieldDescription + " debe tener al Menos " + Integer.toString(pMinRequired)+ " Caracter(es)" ,productName,JOptionPane.WARNING_MESSAGE);
            
            if (pPanel!=null)
            { pPanel.setSelectedIndex(pTabIndex);
              FocusHelper.patchTabFocus(pTxt);
            }
            else
                pTxt.requestFocusInWindow();
            
            return false;
        }
        return true;
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JComboBox
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pCbo Objeto a verificar.
     * @param pObligatorio Es pObligatorio o no su ingreso.
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JComboBox pCbo, boolean pRequired, String pFieldDescription)
    {
        return ValidateField(masterComponent,null,0,pCbo,pRequired,pFieldDescription);
    }
    
    
    /**
     * Valida el ingreso de Información aplicable para un JComboBox.
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pCbo Objeto a verificar
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent,JTabbedPane pPanel,int pTabIndex, JComboBox pCbo, IEnumComponents pField)
    {
        return ValidateField(masterComponent,pPanel,pTabIndex,pCbo,pField.isRequired(),pField.getFieldDescription());
    }
    
        /**
     * Valida el ingreso de Información aplicable para un JComboBox.
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pCbo Objeto a verificar
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JList pList, boolean pRequired, String pFieldDescription)
    {
        if(pList.getModel().getSize() <=0 || pList.getSelectedIndex() < 0 )
        {
            JOptionPane.showMessageDialog(masterComponent,"No ha ingresado " + pFieldDescription, productName,JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else
            return true;
        
            
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JComboBox
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pCbo Objeto a verificar
     * @param pObligatorio Es pObligatorio o no su ingreso
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent,JTabbedPane pPanel,int pTabIndex, JComboBox pCbo, boolean pRequired, String pFieldDescription)
    {
        if (pRequired)
            if (pCbo.getSelectedItem() == null)
            {
            JOptionPane.showMessageDialog(masterComponent,"No ha seleccionado " + pFieldDescription, productName,JOptionPane.WARNING_MESSAGE);
            
            if (pPanel!=null)
            { pPanel.setSelectedIndex(pTabIndex);
              FocusHelper.patchTabFocus(pCbo);
            }
            else
                pCbo.requestFocus();
            
            return false;
            }
        return true;
        
    }
    
    
    /**
     * Valida el ingreso de Información aplicable para un JDateChooser
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pDtp Objeto a verificar
     * @param pObligatorio Es pObligatorio o no su ingreso
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JDateChooser pDtp, boolean pRequired, String pFieldDescription)
    {
        return ValidateField(masterComponent,null,0, pDtp,pRequired,pFieldDescription);
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JYearChooser
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pYCH Objeto a verificar
     * @param pObligatorio Es pObligatorio o no su ingreso
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JYearChooser pYCH, boolean pRequired, String pFieldDescription)
    {
        return ValidateField(masterComponent,null,0, pYCH,pRequired,pFieldDescription);
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JYearChooser
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pDtp Objeto a verificar
     * @param pObligatorio Indica si el campo es o no pObligatorio.
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTabbedPane pPanel,int pTabIndex, JYearChooser pDtp, boolean pRequired, String pFieldDescription)
    {
        if (!isValidYear(pDtp) && (pRequired))
        {
            JOptionPane.showMessageDialog(masterComponent,"Debe ingresar " + pFieldDescription,productName,JOptionPane.WARNING_MESSAGE);
            
            if (pPanel!=null)
            { pPanel.setSelectedIndex(pTabIndex);
              FocusHelper.patchTabFocusYearChooser(pDtp);
            }
            else
            {
                FocusHelper.focusYearChooser(pDtp);
            }
            return false;
        }
        return true;
    }
    
    private static boolean isValidYear(final JYearChooser dtp)
    {
        JSpinner spin = (JSpinner) dtp.getSpinner();
        Component[] components = spin.getComponents();
        for(int i = 0; i< spin.getComponents().length; i++)
        {
            Component component = components[i];
            
            if(component instanceof javax.swing.JTextField)
            {
                JTextField textField = (JTextField)component;
                if(textField.getText()==null || "".equals(textField.getText()))
                    return false;
                else
                {
                    String year = textField.getText();
                    for (i = 0; i<year.length(); i++)
                    {    if (!Character.isDigit(year.charAt(i)))
                             return false;
                    }
                    int yearToValidate = Integer.parseInt(year);
                    if(yearToValidate<dtp.getStartYear() || yearToValidate>dtp.getEndYear())
                        return false;
                }
            }
            
        }
        return true;
    }
    
    
    
    /**
     * Valida el ingreso de Información aplicable para un JDateChooser
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pDtp Objeto a verificar
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTabbedPane pPanel,int pIndiceTab, JDateChooser pDtp,IEnumComponents pField)
    {
        return ValidateField(masterComponent,pPanel,pIndiceTab,pDtp,pField.isRequired(),pField.getFieldDescription());
    }
    
    
    /**
     * Valida el ingreso de Información aplicable para un JDateChooser
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pDtp Objeto a verificar
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JDateChooser pDtp,IEnumComponents pField)
    {
        return ValidateField(masterComponent,pDtp,pField.isRequired(),pField.getFieldDescription());
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JDateChooser
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pDtp Objeto a verificar.
     * @param pObligatorio Indica si el campo es o no Obligatorio.
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTabbedPane pPanel,int pTabIndex, JDateChooser pDtp, boolean pRequired, String pFieldDescription)
    {
        if ((pDtp.getDate() == null) && (pRequired))
        {
            JOptionPane.showMessageDialog(masterComponent,"Debe ingresar " + pFieldDescription,productName,JOptionPane.WARNING_MESSAGE);
            
            if (pPanel!=null)
            { pPanel.setSelectedIndex(pTabIndex);
              FocusHelper.patchTabFocusDateChooser(pDtp);
            }
            else
            {
                FocusHelper.focusDateChooser(pDtp);
            }
            return false;
        }
        return true;
    }
    
       
    /**
     * Valida el ingreso de Información aplicable para un JCheckBox
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pChkBox Objeto a verificar
     * @param pCampo La definici�n del Campo que se quiere validar.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent,JCheckBox pChkBox, IEnumComponents pField)
    {
        return ValidateField(masterComponent,null,0,pChkBox,pField.isRequired(),pField.getFieldDescription());
    }
    
    
    /**
     * Valida el ingreso de Información aplicable para un JCheckBox
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pChkBox Objeto a verificar
     * @param pObligatorio Indica si el campo es o no Obligatorio.
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent,JCheckBox pChkBox, boolean pRequired, String pFieldDescription)
    {
        return ValidateField(masterComponent,null,0,pChkBox,pRequired,pFieldDescription);
    }
    
    /**
     * Valida el ingreso de Información aplicable para un JCheckBox
     * @param pComponentePadre Referencia del Componente Padre,
     * Requerido para poder mostrar un Cuadro de Mensaje.
     * @param pPanel Referencia del Panel donde se encuentra
     * el Componente con la Información a Validar.
     * Es requerido para que en caso de ser necesaria una correci�n
     * el Panel se cambie donde se encuentre el objeto con la Información a corregir.
     * @param pIndiceTab El Indice del Panel del Objeto que se quiere Validar.
     * @param pChkBox Objeto a verificar
     * @param pObligatorio Indica si el campo es o no Obligatorio.
     * @param pDescripcionCampo Nombre del campo, este se mostrar� en el mensaje.
     * @return True .- Cumple con la validaci�n.
     */
    public static boolean ValidateField(Component masterComponent, JTabbedPane pPanel,int pTabIndex,JCheckBox pChkBox, boolean pRequired, String pFieldDescription)
    {
        if ((!pChkBox.isSelected()) && (pRequired))
        {
            JOptionPane.showMessageDialog(masterComponent,"Debe seleccionar " + pFieldDescription,productName,JOptionPane.WARNING_MESSAGE);
            
            if (pPanel!=null)
            { pPanel.setSelectedIndex(pTabIndex);
              FocusHelper.patchTabFocus(pChkBox);
            }
            else pChkBox.requestFocus();
            
            return false;
        }
        return true;
    }
    
    
    
    /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * @param a String a
     * @param b String b
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     * son Iguales.
     */
    public static boolean equals(String a, String b)
    {
        if (a == null) a = "";
        if (b == null) b = "";
        
        return a.equals(b);
        
    }
    
    /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * @param a Date a
     * @param b Date b
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     * son Iguales.
     */
    public static boolean equals(Date a, Date b)
    {
        if (a == null)
        {  return (b== null);
        }
        else
        {
            return a.equals(b);
        }
    }
    
    /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * @param a Integer a
     * @param b Integer b
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     * son Iguales.
     */
    public static boolean equals(Integer a, Integer b)
    {
        if (a == null)
        {  return (b== null);
        }
        else
        {
            return a.equals(b);
        }
    }
    
    /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * @param a Object a
     * @param b Object b
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     * son Iguales.
     */
    public static boolean equals(Object a, Object b)
    {
        if (a == null)
        {  return (b== null);
        }
        else
        {
            return a.equals(b);
        }
    }
    
    /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * son Iguales.
     * @param pDatoDecimal Objecto BigDecimal
     * @param pDatoString Dato String
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     */
    public static boolean equals(BigDecimal pDatoDecimal, String pDatoString)
    {
        if (pDatoDecimal == null)
        {
            if (pDatoString == null)
                return true;
            else return (pDatoString.length() == 0 );
            
        }
        else
        {
            if (pDatoString == null)
                return false;
            else if (pDatoString.length() == 0 )
                return false;
            
            BigDecimal tmp = UtilMath.Round(new BigDecimal(pDatoString));
            
            return tmp.equals(pDatoDecimal);
        }
    }
    
     /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * son Iguales.
     * @param pDatoDecimal Objecto BigDecimal
     * @param pDatoString Dato String
     * @param escala Dato entero que identifica la escala del dato string que se comparar� con el BigDecimal
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     */
    public static boolean equalsOriginal(BigDecimal pDatoDecimal, String pDatoString, int escala)
    {
        if (pDatoDecimal == null)
        {
            if (pDatoString == null)
                return true;
            else return (pDatoString.length() == 0 );
            
        }
        else
        {
            if (pDatoString == null)
                return false;
            else if (pDatoString.length() == 0 )
                return false;
            
            BigDecimal tmp = new BigDecimal(pDatoString);
            tmp.setScale(escala);
            
            return tmp.equals(pDatoDecimal);
        }
    }
    
    /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * son Iguales.
     * @param pDatoNumerico Objecto Integer
     * @param pDatoString Dato String
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     */
    public static boolean equals(Integer pDatoNumerico, String pDatoString)
    {
        if (pDatoNumerico == null)
        {
            if (pDatoString == null)
                return true;
            else return (pDatoString.length() == 0 );
            
        }
        else
        {
            if (pDatoString == null)
                return false;
            else if (pDatoString.length() == 0 )
                return false;
            
            Integer tmp = new Integer(pDatoString);
            
            return tmp.equals(pDatoNumerico);
        }
    }
    
    
    /**
     * Compara que los Objetos o Tipos de Datos Definidos como Par�metros sean Iguales.
     * son Iguales.
     * @param pDatoNumerico Objecto Long
     * @param pDatoString Dato String
     * @return True, si los los Objetos o Tipos de Datos Definidos como Par�metros
     */
    public static boolean equals(Long pDatoNumerico, String pDatoString)
    {
        if (pDatoString.length()==0) pDatoString = null;
        
        if (pDatoNumerico == null)
            return (pDatoString == null);
        else
        {
            if (pDatoString == null)
                return false;
            
            Long tmp = new Long(pDatoString);
            
            return tmp.equals(pDatoNumerico);
        }
    }
    
    
     public static boolean validateIpAddress(Component masterComponent, String iPaddress){     
       if (RegexValidator.ValidateIPAddress(iPaddress) <= 0)
        {
            JOptionPane.showMessageDialog(masterComponent,"La dirección IP no es válida",productName,JOptionPane.WARNING_MESSAGE);
            return false;
       }
       else
                return true;
     }
     
     
    
}     