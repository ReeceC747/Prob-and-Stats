import java.util.ArrayList;

import javax.lang.model.type.NullType;

public class SetOperations 
{
    //method to intersect two arraylists
    //method to return the compliment


    public ArrayList<Integer> union(ArrayList<Integer> setOne, ArrayList<Integer> setTwo)
    {
        QuickSort qS = new QuickSort();
        if(isSet(setOne) == true && isSet(setTwo) == true)
        {
            ArrayList<Integer> unionedSet = new ArrayList<>();
            for(int i = 0; i < setOne.size(); i++)
            {
                unionedSet.add(setOne.get(i));
            }
            for(int i = 0; i < setTwo.size(); i++)
            {
                unionedSet.add(setTwo.get(i));
            }

            unionedSet = qS.quickSort(unionedSet);
            for(int i = 0; i < unionedSet.size() - 1; i++)
            {
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

    public ArrayList<Integer> intersect(ArrayList<Integer> setOne, ArrayList<Integer> setTwo)
    {
        QuickSort qS = new QuickSort();
        if(isSet(setOne) == true && isSet(setTwo) == true)
        {
            ArrayList<Integer> combinedSet = new ArrayList<>();
            for(int i = 0; i < setOne.size(); i++)
            {
                combinedSet.add(setOne.get(i));
            }
            for(int i = 0; i < setTwo.size(); i++)
            {
                combinedSet.add(setTwo.get(i));
            }

            combinedSet = qS.quickSort(combinedSet);
            ArrayList<Integer> intersectedSet = new ArrayList<>();

            for(int i = 0; i < combinedSet.size() - 1; i++)
            {
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

    private boolean isSet(ArrayList<Integer> set)
    {
        boolean foundDupe = false;
        boolean done = false;
        int interval = 0;
        while(!done)
        {
            for(int i = interval; i < set.size() - 1; i++)
            {
                if(set.get(interval) == set.get(i + 1))
                {
                    foundDupe = true;
                    done = true;
                }
            }
            interval++;

            if(interval == set.size() - 1)
            {
                done = true;
            }
        }

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
