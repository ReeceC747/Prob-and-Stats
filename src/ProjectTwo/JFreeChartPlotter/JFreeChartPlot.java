package ProjectTwo.JFreeChartPlotter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import StatsLibrary.StatsLibrary;

public class JFreeChartPlot extends ApplicationFrame
{

    private String chartTitle;
    private ArrayList<Double[]> dataPoints;

    public JFreeChartPlot(String applicationTitle, String title, ArrayList<Double[]> dataPointsP)
    {
        // App title for super class
        super(applicationTitle);   
        chartTitle = title;
        dataPoints = dataPointsP;
    }

    public ChartPanel plot()
    {
        // Create a dataset
        XYSeries series = new XYSeries("Data");

        
        for(Double[] point : dataPoints)
        {
            series.add(point[0], point[1]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Create a chart
        JFreeChart lineChart = ChartFactory.createXYLineChart(
            chartTitle, 
            "X-Axis", 
            "Y-Axis", 
            dataset
        );

        // Customize the x-axis
        XYPlot plot = lineChart.getXYPlot();
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(100)); // Set the tick unit to control spacing

        // Create a chart panel with the line chart
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);

        return chartPanel;
    }

    public ChartPanel salt(ChartPanel chartPanel)
    {
        ArrayList<Double> yValues = new ArrayList<>();
        ArrayList<Double> saltedYValues = new ArrayList<>();
        JFreeChart chart = chartPanel.getChart();
        XYPlot plot = chart.getXYPlot();
        XYDataset dataset = plot.getDataset();
        XYSeries series = new XYSeries("Salted Data");
        
        
        int seriesIndex = 0;
        for (int itemIndex = 0; itemIndex < dataset.getItemCount(seriesIndex); itemIndex++)
        {
            double y = dataset.getYValue(seriesIndex, itemIndex);
            yValues.add(y);
        }

        StatsLibrary stats = new StatsLibrary();
        double standardDeviation = stats.findStandardDeviation(yValues);

        for(int i = 0; i < yValues.size(); i++)
        {
            double salt = (Math.random() * 2 * standardDeviation) - standardDeviation;
            saltedYValues.add(yValues.get(i) + salt);
        }

        for(int i = 0; i < saltedYValues.size(); i++)
        {
            series.add(dataPoints.get(i)[0], saltedYValues.get(i));
        }

        XYSeriesCollection saltedDataset = new XYSeriesCollection(series);

        JFreeChart saltedChart = ChartFactory.createXYLineChart(
            "Salted " + chartTitle, 
            "X-Axis", 
            "Y-Axis", 
            saltedDataset
        );

        ChartPanel saltedChartPanel = new ChartPanel(saltedChart);

        saltedChartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        setContentPane(saltedChartPanel);

        return saltedChartPanel;
    } 

    public ChartPanel smooth(ChartPanel chartPanel)
    {
        ArrayList<Double> yValues = new ArrayList<>();
        ArrayList<Double> smoothedYValues = new ArrayList<>();
        JFreeChart chart = chartPanel.getChart();
        XYPlot plot = chart.getXYPlot();
        XYDataset dataset = plot.getDataset();
        XYSeries series = new XYSeries("Smoothed Data");
        
        
        //popilate yValues with the y values from the dataset (chart)
        for (int itemIndex = 0; itemIndex < dataset.getItemCount(0); itemIndex++)
        {
            double y = dataset.getYValue(0, itemIndex);
            yValues.add(y);
        }

        //for each y value, smooth it and add it to the smoothedYValues arraylist
        for(int i = 0; i < yValues.size(); i++)
        {
            smoothPoint(yValues, i);
            smoothedYValues.add(yValues.get(i));
        }

        //add the smoothed y values to the series
        for(int i = 0; i < smoothedYValues.size(); i++)
        {
            series.add(dataPoints.get(i)[0], smoothedYValues.get(i));
        }

        XYSeriesCollection smoothedDataset = new XYSeriesCollection(series);

        JFreeChart smoothedChart = ChartFactory.createXYLineChart(
            "Smoothed " + chartTitle, 
            "X-Axis", 
            "Y-Axis", 
            smoothedDataset
        );

        ChartPanel smoothedChartPanel = new ChartPanel(smoothedChart);

        smoothedChartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        setContentPane(smoothedChartPanel);

        return smoothedChartPanel;
    }

    private void smoothPoint(ArrayList<Double> values, int index)
    {
        //Sum of the y values and the number of y values counted
        double sum = 0;
        double count = 0;
        //For the three points before, at and after the y value
        for(int i = index - 3; i < index + 4; i++)
        {
            //try to add the y value to the sum and increment the count, if the index is out of bounds, skip it
            try
            {
                sum += values.get(i);
                count++;
            }
            catch(IndexOutOfBoundsException e)
            {
                continue;
            }
        }
        //Set the y value to the average of the sum and the count
        double smoothedPoint = sum / count;
        values.set(index, smoothedPoint);
    }
}
