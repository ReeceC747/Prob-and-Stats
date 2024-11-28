package SetOperations;
import java.util.ArrayList;
import SortingAlgorithms.QuickSort;

public class SetOperations {

    /**
     * Checks if the two sets are valid and returns the union of the two sets, if the parameters are not valid sets the method returns Null
     * @param setOne
     * @param setTwo
     * @return The union of two sets
     */
    public ArrayList<Double> union(ArrayList<Double> setOne, ArrayList<Double> setTwo) {
        // Check if either setOne or setTwo is not a set
        if (!isSet(setOne) || !isSet(setTwo)) 
        {
            return null;
        }

        // Initializes the quicksort object
        QuickSort qS = new QuickSort();

        // Initializes the unioned set
        ArrayList<Double> unionedSet = new ArrayList<>();

        // Adds all the elements of the two sets to the unioned set
        for (int i = 0; i < setOne.size(); i++) {
            unionedSet.add(setOne.get(i));
        }
        for (int i = 0; i < setTwo.size(); i++) {
            unionedSet.add(setTwo.get(i));
        }

        // Sorts the unioned set
        unionedSet = qS.quickSort(unionedSet);

        // Removes any duplicates in the unioned set
        for (int i = 0; i < unionedSet.size() - 1; i++) {
            // If the current element is equal to the next element, the current element is removed
            if (unionedSet.get(i).equals(unionedSet.get(i + 1))) {
                unionedSet.remove(i);
                i--; // Adjust the index after removal
            }
        }
        return unionedSet;
    }

    /**
     * Checks if the two sets are valid and returns the intersection of the two sets, if the parameters are not valid sets the method returns Null
     * @param setOne
     * @param setTwo
     * @return The intersection of two sets
     */
    public ArrayList<Double> intersection(ArrayList<Double> setOne, ArrayList<Double> setTwo) {
        // Check if either setOne or setTwo is not a set
        if (!isSet(setOne) || !isSet(setTwo)) 
        {
            return null;
        }

        ArrayList<Double> intersectionSet = new ArrayList<>();

        for (int i = 0; i < setOne.size(); i++) {
            if (setTwo.contains(setOne.get(i))) {
                intersectionSet.add(setOne.get(i));
            }
        }

        return intersectionSet;
    }

    /**
     * Checks if the two sets are valid and returns the difference of the two sets, if the parameters are not valid sets the method returns Null
     * @param setOne
     * @param setTwo
     * @return The difference of two sets
     */
    public ArrayList<Double> difference(ArrayList<Double> setOne, ArrayList<Double> setTwo) {
        // Check if either setOne or setTwo is not a set
        if (!isSet(setOne) || !isSet(setTwo)) 
        {
            return null;
        }

        ArrayList<Double> differenceSet = new ArrayList<>(setOne);

        for (int i = 0; i < setTwo.size(); i++) {
            differenceSet.remove(setTwo.get(i));
        }

        return differenceSet;
    }

    /**
     * Checks if the set is valid and returns the complement of the set, if the parameter is not a valid set the method returns Null
     * @param universalSet
     * @param set
     * @return The complement of the set
     */
    public ArrayList<Double> complement(ArrayList<Double> universalSet, ArrayList<Double> set) {
        // Check if either universalSet or set is not a set
        if (!isSet(universalSet) || !isSet(set)) 
        {
            return null;
        }

        ArrayList<Double> complementSet = new ArrayList<>(universalSet);

        for (int i = 0; i < set.size(); i++) {
            complementSet.remove(set.get(i));
        }

        return complementSet;
    }

    // Assuming isSet method is defined somewhere in this class
    public boolean isSet(ArrayList<Double> list) {
        // Implementation of isSet method
        return true; // Placeholder
    }
}
