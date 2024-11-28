package ProjectTwo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlotterTest 
{
    public static void main(String[] args)
    {
        equation eq = new equation(122, 344);
        Plotter plot = new Plotter();
        //plot.makeCSV(eq.dataPoints(48), "dataSix.csv");
        File file = new File("Project Two Documents/ExcelFiles/dataOne.csv");
        try 
        {
            plot.saltData(file, "SaltedDataOne.csv");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
