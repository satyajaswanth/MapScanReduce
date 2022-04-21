/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity;

/**
 *
 * @author code
 */
import static Aggregate1.MainView.sz;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Job extends JFrame {

    public Job() {

        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        // chartPanel.setBackground(Color.black);
        add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 270));
        setContentPane(chartPanel);

        pack();
        setTitle("Speed Up Ratio vs Processing Time");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {

        XYSeries series1 = new XYSeries("Proposed");
        //XYSeries series2 = new XYSeries("LS PS");
        //XYSeries series3 = new XYSeries("ATLAS+");
       // XYSeries series4 = new XYSeries("JOSS-T,J");
        series1.add(0, 0);
        series1.add(10, 20);
       // series2.add(0, 10);
        //series2.add(50, 30);
        //series3.add(0, 20);
        //series3.add(50, 40);
        //series4.add(0, 30);
        //series4.add(50, 50);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        //dataset.addSeries(series2);
       /// dataset.addSeries(series3);
        ///dataset.addSeries(series4);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Speed Up Ratio vs Processing Time",
               "Processing Nodes",
                "Time(sec)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesPaint(1, Color.MAGENTA);
        renderer.setSeriesPaint(2, Color.green);
        renderer.setSeriesPaint(3, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.GRAY);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Speed Up Ratio vs Processing Time",
                new Font("Serif", java.awt.Font.BOLD, 18)
        )
        );

        return chart;

    }

}
