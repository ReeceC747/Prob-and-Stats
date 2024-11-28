package SetOperations;
import java.util.ArrayList;
import java.util.Random;

public class TestSetOperations
{
    public static void main(String[]args)
    {
        ArrayList<Double> set = new ArrayList<>();
        ArrayList<Double> subsetOne = new ArrayList<>();
        ArrayList<Double> subsetTwo = new ArrayList<>();
        Random generator = new Random();
        SetOperations setOp = new SetOperations();

        //Fills the set and subsets with random numbers
        for(double i = 0; i < 10; i++)
        {
            set.add(i);
        }
        for(int i = 0; i < 10; i++)
        {
            subsetOne.add(generator.nextDouble(100));
            subsetTwo.add(generator.nextDouble(100));
        }

        //Tests union
        System.out.println("Union of subsetOne and subsetTwo: " + setOp.union(subsetOne, subsetTwo));

        //Tests intersect
        System.out.println("Intersection of subsetOne and subsetTwo: " + setOp.intersection(subsetOne, subsetTwo));

        //Tests compliment
        System.out.println("Compliment of subsetOne and set: " + setOp.complement(set, subsetOne));
        System.out.println("Compliment of subsetTwo and set: " + setOp.complement(set, subsetTwo));


    }
}
