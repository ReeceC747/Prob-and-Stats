package PokemonCardGame;

import java.util.Scanner;
import java.util.ArrayList;
import PokemonCardGame.CardTypes.*;

public class CardGame 
{
    private ArrayList<Card> premadeDeck = new ArrayList<Card>();

    public void start()
    {
        Scanner input = new Scanner(System.in);
        Player player1 = new Player();
        Player player2 = new Player();

        System.out.println("Game started");

        System.out.println("Player1 Would You like to use a premade deck or create your own?");
        System.out.println("1. Premade");
        System.out.println("2. Create your own");

        int choice = input.nextInt();

        if(choice == 1)
        {
            System.out.println("You have chosen a premade deck");
            //deck ratio, 12 energy, 12 pokemon, 36 trainers
        }
        else if(choice == 2)
        {
            System.out.println("You have chosen to create your own deck");
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }
}
