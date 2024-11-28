package SortingAlgorithms;
import java.util.ArrayList;

public class QuickSort 
{
        /**
     * organizes the elements in an array list from least to greatest by sorting them around a pivot
     * @param unsortedArrayList
     * @return sorted array list
     */    
    public ArrayList<Double> quickSort(ArrayList<Double> unsortedArrayList)
    {

    {

        ///initial variables
        int size = unsortedArrayList.size();
        double pivot = unsortedArrayList.get(size - 1);
        int position = 0;

        //Places numbers smaller than the pivot to the left of it
        for(int i = 0; i < size - 1; i++)
        {
            if(unsortedArrayList.get(i) < pivot && i != position)
            {
                double tempI = unsortedArrayList.get(i);
                double tempPos = unsortedArrayList.get(position);
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
        double temp = unsortedArrayList.get(position);
        unsortedArrayList.remove(position);
        unsortedArrayList.add(position, pivot);
        unsortedArrayList.remove(size - 1);
        unsortedArrayList.add(size - 1, temp);

        ArrayList<Double> left = new ArrayList<>();
        ArrayList<Double> right = new ArrayList<>();


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
            left = quickSort(left);
        }
        if(right.size() > 1)
        {
            right = quickSort(right);
        }


        //new sorted array
        ArrayList<Double> sortedArray = new ArrayList<>();

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
}
