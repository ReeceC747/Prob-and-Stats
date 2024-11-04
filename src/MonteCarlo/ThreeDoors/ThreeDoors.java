package MonteCarlo.ThreeDoors;

import java.util.Random;

public class ThreeDoors 
{
    /**
     * Plays the game Deal or No Deal without changing doors 
     */
    public void playDealNoDeal(int games)
    {
        Random generator = new Random();

        // Variables to keep track of wins and win percentage
        double wins = 0;
        double WinPercent = 0;

        // Play the game for the number of games
        for(int i = 0; i < games; i++)
        {
            // Randomly choose the correct door and the door the player chooses
            int correctDoor = generator.nextInt(3);
            int chooseDoor = generator.nextInt(3);
            // If the player chooses the correct door, they win
            if(chooseDoor == correctDoor)
            {
                wins++;
            }
        }

        // Calculate the win percentage
        WinPercent = wins / games;
        // Print the win percentage
        System.out.println("Win percent after not changing Doors is " + WinPercent);
    }

    /**
     * Plays the game Deal or No Deal with changing doors
     */
    public void playDealNoDealChange(int games)
    {
        // Variables to keep track of wins and win percentage, and a random generator
        Random generator = new Random();
        double wins = 0;
        double winPercent = 0;

        // Play the game for the number of games
        for(int i = 0; i < games; i++)
        {
            // Randomly choose the correct door and the door the player chooses and a door the host shows
            int chooseDoor = generator.nextInt(3);
            int correctDoor = generator.nextInt(3);
            int killDoor = generator.nextInt(3);
            //If the door the host chooses is either the correct door or the door the player chose, choose a new door
            while(killDoor == correctDoor || killDoor == chooseDoor)
            {
                killDoor = generator.nextInt(3);
            }
            // Choose a new door for the player
            int newDoor = generator.nextInt(3);
            // If the new door is either the original door the player chose or the door the host showed, choose a new door
            while(newDoor == chooseDoor || newDoor == killDoor)
            {
                newDoor = generator.nextInt(3);
            }
            // If the new door is the correct door, the player wins
            if(newDoor == correctDoor)
            {
                wins++;
            }
        }
        // Calculate the win percentage
        winPercent = wins /  games;
        // Print the win percentage
        System.out.println("Win percent after changing doors is " + winPercent);
    }
}
