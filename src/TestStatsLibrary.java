import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class TestStatsLibrary 
{
    public static void main(String[] args)
    {
        StatsLibrary sL = new StatsLibrary();
        Random generator = new Random();

        //Make an array list of numbers 0 -> 99
        ArrayList<Integer> listOfNumbers = new ArrayList();
        for(int i = 0; i < 9; i++)
        {
            int rng = generator.nextInt(21);
            listOfNumbers.add(rng);
        }


        //find Mean
        System.out.println(sL.findMean(listOfNumbers));

        //FindMedian
        System.out.println(sL.findMedian(listOfNumbers));

        //FindMode
        System.out.println(sL.findMode(listOfNumbers));

        //find standardDeviation
        System.out.println(sL.findStandardDeviation(listOfNumbers));


    }
}
