/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

import sicce.api.processor.viewer.observers.ChartObserver;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Image;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class ChartHandler {

    private TimeSeriesCollection series;
    private HashMap<String, TimeSeries> seriesMap;
    private TimeSeries virtual1 = new TimeSeries("Medidor virtual 1", Millisecond.class);
    private TimeSeries virtual2 = new TimeSeries("Medidor virtual 2", Millisecond.class);

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
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        PowerMeterBizObject powerMeterBizObject = new PowerMeterBizObject();
        List<IPowerMeter> powerMeters = powerMeterBizObject.GetAllPowerMeter();
        for (IPowerMeter powerMeter : powerMeters) {
            TimeSeries timeSeries = new TimeSeries(powerMeter.getDescription(), Millisecond.class);
            timeSeries.setMaximumItemAge(300000);
            getSeries().addSeries(timeSeries);

            getSeriesMap().put(powerMeter.getSerial(), timeSeries);
            dataset.addSeries(timeSeries);
            dataset.addSeries(virtual1);
            dataset.addSeries(virtual2);
        }
        return dataset;
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
        TimeSeriesCollection dataset = BuildSeries();
        DateAxis xAxis = ConfigureXAxis();
        NumberAxis yAxis = ConfigureYAxis();
        XYPlot plot = BuildPlot(dataset, xAxis, yAxis, ConfigureChartRenderer());
        JFreeChart chart = new JFreeChart(chartTitle, new Font("Verdana", Font.BOLD, 10), plot, true);
        chart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(chart);
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
        powerMeterSeries.add(new Millisecond(), measure.getPhaseToNeutralVoltagePhase3());
        virtual1.add(new Millisecond(), Math.random() * 1000);
        virtual2.add(new Millisecond(), Math.random() * 1000);
    }

    /**
     * 
     * @return
     */
    public ChartObserver getChartObserver() {
        return new ChartObserver(this);
    }
}
