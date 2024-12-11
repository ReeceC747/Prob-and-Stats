package ProjectTwo.JFreeChartPlotter;

import java.util.ArrayList;

import org.jfree.chart.ChartPanel;

import ProjectTwo.Equation;

public class JFreeTester 
{
    public static void main(String[] args)
    {
        Equation equation = new Equation(0, 1000);
        ArrayList<Double[]> dataPoints = equation.dataPoints(100);
        JFreeChartPlot chart = new JFreeChartPlot("Graph", "Graph of x^2/10", dataPoints);
        ChartPanel chartPanel = chart.plot();
        chart.salt(chartPanel);
        chart.smooth(chartPanel);
        chart.smooth(chartPanel);
        chart.pack();
        chart.setVisible(true);

    }

}
