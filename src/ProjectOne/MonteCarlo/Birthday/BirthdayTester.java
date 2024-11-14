package MonteCarlo.Birthday;

public class BirthdayTester 
{
    public static void main(String[] args)
    {
        // Test the BirthdayChecker class
        BirthdayChecker bC = new BirthdayChecker();
        int numberOfPeople = 23;
        int runs = 100;
        System.out.println("The probability that 2 people share the same birthday in a group of " + numberOfPeople + " is "
            + bC.probabilityOfSharedBirthday(numberOfPeople, runs) + " after " + runs + " runs.");
    }
}
