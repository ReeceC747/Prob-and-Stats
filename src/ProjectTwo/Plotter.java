package ProjectTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import StatsLibrary.StatsLibrary;

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

    /**
     * This method will read a csv file and return an arraylist of arrays containing the x and y values of the data points
     * @param csv
     * @return an arraylist of arrays containing the x and y values of the data points
     * @throws FileNotFoundException
     */
    public ArrayList<Double[]> makeDataPoints(File csv) throws FileNotFoundException
    {
        try(Scanner scan = new Scanner(csv))
        {
            ArrayList<Double[]> dataPoints = new ArrayList<>();
            while(scan.hasNext())
            {
                if(!scan.nextLine().equals("x,y"))
                {
                    Double[] point = new Double[2];
                    String[] parts = scan.nextLine().split(",");
                    point[0] = Double.parseDouble(parts[0]); //x value
                    point[1] = Double.parseDouble(parts[1]); //y value
                    dataPoints.add(point);
                }

            }
            return dataPoints;
        }
    }

    /**
     * This method will salt the data of a given csv file, it will use the varaince of the data set as the bounds for randomly generated numbers to
     * add or subtract from the data points
     * @param csv
          * @throws IOException 
          */
         public void saltData(File csv, String saltedFileName) throws IOException
    {
        try(Scanner scan = new Scanner(csv))
        {
            ArrayList<String> points = new ArrayList<>();
            Random generator = new Random();
            while(scan.hasNext())
            {
                points.add(scan.nextLine());
            }

            //get the mean and subrtract it from each data point, square it, add up all the results and divide by the number of data points - 1
            StatsLibrary stats = new StatsLibrary();

            ArrayList<Double[]> dataPoints = makeDataPoints(csv);

            ArrayList<Double> yValues = new ArrayList<>();
            for(Double[] point : dataPoints)
            {
                yValues.add(point[1]);
            }

            double variance = stats.findVariance(yValues);

            try(PrintWriter writer = new PrintWriter(new FileWriter(saltedFileName)))
            {
                writer.println("x,y"); //header
                for(String point : points)
                {
                    if(point.equals("x,y"))
                    {
                        continue;
                    }
                    String[] parts = point.split(",");
                    double x = Double.parseDouble(parts[0]);
                    double y = Double.parseDouble(parts[1]);
                    double salt = (generator.nextDouble() * 2 * variance) - variance;
                    writer.println(x + "," + (y + salt));
                }
            }






        }
    }



}
