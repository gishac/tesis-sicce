/*
 * UsuarioCellRenderer.java
 *
 */

package sicce.api.businesslogic.renderer;


import sicce.api.info.Field;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Modifica la manera de presentar la informacion.
 * @author Karu
 */
public class FieldsCellRenderer extends JLabel implements ListCellRenderer
{
     
     private Field temporal;
    /**
     * Modo de renderizacion por defecto.
     */
     protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer ();

     /**
     * Modo de Renderizacion especifico para los campos listados en el componente.
     * @param list Objeto List a aplicar renderizacion
     * @param value Object Objeto TadeUsuario a mostrar
     * @param index Indice o posicion de la lista
     * @param isSelected Se trata o no de un elemento seleccionado.
     * @param cellHasFocus cellHasFocus
     * @return Component Componente en el cual se ha aplicado el metodo de renderizacion personalizado.
     */
     public Component getListCellRendererComponent (
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
     {
          JLabel renderer = (JLabel) defaultRenderer
                 .getListCellRendererComponent (list, value, index, isSelected,
                 cellHasFocus);
          
          if (value == null)
          {
               renderer.setIcon (null);
               renderer.setText (null);
          }
          else if (value instanceof Field)
          {
               temporal = (Field)value;                                                              
               renderer.setText (temporal.getTitle());
          }
          return renderer;
     }
}
