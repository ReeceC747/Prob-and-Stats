package MonteCarlo;

import java.util.Random;

public class ThreeDoors 
{
    public void playDealNoDeal(int games)
    {
        Random generator = new Random();

        double wins = 0;
        double WinPercent = 0;

        for(int i = 0; i < games; i++)
        {
            int correctDoor = generator.nextInt(3);
            int chooseDoor = generator.nextInt(3);
            if(chooseDoor == correctDoor)
            {
                wins++;
            }
        }

        WinPercent = wins / games;
        System.out.println("Win percent after not changing Doors is " + WinPercent);
    }

    public void playDealNoDealChange(int games)
    {
        Random generator = new Random();

        double wins = 0;
        double winPercent = 0;

        for(int i = 0; i < games; i++)
        {
            int chooseDoor = generator.nextInt(3);
            int correctDoor = generator.nextInt(3);

            int killDoor = generator.nextInt(3);
            while(killDoor == correctDoor || killDoor == chooseDoor)
            {
                killDoor = generator.nextInt(3);
            }
            int newDoor = generator.nextInt(3);
            while(newDoor == chooseDoor || newDoor == killDoor)
            {
                newDoor = generator.nextInt(3);
            }
            if(newDoor == correctDoor)
            {
                wins++;
            }
        }
        winPercent = wins /  games;
        System.out.println("Win percent after changing doors is " + winPercent);
    }
}
