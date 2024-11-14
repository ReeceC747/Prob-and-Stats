package MonteCarlo.Birthday;

public class Person 
{
    // The birthday of the person
    int birthday;

    // Constructor for the Person class
    Person(int birthdayP)
    {
       birthday = birthdayP;
    }

    /**
     * Get the birthday of the person
     * @return
     */
    public int getBirthday()
    {
        return birthday;
    }
}

