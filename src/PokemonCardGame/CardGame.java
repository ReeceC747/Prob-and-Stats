package PokemonCardGame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import PokemonCardGame.CardTypes.*;

public class CardGame 
{
    private ArrayList<Card> premadeDeck = new ArrayList<Card>();

    public void start()
    {
        Random rand = new Random();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        deckBuilder(player1);
        deckBuilder(player2);
        
        int coinFlip = rand.nextInt(2);
        if(coinFlip == 0)
        {
            System.out.println(player1.getName() + " goes first");
            player1.setTurn(true);
        }
        else
        {
            System.out.println(player2.getName() + " goes first");
            player2.setTurn(true);
        }

        
    }

    private void deckBuilder(Player player)
    {
        Scanner input = new Scanner(System.in);
        boolean done = false;

        System.out.println("Game started");

        System.out.println(player.getName() + ", Would You like to use a premade deck or create your own?");
        System.out.println("1. Premade");
        System.out.println("2. Create your own");

        while(!done)
        {
            int choice = input.nextInt();

            if(choice == 1)
            {
                //deck ratio, 12 energy, 12 pokemon, 36 trainers            
                System.out.println("You have chosen a premade deck");
                player.chooseDeck(choice);
                done = true;
            }
            else if(choice == 2)
            {
                System.out.println("You have chosen to create your own deck");
                player.chooseDeck(choice);
                done = true;
            }
            else
            {
                System.out.println("Invalid choice");
            }
        }
    }
}
