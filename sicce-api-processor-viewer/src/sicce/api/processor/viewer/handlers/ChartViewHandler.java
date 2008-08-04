/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.processor.viewer.observers.ChartObserver;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import sicce.api.businesslogic.MeasureBizObject;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class ChartViewHandler {

    private TimeSeriesCollection series;
    private HashMap<String, TimeSeries> seriesMap;
    private TimeSeries virtual1 = new TimeSeries("Medidor virtual 1", Millisecond.class);
    private TimeSeries virtual2 = new TimeSeries("Medidor virtual 2", Millisecond.class);
    private List<String> nonVisiblePowerMeters;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private int currentRegisterID;
    private String currentRegisterName;
    private String chartTitle;
    private Set<IPowerMeter> powerMetersForCurrentUser;

    
    public ChartViewHandler(Set<IPowerMeter> powerMetersForCurrentUser){
        this.powerMetersForCurrentUser = powerMetersForCurrentUser;
    }
    
    /**
     * 
     * @return
     */
    private List<String> getNonVisiblePowerMeters() {
        if (nonVisiblePowerMeters == null) {
            nonVisiblePowerMeters = new ArrayList<String>();
        }
        return nonVisiblePowerMeters;
    }

    /**
     * 
     * @return
     */
    private TimeSeriesCollection getSeries() {
        if (series == null) {
            series = new TimeSeriesCollection();
        }
        return series;
    }

    /**
     * 
     * @return
     */
    private HashMap<String, TimeSeries> getSeriesMap() {
        if (seriesMap == null) {
            seriesMap = new HashMap<String, TimeSeries>();
        }
        return seriesMap;
    }

    /**
     * 
     * @return
     */
    private TimeSeriesCollection BuildSeries() {
       
        for (IPowerMeter powerMeter : powerMetersForCurrentUser) {
            TimeSeries timeSeries = new TimeSeries(powerMeter.getDescription(), Millisecond.class);
            getSeries().addSeries(timeSeries);
            getSeriesMap().put(powerMeter.getSerial(), timeSeries);
//            getSeries().addSeries(virtual1);
//            getSeries().addSeries(virtual2);
//
//            getSeriesMap().put("v1", virtual1);
//            getSeriesMap().put("v2", virtual2);
        }
        return getSeries();
    }

    /**
     * 
     * @return
     */
    private DateAxis ConfigureXAxis() {
        DateAxis xAxis = new DateAxis("Hora de Lectura");
        xAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 7));
        xAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 9));
        xAxis.setAutoRange(true);
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setTickLabelsVisible(true);
        return xAxis;
    }

    /**
     * 
     * @return
     */
    private NumberAxis ConfigureYAxis() {
        NumberAxis yAxis = new NumberAxis("Lecturas");
        yAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 7));
        yAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 9));
        return yAxis;
    }

    /**
     * 
     * @param dataset
     * @param xAxis
     * @param yAxis
     * @param renderer
     * @return
     */
    private XYPlot BuildPlot(TimeSeriesCollection dataset, DateAxis xAxis, NumberAxis yAxis, XYItemRenderer renderer) {
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        GradientPaint gradientBrush = new GradientPaint(0, 0, new Color(245, 245, 245),
                1, 1, Color.WHITE, true);
        plot.setBackgroundPaint(gradientBrush);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets(5.0, 8.0, 5.0, 5.0));
        return plot;
    }

    /**
     * 
     * @return
     */
    private XYSplineRenderer ConfigureChartRenderer() {
        XYSplineRenderer renderer = new XYSplineRenderer();
        renderer.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        renderer.setShapesVisible(true);
        renderer.setShapesFilled(true);
        renderer.setDrawOutlines(true);
        renderer.setToolTipGenerator(new ToolTipHandler());
        return renderer;
    }

    /**
     * 
     * @param chartTitle
     * @return
     */
    public ChartPanel BuildChart(String chartTitle) {
        this.chartTitle = chartTitle;
        TimeSeriesCollection dataset = BuildSeries();
        DateAxis xAxis = ConfigureXAxis();
        NumberAxis yAxis = ConfigureYAxis();
        XYPlot plot = BuildPlot(dataset, xAxis, yAxis, ConfigureChartRenderer());
        chart = new JFreeChart(chartTitle + " - " + currentRegisterName, new Font("Verdana", Font.BOLD, 10), plot, true);
        chart.setBackgroundPaint(Color.WHITE);
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 8, 4, 4),
                BorderFactory.createLineBorder(Color.black)));
        chartPanel.setDisplayToolTips(true);
        return chartPanel;
    }

    /**
     * 
     * @param measure
     */
    public void ProcessMeasure(IMeasure measure) {
        TimeSeries powerMeterSeries = getSeriesMap().get(measure.getPowerMeter().getSerial());
        powerMeterSeries.add(new Millisecond(), GetMeasure(measure));
//        virtual1.add(new Millisecond(), Math.random() * 1000);
//        virtual2.add(new Millisecond(), Math.random() * 1000);
    }

    /**
     * 
     * @return
     */
    public ChartObserver getChartObserver() {
        return new ChartObserver(this);
    }

    /**
     * 
     * @param powerMeterSerial
     * @param state
     */
    public void HandlePowerMeterVisibility(String powerMeterSerial, boolean state) {
        if (!state && getSeriesMap().containsKey(powerMeterSerial)) {
            getSeries().removeSeries(getSeriesMap().get(powerMeterSerial));
        } else {
            getSeries().addSeries(getSeriesMap().get(powerMeterSerial));
        }
    }
    
    /**
     * 
     * @param registerID
     * @param registerName
     */
    public void HandleMeasureChanged(int registerID, String registerName){
        currentRegisterID = registerID;
        currentRegisterName = registerName;
        if(chart != null){
            for(Object timeSeries : series.getSeries()){
                ((TimeSeries) timeSeries).clear();
            }
            chart.setTitle(chartTitle + " - " + currentRegisterName);
        }        
    }
    
    
    /**
     * 
     * @param measure
     * @return
     */
    private double GetMeasure(IMeasure measure){
        return new MeasureBizObject().GetMeasure(measure, currentRegisterID);
    }
    
    /**
     * 
     */
    public void SaveChart(){
        try {
            if (chartPanel != null) {
                chartPanel.doSaveAs();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar el archivo.");
            Logger.getLogger(ChartViewHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
