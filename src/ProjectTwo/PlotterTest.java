package ProjectTwo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlotterTest 
{
    public static void main(String[] args)
    {
        equation eq = new equation(100, 1000);
        Plotter plot = new Plotter();
        plot.makeCSV(eq.dataPoints(100), "dataFour.csv");
    }
    //chamges
}
