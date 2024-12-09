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
    //Constructor
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
        //try to make a scanner object with the given file
        try(Scanner scan = new Scanner(csv))
        {
            //An arraylist of arrays containing the x and y values of the data points
            ArrayList<Double[]> dataPoints = new ArrayList<>();
            //while there are more lines in the file, add the x and y values to the arraylist
            while(scan.hasNext())
            {
            String line = scan.nextLine();
                //if the line is not the header, add the x and y values to the arraylist
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
        //New file to write the salted data to
        File saltedFile = new File(saltedFileName);

        //try to make a scanner object with the given file
        try(Scanner scan = new Scanner(csv))
        {
            //An arraylist of strings containing the x and y values of the data points
            ArrayList<String> points = new ArrayList<>();

            //Random number generator to salt the data
            Random generator = new Random();

            //while there are more lines in the file, add the x and y values to the arraylist
            while(scan.hasNext())
            {
                points.add(scan.nextLine());
            }

            //StatsLibrary object to find the standard deviation of the data set
            StatsLibrary stats = new StatsLibrary();

            //Arraylist of arrays containing the x and y values of the data points
            ArrayList<Double[]> dataPoints = makeDataPoints(csv);

            //Arraylist of y values
            ArrayList<Double> yValues = new ArrayList<>();

            //Add all the y values to the arraylist
            for(Double[] point : dataPoints)
            {
                yValues.add(point[1]);
            }

            //Find the standard deviation of the y values
            double standardDeviation = stats.findStandardDeviation(yValues);

            //try to make a printWriter object with the given file name
            try(PrintWriter writer = new PrintWriter(saltedFile))
            {

                writer.println("x,y"); //header
                //for each point in the arraylist, salt the y value and write it to the file
                for(String point : points)
                {
                    //if the point is the header, skip it
                    if(point.equals("x,y"))
                    {
                        continue;
                    }
                    //split the point into x and y values
                    String[] parts = point.split(",");
                    double x = Double.parseDouble(parts[0]);
                    double y = Double.parseDouble(parts[1]);
                    //Make salt for the Y value
                    double salt = (generator.nextDouble() * 2 * standardDeviation) - standardDeviation;
                    //write the x and y values to the file with the salt added to the y value
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
        //Arraylist of arrays containing the x and y values of the data points
        ArrayList<Double[]> data = makeDataPoints(csv);

        //New file to write the smoothed data to
        File smoothedFile;

        //for each point in the arraylist, smooth the y value
        for(int i = 1; i < data.size(); i++)
        {
            smoothPoint(data, i);
        }

        //try to make a scanner object with the given file
        try(Scanner scan = new Scanner(csv))
        {
            //Make a new csv file with the smoothed data
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
        //Sum of the y values and the number of y values counted
        double sum = 0;
        double count = 0;
        //For the three points before, at and after the y value
        for(int i = index - 3; i < index + 4; i++)
        {
            //try to add the y value to the sum and increment the count, if the index is out of bounds, skip it
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
        //Set the y value to the average of the sum and the count
        dataSet.get(index)[1] = sum / count;
    }



}
