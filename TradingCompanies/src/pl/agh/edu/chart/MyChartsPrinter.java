package pl.agh.edu.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import pl.agh.edu.companies.entitiy.Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by Grzegorz Zuber on 11.06.15.
 */
public class MyChartsPrinter extends ApplicationFrame {

    private static final String TITLE = "Capital of company";
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final float MINMAX = 200000;
    private static final int COUNT = 1000;
    private static final int FAST = 100;
    private static final int SLOW = FAST * 5;
    private static final Random random = new Random();
    private final List<Company> companies;
    private Timer timer;
    private int turnNumber = 0;

    private static class DayDTSC extends DynamicTimeSeriesCollection {

        public DayDTSC(int nSeries, int nMoments, RegularTimePeriod timeSample) {
            super(nSeries, nMoments, timeSample);
            if (timeSample instanceof Day) {
                this.pointsInTime = new Day[nMoments];
            }
        }
    }

    static final DynamicTimeSeriesCollection dataset =
        new DayDTSC(1, COUNT, new Day());

    public MyChartsPrinter(final String title, List<Company> companies) {
        super(title);
        this.companies = companies;

        Date date = new Date();
        dataset.setTimeBase(new Day(date));
        dataset.addSeries(gaussianData(), 0, "Capital");
        JFreeChart chart = createChart(dataset);

        this.add(new ChartPanel(chart), BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        this.add(btnPanel, BorderLayout.SOUTH);

    }

    private float[] gaussianData() {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = (float) 10000.0;
        }
        return a;
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                TITLE, "day", "capital", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        //range.setRange(-MINMAX, MINMAX);
        return result;
    }

    public static void createChart() {
        MyChartsPrinter demo = new MyChartsPrinter(TITLE, null);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    public static void appendData(float value) {
        float[] newData = new float[1];
        //newData[0] = randomValue();
        newData[0] = value;
        try {
            dataset.advanceTime();
            dataset.appendData(newData);
        } catch (Exception e) {
            dataset.appendData(newData);
        }
    }
}
