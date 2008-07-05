/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.handlers;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author gish@c
 */
public class ToolTipHandler implements XYToolTipGenerator {

    public String generateToolTip(XYDataset dataset, int series, int item) {
        double yValue = dataset.getYValue(series, item);
        String seriesName = dataset.getSeriesKey(series).toString();
        return seriesName + "- Lectura : " + String.valueOf(yValue);
        
    }

}
