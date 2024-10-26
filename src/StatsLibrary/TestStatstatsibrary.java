package StatsLibrary;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Test Class for Statstatsibrary methods
 */
public class TestStatstatsibrary 
{
    public static void main(String[] args)
    {
        //Statstatsibrary object
        StatsLibrary stats = new StatsLibrary();

        //Empty ArrayLists of numbers
        ArrayList<Integer> listOfNumbersOne = new ArrayList<>(Arrays.asList(23, 45, 67, 89, 12, 34, 56, 78, 90, 23));
        ArrayList<Integer> listOfNumbersTwo = new ArrayList<>(Arrays.asList(15, 37, 62, 84, 19, 31, 54, 76, 98, 21));
        ArrayList<Integer> groupSizes = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 3));

        int distinctObjects = 21;
        int takenAtATime = 4;

        double probabilityA = .7;
        double probabilityB = .6;

        int trials = 5;
        int successes = 3;


        System.out.println("List of Numbers: " + listOfNumbersOne);

        //find Mean, Expected Value: 51.7
        System.out.println("The mean: " + stats.findMean(listOfNumbersOne));

        //FindMedian, Expected Value 61.5
        System.out.println("The median: " + stats.findMedian(listOfNumbersOne));

        //FindMode, Expected Value 23
        System.out.println("The mode: " + stats.findMode(listOfNumbersOne));

        //find standardDeviation, Expected Value ~28.6
        System.out.println("Standard Deviation: " + stats.findStandardDeviation(listOfNumbersOne));

        //Tests mn Rule, Expected Value 100
        System.out.println("The number of pairs with an element from each pair: " + stats.mnSize(listOfNumbersOne, listOfNumbersTwo));

        //Tests Permutation, Expected Value: 143,640
        System.out.println("The number of ways to choose " + takenAtATime + " objects from " + distinctObjects + " distinct objects: " 
            + stats.permutation(distinctObjects, takenAtATime));

        //Tests Partition, Expected Value: very big but first few digits are 68441
        System.out.println("The number of ways to partition a set of " + distinctObjects + " objects into distinct groups of Varying Sizes " + groupSizes 
            + " is: " + stats.partition(distinctObjects, groupSizes));

        //Tests Combination, Expected Value: 5985
        System.out.println("The number of ways to choose " + takenAtATime + " objects from " + distinctObjects + " objects: " 
            + stats.combination(distinctObjects, takenAtATime));

        //Tests Conditional Probability, Expected Value: .7
        System.out.println("The probability of A given B: " + stats.conditionalProbability(probabilityA, probabilityB));

        //Tests Bayes Theorem, Expected Value: .78
        System.out.println("The probability of A given B with bayes theorem: " + stats.bayes(probabilityA, probabilityB));

        //Tests Binomial Distribution, Expected Value: .3087
        System.out.println("The probability of " + successes + " successes in " + trials + " trials: " + stats.binomialDistribution(trials, successes, probabilityA));

        //Tests Geometric Distribution, Expected Value: .00567
        System.out.println("The probability of " + (trials - 1) + " fails until the first success: " + stats.geometricDistribution(trials, probabilityA));
    }

}
