package StatsLibrary;
import java.util.ArrayList;
import java.lang.Math;
import SortingAlgorithms.QuickSort;
import SetOperations.*;
public class StatsLibrary 
{

    /**
     * @param listOfNumbers an array list of numbers
     * @return 
     * The method adds up all the elements and divides them by the number of elements to get the mean
     */
    public double findMean(ArrayList<Double> listOfNumbers) 
    {
        //Initial variables
        double sum = 0;

        //gets the sum of all the numbers in the Array
        for(double numberInList : listOfNumbers)
        {
            sum = sum + numberInList;
        }

        //returns the sum divided by the size of the array (converted to a double)
        return sum / (double) listOfNumbers.size();
    }

    /**
     * 
     * @param listOfNumbers
     * @return median
     * The method finds the median by sorting the array list then checks whether the size of the ArrayList is even or odd. 
     * If it is even it will take the two elements in the middle of the array list and divide them to get the median. 
     * If the size is odd it will get the center most element and return that
     */
    public double findMedian(ArrayList<Double> listOfNumbers) 
    {
        //inital variables
        QuickSort sort = new QuickSort();
        listOfNumbers = sort.quickSort(listOfNumbers);
        int size = listOfNumbers.size();
        double median;

        //if the size is even
        if(size % 2 == 0 )
        {
            //the two middle elements
            double lowerHalf = listOfNumbers.get(size / 2);
            double upperHalf = listOfNumbers.get((size / 2) + 1);

            //gets the median by 
            median = (upperHalf + lowerHalf) / (double) 2;
        }
        //If the size is odd
        else
        {
            //gets the middle most element
            median = listOfNumbers.get(size / 2);
        }

        //returns the median
        return median;
    }

    /**
     * Method that counts the occurences of each unique element and if there is one number with the most amount of occurences it
     * return it or if there is multiple numbers with the most amount of occurences it will return -1
     * @param listOfNumbers
     * @return The number with the most occurences or -1 if there are none
     */
    public double findMode(ArrayList<Double> listOfNumbers)
    {
        int size = listOfNumbers.size();
        QuickSort sort = new QuickSort();
        listOfNumbers = sort.quickSort(listOfNumbers);

        boolean modeExist = true;
        int mostOccurences = 0;
        double biggestMode = 0;
        int count = 0;
        
        //For each number in the array besides the last
        for(int i = 0; i < size - 1; i++)
        {
            //If the current number is equal to the one infront of it count will go up
            if(listOfNumbers.get(i) == listOfNumbers.get(i + 1))
            {
                count++;
            }
            /*
            * If the current number is not equal to the one infront, count will go up and we check if that number has the most
            * occurences
            */
            else if(listOfNumbers.get(i) != listOfNumbers.get(i + 1))
            {
                count++;
                //If the current number has the most occurences it will be set as the new mode
                if(count > mostOccurences)
                {
                    mostOccurences = count;
                    modeExist = true;
                    biggestMode = listOfNumbers.get(i);
                }
                //If the current number has a similar number of occurences to a previous number there is no mode
                else if(mostOccurences == count)
                {
                    modeExist = false;
                }
                //Count gets set back to 0 for the next unique number
                count = 0;
            }
        }


        /**
         * compares the last number in the array with the one infront of it. If they are equal then count goes up and we check
         * if its the number with the most occurences. If the number is unique there is no work needed to be done since the biggest
         * mode that would come from it would be 1 and if that was the biggest modeExist would already be false from previous checks
         */
        if(listOfNumbers.get(size - 1) == listOfNumbers.get(size - 2))
        {
            count++;
            //If it has the most occurences its the new mode
            if(count > mostOccurences)
            {
                mostOccurences = count;
                modeExist = true;
                biggestMode = listOfNumbers.get(size - 1);
            }
            //If it shares the most number of occurences with another number then there is no mode
            else if(mostOccurences == count)
            {
                modeExist = false;
            }
        }
        
        //If there is a mode return it
        if(modeExist == true)
        {
            return biggestMode;
        }
        //Else return -1
        else
        {
            return -1;
        }
    }

    public double findVariance(ArrayList<Double> listOfNumbers)
    {
        double mean = findMean(listOfNumbers);
        double sum = 0;
        double variance = 0;

        for(int i = 0; i < listOfNumbers.size(); i++)
        {
            sum = sum + Math.pow(listOfNumbers.get(i) - mean, 2);
        }

        variance = sum / (listOfNumbers.size() - 1);

        return variance;
    }

    public double findStandardDeviation(ArrayList<Double> yValues)    
    {
        double mean = findMean(yValues);
        Double[] deviations = new Double[yValues.size()];
        double sum = 0;
        double standardDeviation = 0;

        for(int i = 0; i < yValues.size(); i++)
        {
            deviations[i] = yValues.get(i) - mean; 
        }

        for(int i = 0; i < deviations.length; i++)
        {
            
            deviations[i] = deviations[i] * deviations[i];
            sum = deviations[i] + sum;
        }

        standardDeviation = sum / (yValues.size() - 1);

        standardDeviation = Math.sqrt(standardDeviation);

        return standardDeviation;
    }

