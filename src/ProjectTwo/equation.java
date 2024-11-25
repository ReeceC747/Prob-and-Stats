package ProjectTwo;

import java.util.ArrayList;

/**
 * This class will be used to create data points with a specific function
 * function will be f(x) = 1/10*x^2
 */
public class equation 
{
    //Left and right bounds of the function
    private double lowerBound, upperBound;

    //Constructor that initalizes the bounds, 
    public equation(double lowerBoundP, double upperBoundP)
    {
        //If the lower bound is less than the upper bound, set the bounds
        if(lowerBoundP < upperBoundP)
        {
            lowerBound = lowerBoundP;
            upperBound = upperBoundP;
        }
        //else throw an exception: lower bound cannot be greater than or equal to the upper bound
        else if(lowerBoundP >= upperBoundP)
        {
            throw new IllegalArgumentException("Lower bound cannot be greater than or equal to the upper bound");
        }
    }

    /**
     * returns an arraylist of arrays containing the x and y values of the function, including the bounds
     * @param numberOfPoints the number of points to be generated
     * @return Arraylist of arrays containing the x and y values of the function
     * @return null if the bounds arent equally divisible by the interval
     */
    public ArrayList<Double[]> dataPoints(int numberOfPoints)
    {
        if(numberOfPoints <= 2)
        {
            throw new IllegalArgumentException("Number of points must be greater than 2");
        }
        ArrayList<Double[]> dataPoints = new ArrayList<>();
        double interval = (upperBound - lowerBound) / (numberOfPoints - 1);
        double x = lowerBound;


        for (int i = 0; i < numberOfPoints; i++) 
        {
            Double[] point = new Double[2];
            point[0] = x;
            point[1] = solveForY(x);
            dataPoints.add(point);
            x += interval;
        }

        Double[] lastPoint = dataPoints.get(dataPoints.size() - 1);
        if(lastPoint[0] != upperBound)
        {
            Double[] upperBoundPoint = new Double[2];
            upperBoundPoint[0] = upperBound;
            upperBoundPoint[1] = solveForY(upperBound);
            dataPoints.add(upperBoundPoint);
        }



        return dataPoints;
    }

    /**
     * solves for Y given a value of x for the function f(x) = 1/10*x^2
     * @param x
     * @return y value of the function
     */
    private double solveForY(double x)
    {
        return (1.0/10.0) * Math.pow(x, 2);
    }

    //Getters and Setters -----------------------------------------------------

    public double getLowerBound() 
    {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) 
    {
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() 
    {
        return upperBound;
    }

    public void setUpperBound(double upperBound) 
    {
        this.upperBound = upperBound;
    }
}
