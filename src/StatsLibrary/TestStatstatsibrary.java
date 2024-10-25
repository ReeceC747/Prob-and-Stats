package StatsLibrary;
import java.util.ArrayList;
import java.util.Random;

/**
 * Test Class for Statstatsibrary methods
 */
public class TestStatstatsibrary 
{
    public static void main(String[] args)
    {
        //Statstatsibrary object
        StatsLibrary stats = new StatsLibrary();

        //Random object
        Random generator = new Random();

        //Empty ArrayList of numbers
        ArrayList<Integer> listOfNumbers = new ArrayList<>();

        //Fill the ArrayList with random numbers from 0 to 99
        for(int i = 0; i < 10; i++)
        {
            listOfNumbers.add(generator.nextInt(100));
        }


        System.out.println("List of Numbers: " + listOfNumbers);

        //find Mean
        System.out.println("The mean: " + stats.findMean(listOfNumbers));

        //FindMedian
        System.out.println("The median: " + stats.findMedian(listOfNumbers));

        //FindMode
        System.out.println("The mode: " + stats.findMode(listOfNumbers));

        //find standardDeviation
        System.out.println("Standard Deviation: " + stats.findStandardDeviation(listOfNumbers));

        //Tests mn Rule
        System.out.println("The number of pairs with an element from each pair:" + stats.mnSize(listOfNumbers, listOfNumbers));

    }
}
