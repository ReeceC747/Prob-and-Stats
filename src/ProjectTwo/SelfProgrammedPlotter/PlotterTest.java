package ProjectTwo.SelfProgrammedPlotter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import ProjectTwo.Equation;

public class PlotterTest 
{
    public static void main(String[] args)
    {
        Equation eq = new Equation(84, 524);
        Plotter plot = new Plotter();
        File file = plot.makeCSV(eq.dataPoints(80), "dataOne.csv");
        try 
        {
            file = plot.saltData(file, "saltedDataOne.csv");
            file = plot.smoothData(file, "smoothedDataOneIterOne.csv");
            file = plot.smoothData(file, "smoothedDataOneIterTwo.csv");
            file = plot.smoothData(file, "smoothedDataOneIterThree.csv");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
