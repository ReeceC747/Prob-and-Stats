package PokemonCardGame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import PokemonCardGame.CardTypes.*;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

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

    /**
     * The setup phase of the game, where the player1 goes first then player2
     * @param player1
     * @param player2
     */
    public void setup(Player player)
    {
        int playablePokemon = 0;
        ArrayList<Pokemon> pokemonCards = new ArrayList<>();
        Scanner input = new Scanner(System.in);


        for(int i = 0; i < 7; i++)
        {
            player.drawCard();
        }
        while(!player.hasPokemon())
        {
            player.mulligan();
        }
        player.drawPrizes();

        for(Card card : player.getHand())
        {
            if(card instanceof Pokemon && ((Pokemon)card).getStage() == 0)
            {
                playablePokemon++;
                pokemonCards.add((Pokemon)card);
            }
        }

        System.out.println("Please select a basic pokemon to put on the active field");         
        for(int i = 0; i < playablePokemon; i++)
        {
            if(player.getHand().get(i) instanceof Pokemon && ((Pokemon)player.getHand().get(i)).getStage() == 0)
            {
                System.out.println(i + ". " + ((Pokemon) player.getHand().get(i)).getName());
            }
        }
        
        int choice = input.nextInt();


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
        input.close();
    }
}
