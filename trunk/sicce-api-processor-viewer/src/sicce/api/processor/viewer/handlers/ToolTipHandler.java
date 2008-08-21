/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.handlers;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;

/**
 * Manejador del toolTip para el grafico
 * @author gish@c
 */
public class ToolTipHandler implements XYToolTipGenerator {

    /**
     * Crea el texto a ser mostrado en el tooltip de los puntos del grafico
     * @param dataset Fuente de datos del grafico
     * @param series Indice de la serie en el grafico
     * @param item Indice del punto donde se va a mostrar el toolTip
     * @return Texto del toolTip
     */
    public String generateToolTip(XYDataset dataset, int series, int item) {
        double yValue = dataset.getYValue(series, item);
        String seriesName = dataset.getSeriesKey(series).toString();
        return seriesName + "- Lectura : " + String.valueOf(yValue);
        
    }

}
