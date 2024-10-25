package SetOperations;
import java.util.ArrayList;
import java.util.Random;

public class TestSetOperations
{
    public static void main(String[]args)
    {
        ArrayList<Integer> set = new ArrayList<>();
        ArrayList<Integer> subsetOne = new ArrayList<>();
        ArrayList<Integer> subsetTwo = new ArrayList<>();
        Random generator = new Random();
        SetOperations setOp = new SetOperations();

        //Fills the set and subsets with random numbers
        for(int i = 0; i < 10; i++)
        {
            set.add(i);
        }
        for(int i = 0; i < 10; i++)
        {
            subsetOne.add(generator.nextInt(100));
            subsetTwo.add(generator.nextInt(100));
        }

        //Tests union
        System.out.println("Union of subsetOne and subsetTwo: " + setOp.union(subsetOne, subsetTwo));

        //Tests intersect
        System.out.println("Intersection of subsetOne and subsetTwo: " + setOp.intersect(subsetOne, subsetTwo));

        //Tests compliment
        System.out.println("Compliment of subsetOne and set: " + setOp.compliment(set, subsetOne));
        System.out.println("Compliment of subsetTwo and set: " + setOp.compliment(set, subsetTwo));


    }
}
