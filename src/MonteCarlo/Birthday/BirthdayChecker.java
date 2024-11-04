package MonteCarlo.Birthday;

import java.util.ArrayList;
import java.util.Random;

public class BirthdayChecker 
{
    public double probabilityOfSharedBirthday(int numberOfPeople, int runs)
    {
        //intialize variables and objects
        Random generator = new Random();
        ArrayList<Person> people;
        int sharedBirthdays = 0;
        double percent;
        boolean done = false;
        boolean found;
        int interval;

        //run the simulation for the number of runs
        for(int r = 0; r < runs; r++)
        {
            //create a new arraylist of people
            people = new ArrayList<>();
            //used to check if a person has the same birthday as another
            interval = 0;
            //used to check if a shared birthday was found
            found = false;
            //used to check if the simulation is done
            done = false;
            //create a new person with a random birthday for each person in the group
            for(int i = 0; i < numberOfPeople; i++)
            {
                int birthday = generator.nextInt(365);
                Person guy = new Person(birthday);
                people.add(guy);
            }

            //check if any of the people have the same birthday
            while(!done)
            {
                //Compares the person at interval with everyone infront of them for a shared birthday
                for(int i = interval + 1; i < people.size(); i++)
                {
                    //if a shared birthday is found, set found to true and done to true
                    if(people.get(interval).getBirthday() == people.get(i).getBirthday())
                    {
                        found = true;
                        done = true;
                    }
                }
                //increment interval to check the next person
                interval++;

                //if interval is equal to the number of people, set done to true
                if(interval == people.size() - 1)
                {
                    done = true;
                }
            }

            //if a shared birthday was found, increment sharedBirthdays
            if(found)
            {
                sharedBirthdays++;
            }
            done = false;
        }

        //calculate the percentage of shared birthdays
        percent = sharedBirthdays / (double) runs;

        //return the percentage
        return percent;
    }
}
