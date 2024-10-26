package MonteCarlo.Birthday;

import java.util.ArrayList;
import java.util.Random;

public class BirthdayChecker 
{
    public double probabilityOfSharedBirthday(int numberOfPeople, int runs)
    {
        Random generator = new Random();
        ArrayList<Person> people;

        int sharedBirthdays = 0;
        double percent;
        boolean done = false;
        boolean found;
        int interval;

        //while not done go through 
        for(int r = 0; r < runs; r++)
        {
            people = new ArrayList<>();
            interval = 0;
            found = false;
            done = false;
            for(int i = 0; i < numberOfPeople; i++)
            {
                int birthday = generator.nextInt(365);
                Person guy = new Person(birthday);
                people.add(guy);
            }

            while(!done)
            {
                for(int i = interval + 1; i < people.size(); i++)
                {
                    if(people.get(interval).getBirthday() == people.get(i).getBirthday())
                    {
                        found = true;
                        done = true;
                    }
                }
                interval++;

                if(interval == people.size() - 1)
                {
                    done = true;
                }
            }

            if(found)
            {
                sharedBirthdays++;
            }
            done = false;
        }

        percent = sharedBirthdays / (double) runs;

        return percent;
    }
}
