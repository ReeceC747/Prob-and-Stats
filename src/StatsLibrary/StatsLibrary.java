package StatsLibrary;
import java.util.ArrayList;
import java.lang.Math;
import SortingAlgorithms.QuickSort;

public class StatsLibrary 
{

    /**
     * @param listOfNumbers an array list of numbers
     * @return 
     * The method adds up all the elements and divides them by the number of elements to get the mean
     */
    public double findMean(ArrayList<Integer> listOfNumbers) 
    {
        //Initial variables
        int sum = 0;

        //gets the sum of all the numbers in the Array
        for(int numberInList : listOfNumbers)
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
    public double findMedian(ArrayList<Integer> listOfNumbers) 
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
            int lowerHalf = listOfNumbers.get(size / 2);
            int upperHalf = listOfNumbers.get((size / 2) + 1);

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
    public int findMode(ArrayList<Integer> listOfNumbers)
    {
        int size = listOfNumbers.size();
        QuickSort sort = new QuickSort();
        listOfNumbers = sort.quickSort(listOfNumbers);

        boolean modeExist = true;
        int mostOccurences = 0;
        int biggestMode = 0;
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

    public double findStandardDeviation(ArrayList<Integer> listOfNumbers)    
    {
        double mean = findMean(listOfNumbers);
        Double[] deviations = new Double[listOfNumbers.size()];
        double sum = 0;
        double standardDeviation = 0;

        for(int i = 0; i < listOfNumbers.size(); i++)
        {
            deviations[i] = listOfNumbers.get(i) - mean; 
        }

        for(int i = 0; i < deviations.length; i++)
        {
            
            deviations[i] = deviations[i] * deviations[i];
            sum = deviations[i] + sum;
        }

        standardDeviation = sum / (listOfNumbers.size() - 1);

        standardDeviation = Math.sqrt(standardDeviation);

        return standardDeviation;
    }

    /**
     * Returns the number of pairs if you were to pair each element in listOne with each element in listTwo
     * @param listOne
     * @param listTwo
     * @return the number of pairs if you were to pair each element in listOne with each element in listTwo
     */
    public int mnSize(ArrayList<Integer> listOne, ArrayList<Integer> listTwo)
    {
        //Initial variables
        int sizeOne = listOne.size();
        int sizeTwo = listTwo.size();

        //multiplies the size
        int mn = sizeOne * sizeTwo;
        return mn;
    }
    
    
}
