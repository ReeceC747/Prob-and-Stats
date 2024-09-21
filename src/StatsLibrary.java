import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
        listOfNumbers = sortArrayList(listOfNumbers);
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
        listOfNumbers = sortArrayList(listOfNumbers);

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

    public void findStandardDeviation() {}

    /**
     * organizes the elements in an array list from least to greatest by sorting them around a pivot
     * @param unsortedArrayList
     * @return sorted array list
     */
    private ArrayList<Integer> sortArrayList(ArrayList<Integer> unsortedArrayList)
    {

        ///initial variables
        int size = unsortedArrayList.size();
        int pivot = unsortedArrayList.get(size - 1);
        int position = 0;

        //Places numbers smaller than the pivot to the left of it
        for(int i = 0; i < size - 1; i++)
        {
            if(unsortedArrayList.get(i) < pivot && i != position)
            {
                int tempI = unsortedArrayList.get(i);
                int tempPos = unsortedArrayList.get(position);
                unsortedArrayList.remove(position);
                unsortedArrayList.add(position, tempI);
                unsortedArrayList.remove(i);
                unsortedArrayList.add(i, tempPos);
                position++;
            }
            else if(unsortedArrayList.get(i) < pivot && i == position)
            {
                position++;
            }
        }

        /**
        * swaps the pivot with the element in "position" so that all elements to the left of the pivot are smaller and those to
        * the right are equal to or greater than the pivot
        */
        int temp = unsortedArrayList.get(position);
        unsortedArrayList.remove(position);
        unsortedArrayList.add(position, pivot);
        unsortedArrayList.remove(size - 1);
        unsortedArrayList.add(size - 1, temp);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();


        //Fills left and right arraylists with respective halves of the unsorted one
        for(int i = 0; i < position; i++)
        {
            left.add(unsortedArrayList.get(i));
        }
        for(int i = position + 1; i < size; i++)
        {
            right.add(unsortedArrayList.get(i));
        }

        //if either left or right array list is bigger than one recurse the method, will eventually return a sorted Array list
        if(left.size() > 1)
        {
            left = sortArrayList(left);
        }
        if(right.size() > 1)
        {
            right = sortArrayList(right);
        }


        //new sorted array
        ArrayList<Integer> sortedArray = new ArrayList<>();

        //add in sorted elements from least to greatest that are smaller than the pivot
        for(int i = 0; i < left.size(); i++)
        {
            sortedArray.add(left.get(i));
        }

        //adds the pivot
        sortedArray.add(pivot);

        //Adds in the remaining elements which are greater than or equal to the pivot
        for(int i = 0; i < right.size(); i++)
        {
            sortedArray.add(right.get(i));
        }
        //returns the sorted array
        return sortedArray;
    }
}
