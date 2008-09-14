/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.controls;

import javax.swing.JPanel;
import org.jdesktop.application.ResourceMap;
import sicce.api.processor.Processor;

/**
 * Representa a un panel para mostrar datos dentro del monitor de mediciones
 * @author gish@c
 */
public class DataDisplayer extends JPanel {

    /**
     * Objeto que realiza las lecturas de los medidores
     */
    protected Processor processor;
    
    /**
     * Manejador de recursos
     */
    protected ResourceMap resourceMap;

    /**
     * Devuelve el objeto que realiza las lecturas de los medidores
     * @return Objeto que realiza las lecturas de los medidores
     */
    public Processor getProcessor() {
        return processor;
    }

    /**
     * Establece el objeto que realiza las lecturas de los medidores
     * @param processor Objeto que realiza las lecturas de los medidores
     */
    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
    
}
