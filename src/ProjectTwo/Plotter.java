package ProjectTwo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class will be used to make CSV files with given data points
 */
public class Plotter 
{
    public Plotter()
    {
    }

    /**
     * writes given data points into a csv file
     * @param dataPoints
     * @param fileName
     */
    public void makeCSV(ArrayList<Double[]> dataPoints, String fileName)
    {
        //try to make a printWriter object with the given file name
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName)))
        {
            //The header of the csv file (x and y coordinates)
            writer.println("x,y"); //header
            for(Double[] point : dataPoints)
            {
                //write the x and y coordinates of the data points
                writer.println(point[0] + "," + point[1]);
            }
        }


        //If the file cannot be found, print the stack trace
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}
