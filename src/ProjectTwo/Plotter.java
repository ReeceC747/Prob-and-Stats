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
    public File makeCSV(ArrayList<Double[]> dataPoints, String fileName)
    {
        //try to make a printWriter object with the given file name
        File file = new File(fileName);
        try(PrintWriter writer = new PrintWriter(file))
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

        return file;
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
            String line = scan.nextLine();
                if(!line.equals("x,y"))
                {
                    Double[] point = new Double[2];
                    String[] parts = line.split(",");
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
    public File saltData(File csv, String saltedFileName) throws IOException
    {
        File saltedFile = new File(saltedFileName);
        try(Scanner scan = new Scanner(csv))
        {
            ArrayList<String> points = new ArrayList<>();
            Random generator = new Random();
            while(scan.hasNext())
            {
                points.add(scan.nextLine());
            }

            StatsLibrary stats = new StatsLibrary();

            ArrayList<Double[]> dataPoints = makeDataPoints(csv);

            ArrayList<Double> yValues = new ArrayList<>();
            for(Double[] point : dataPoints)
            {
                yValues.add(point[1]);
            }

            double variance = stats.findVariance(yValues);

            try(PrintWriter writer = new PrintWriter(saltedFile))
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
        return saltedFile;
    }

    /**
     * Makes a new csv file by smoothing the data values in the given csv by taking the average of current and the the three points 
     * before and after it
     * @param csv
     * @param smoothedFileName
     * @throws IOException
     */
    public File smoothData(File csv, String smoothedFileName) throws IOException
    {
        ArrayList<Double[]> data = makeDataPoints(csv);

        File smoothedFile;;

        for(int i = 1; i < data.size(); i++)
        {
            smoothPoint(data, i);
        }

        try(Scanner scan = new Scanner(csv))
        {
            smoothedFile = makeCSV(data, smoothedFileName);
        }
        catch(FileNotFoundException e)
        {
            smoothedFile = new File(smoothedFileName);
            e.printStackTrace();
        }
        return smoothedFile;
    }

    /**
     * This method will smooth a point by averaging it with the three points before and after it (including the current one)
     * @param dataSet
     * @param index
     */
    private void smoothPoint(ArrayList<Double[]> dataSet, int index)
    {
        double sum = 0;
        double count = 0;
        for(int i = index - 3; i < index + 4; i++)
        {
            
            try
            {
                sum += dataSet.get(i)[1];
                count++;
            }
            catch(IndexOutOfBoundsException e)
            {
                continue;
            }
        }
        dataSet.get(index)[1] = sum / count;
    }



}