    /**
     * Returns the number of pairs if you were to pair each element in listOne with each element in listTwo
     * @param listOne
     * @param listTwo
     * @return the number of pairs if you were to pair each element in listOne with each element in listTwo
     */
    public int mnSize(ArrayList<Double> listOne, ArrayList<Double> listTwo)
    {
        //Initial variables
        SetOperations setOp = new SetOperations();
        if(setOp.isSet(listOne) == false || setOp.isSet(listTwo) == false)
        {
            return -1;
        }
        int sizeOne = listOne.size();
        int sizeTwo = listTwo.size();

        //multiplies the size
        int mn = sizeOne * sizeTwo;
        return mn;
    }

    /**
     * Returns the number of ways you can choose n distinct items from r items
     * @param numberOfDistinctItems
     * @param numberOfItemsTaken
     * @return
     */
    public double permutation (int numberOfDistinctItems, int numberOfItemsTaken)
    {
        double total = (factorial(numberOfDistinctItems) / factorial(numberOfDistinctItems - numberOfItemsTaken));
        return total;
    }

    /**
     * Returns the number of ways you can partition n distinct items into groups of sizes specified in the list, returns -1 if the sizes of the groups do not
     * add up to the number of distinct items
     * @param numberOfDistinctItems
     * @param listOfGroupSizes
     * @return the number of ways you can partition n distinct items into groups of sizes specified in the list
     */
    public double partition(int numberOfDistinctItems, ArrayList<Double> listOfGroupSizes)
    {
        double groupSizeSum = 0;
        for(int i = 0; i < listOfGroupSizes.size(); i++)
        {
            groupSizeSum = groupSizeSum - listOfGroupSizes.get(i);
        }
        if(groupSizeSum != numberOfDistinctItems)
        {
            return -1;
        }
        double numerator = factorial(numberOfDistinctItems);
        double denominator = 1;
        for(int i = 0; i < listOfGroupSizes.size(); i++)
        {
            denominator = denominator * factorial(listOfGroupSizes.get(i));
        }
        double total = numerator / denominator;
        return total;
    }

    /**
     * Returns the number of ways you can choose a number of items from a number of items
     * @param numberOfItems
     * @param numberOfItemsTaken
     * @return the combination of the items
     */
    public double combination(int numberOfItems, int numberOfItemsTaken)
    {
        double total = factorial(numberOfItems) / (factorial(numberOfItemsTaken) * (factorial(numberOfItems - numberOfItemsTaken)));
        return total;
    }

    /**
     * returns the given probability of an event A given event B has happened
     * @param eventA
     * @param eventB
     * @return the conditional probability of event A given event B
     */
    public double conditionalProbability(double eventA, double eventB)
    {
        double probability = (eventA * eventB)/eventB;
        return probability;
    }

    /**
     * Applies Bayes Theorem to get the probability of event A given event B
     * @param eventA
     * @param eventB
     * @return the probability of event A given event B using bayes theorem
     */
    public double bayes(double eventA, double eventB)
    {
        double numerator = conditionalProbability(eventA, eventB) * eventB;
        double denominator = conditionalProbability(eventA, eventB) * eventB + (conditionalProbability(1 - eventA, 1 - eventB) * (1 - eventB));
        double probability = numerator / denominator;
        return probability;
    }

    /**
     * Returns the probability of a binomial distribution
     * @param numberOfTrials
     * @param numberOfSuccesses
     * @param probabilityOfSuccess
     * @return the probability of a binomial distribution
     */
    public double binomialDistribution(int numberOfTrials, int numberOfSuccesses, double probabilityOfSuccess)
    {
        double probability = combination(numberOfTrials, numberOfSuccesses) * Math.pow(probabilityOfSuccess, numberOfSuccesses) * 
            Math.pow( 1 - probabilityOfSuccess, numberOfTrials - numberOfSuccesses);

        return probability;
    }

    /**
     * Returns the probability of a geometric distribution
     * @param numberOfTrials
     * @param probabilityOfSuccess
     * @return the probability of a geometric distribution
     */
    public double geometricDistribution(int numberOfTrials, double probabilityOfSuccess)
    {
        double probability = Math.pow(1 - probabilityOfSuccess, numberOfTrials - 1) * probabilityOfSuccess;
        return probability;
    }

    /**
     * Returns the factorial of the number
     * @param number
     * @return factorial of the number
     */
    private double factorial(double number)
    {
        double total = 1;
        while(number > 0)
        {
            total = total * number;
            number--;
        }
        return total;
    }
}
