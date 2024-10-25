package SetOperations;
import java.util.ArrayList;

import SortingAlgorithms.QuickSort;

public class SetOperations 
{

    /**
     * Checks if the two sets are valid and returns the union of the two sets, if the parameters are not valid sets the method returns Null
     * @param setOne
     * @param setTwo
     * @return The union of two sets
     */
    public ArrayList<Integer> union(ArrayList<Integer> setOne, ArrayList<Integer> setTwo)
    {
        //Initializes the quicksort object
        QuickSort qS = new QuickSort();

        //Checks if the two sets are valid, if theyre not sets the method returns Null
        if(isSet(setOne) == true && isSet(setTwo) == true)
        {
            //Initializes the unioned set
            ArrayList<Integer> unionedSet = new ArrayList<>();

            //Adds all the elements of the two sets to the unioned set
            for(int i = 0; i < setOne.size(); i++)
            {
                unionedSet.add(setOne.get(i));
            }
            for(int i = 0; i < setTwo.size(); i++)
            {
                unionedSet.add(setTwo.get(i));
            }

            //Sorts the unioned set
            unionedSet = qS.quickSort(unionedSet);

            //Removes any duplicates in the unioned set
            for(int i = 0; i < unionedSet.size() - 1; i++)
            {
                //If the current element is equal to the next element, the current element is removed
                if(unionedSet.get(i) == unionedSet.get(i + 1))
                {
                    unionedSet.remove(i);
                }
            }
            return unionedSet;
        }
        else
        {
            return null;
        }
        






    }

    /**
     * Checks if the two sets are valid and returns the intersection of the two sets, if the parameters are not valid sets the method returns Null
     * @param setOne
     * @param setTwo
     * @return The intersection of two sets
     */
    public ArrayList<Integer> intersect(ArrayList<Integer> setOne, ArrayList<Integer> setTwo)
    {
        //Initializes the quicksort object
        QuickSort qS = new QuickSort();

        //Checks if the two sets are valid, if theyre not sets the method returns Null
        if(isSet(setOne) == true && isSet(setTwo) == true)
        {
            //Initializes the combined set
            ArrayList<Integer> combinedSet = new ArrayList<>();

            //Adds all the elements of the two sets to the combined set
            for(int i = 0; i < setOne.size(); i++)
            {
                combinedSet.add(setOne.get(i));
            }
            for(int i = 0; i < setTwo.size(); i++)
            {
                combinedSet.add(setTwo.get(i));
            }

            //Sorts the combined set
            combinedSet = qS.quickSort(combinedSet);

            //Initializes the intersected set
            ArrayList<Integer> intersectedSet = new ArrayList<>();

            //Goes through the whole combinedSet and if it finds a duplicate it adds it to the intersected set and removes the duplicates infront of it
            for(int i = 0; i < combinedSet.size() - 1; i++)
            {
                //If the current element is equal to the next element
                //the current element is added to the intersected set and removes the duplicates infront of it
                if(combinedSet.get(i) == combinedSet.get(i + 1))
                {
                    intersectedSet.add(combinedSet.get(i));
                    while(combinedSet.get(i) == combinedSet.get(i + 1))
                    {
                        combinedSet.remove(i);
                    }                    
                }

            }
            return intersectedSet;
        }
        else
        {
            return null;
        }
    }

    /**
     * Checks if the two sets are valid and returns the compliment of the two sets, if the parameters are not valid sets the method returns Null
     * @param sortedSet
     * @param subSet
     * @return The compliment of two sets
     */
    public ArrayList<Integer> compliment (ArrayList<Integer> sortedSet, ArrayList<Integer> subSet)
    {

        //Checks if the two sets are valid, if theyre not sets the method returns Null
        if(isSet(sortedSet) == true && isSet(subSet) == true)
        {
            //Initializes the quicksort object
            QuickSort qS = new QuickSort();

            //Sorts the two sets
            subSet = qS.quickSort(subSet);
            sortedSet = qS.quickSort(sortedSet);

            //Initializes the compliment set
            ArrayList<Integer> compliment = new ArrayList<>();

            //Initializes the interval
            int interval = 0;

            //Goes through the whole sorted set and if it isnt in the subSet it adds it to the compliment set
            for(int i = 0; i < sortedSet.size(); i++)
            {
                if(sortedSet.get(i) != subSet.get(interval))
                {
                    compliment.add(sortedSet.get(i));
                }
                else
                {
                    interval++;
                }
            }        
            return compliment;    
        }
        else
        {
            return null;
        }

    }

    /**
     * Checks if the set is valid
     * @param set
     * @return True if the set is valid, false if it is not
     */
    private boolean isSet(ArrayList<Integer> set)
    {
        //initailizes the foundDupe, done, and interval variables
        boolean foundDupe = false;
        boolean done = false;
        int interval = 0;

        //Goes through the whole set and if it finds a duplicate it sets foundDupe to true
        while(!done)
        {
            //Goes through the whole set and repeats at interval until the whole list has been checked
            for(int i = interval; i < set.size() - 1; i++)
            {
                //If the interval element is equal to the currently looked at element there is a duplicate
                if(set.get(interval) == set.get(i + 1))
                {
                    foundDupe = true;
                    done = true;
                }
            }
            //Increases the interval 
            interval++;

            //If the interval is equal to the size of the set - 1 the whole set has been checked
            if(interval == set.size() - 1)
            {
                done = true;
            }
        }

        //If a duplicate was found the set is not valid
        if(foundDupe)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
