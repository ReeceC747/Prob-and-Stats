package PokemonCardGame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import PokemonCardGame.CardTypes.*;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class CardGame 
{
    private Scanner input;

    public CardGame()
    {
        input = new Scanner(System.in);
    }

    public void start()
    { 
        Random rand = new Random();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player first;
        Player second;
        int player1Mulligans;
        int player2Mulligans;
        System.out.println("Game Started");
        deckBuilder(player1);
        deckBuilder(player2);
        
        int coinFlip = rand.nextInt(2);
        if(coinFlip == 0)
        {
            System.out.println(player1.getName() + " goes first");
            player1Mulligans = setupPokemon(player1);
            first = player1;
            second = player2;

            System.out.println("Now for " + player2.getName());
            player2Mulligans = setupPokemon(player2);

            System.out.println(player1.getName() + ", " + player2.getName() + " has mulliganed " + player2Mulligans + " times, you can draw up to " 
                + player2Mulligans + " extra cards, how many would you like to draw?");
            
            int extraCards = input.nextInt();
            input.nextLine();

            for(int i = 0; i < extraCards; i++)
            {
                player1.drawCard();
            }

            System.out.println(player1.getName() + " has drawn " + extraCards + " cards");

            System.out.println(player2.getName() + ", " + player1.getName() + " has mulliganed " + player1Mulligans + " times, you can draw up to " 
                + player1Mulligans + " extra cards, how many would you like to draw?");

            extraCards = input.nextInt();
            input.nextLine();

            for(int i = 0; i < extraCards; i++)
            {
                player2.drawCard();
            }

            System.out.println(player2.getName() + " has drawn " + extraCards + " cards");
        }
        else
        {
            System.out.println(player2.getName() + " goes first");
            player2Mulligans = setupPokemon(player2);
            first = player2;
            second = player1;

            System.out.println("Now for " + player1.getName());
            player1Mulligans = setupPokemon(player1);

            System.out.println(player2.getName() + ", " + player1.getName() + " has mulliganed " + player1Mulligans + " times, you can draw up to " 
                + player1Mulligans + " extra cards, how many would you like to draw?");

            int extraCards = input.nextInt();
            input.nextLine();

            for(int i = 0; i < extraCards; i++)
            {
                player2.drawCard();
            }

            System.out.println(player1.getName() + ", " + player2.getName() + " has mulliganed " + player2Mulligans + " times, you can draw up to " 
                + player2Mulligans + " extra cards, how many would you like to draw?");

            extraCards = input.nextInt();
            input.nextLine();

            for(int i = 0; i < extraCards; i++)
            {
                player1.drawCard();
            }

            System.out.println(player1.getName() + " has drawn " + extraCards + " cards");
        }

        gameLoop(first, second);
    }



    /**
     * 
     * @param player
     * @param opponent
     * @return false if the 
     */
    public void gameLoop(Player player1, Player player2)
    {
        while(player1.getWinner() == false && player2.getWinner() == false)
        {
            if(player1.drawCard() == false)
            {
                System.out.println(player1.getName() + " cant draw any more cards");
                player2.setWinner(true);
                break;
            }
            player1.turn(player2);

            if(player1.getWinner() == true || player2.getWinner() == true)
            {
                break;
            }
            if(player2.drawCard() == false)
            {
                System.out.println(player2.getName() + " cant draw any more cards");
                player1.setWinner(true);
                break;
            }
            player2.turn(player1);
        }

        if(player1.getWinner() == true)
        {
            System.out.println(player1.getName() + " wins");
        }
        else
        {
            System.out.println(player2.getName() + " wins");
        }
    }


    /**
     * The setup phase of the game, where we handle the inital card draw, mulligan, and active/bench pokemon selection, return the number of times 
     * the player mulliganed, the benching and active pokemon selection is done by using two arrays, one with the pokemon class and the other with 
     * its index in the players hand
     * @param player1
     * @param player2
     */
    public int setupPokemon(Player player)
    {
        //The index of the basic pokemon cards
        ArrayList<Integer> pokemonIndex = new ArrayList<>();
        //The basic pokemon cards in the players hand
        ArrayList<Pokemon> basicPokemon = new ArrayList<>();
        //Scanner for user input
        input = new Scanner(System.in);

        //number of mulligans
        int mulligans = 0;

        //Draw 7 cards
        for(int i = 0; i < 7; i++)
        {
            player.drawCard();
        }
        //Mulligan if the player has no basic pokemon
        while(!player.hasPokemon())
        {
            player.mulligan();
            mulligans++;
        }
        //Draw 6 prize cards
        player.drawPrizes();

        //Check for basic pokemon in hand and record their index and the card
        for(Card card : player.getHand())
        {
            if(card instanceof Pokemon && ((Pokemon)card).getStage() == 0)
            {
                pokemonIndex.add(player.getHand().indexOf(card));
                basicPokemon.add((Pokemon)card);
            }
        }

        //Print out the basic pokemon cards in the players hand
        System.out.println("Please select a basic pokemon to put on the active field");         
        for(int i = 0; i < basicPokemon.size(); i++)
        {
            System.out.println((i + 1) + ". " + basicPokemon.get(i).getName());
        }
        
        int choice = input.nextInt();
        input.nextLine();

        //Sets the active pokemon and remove it from the hand
        player.setActivePokemon((Pokemon) player.getHand().get(pokemonIndex.get(choice - 1)));
        player.getHand().remove(pokemonIndex.get(choice - 1));
        pokemonIndex.remove(choice - 1);
        basicPokemon.remove(choice - 1);

        boolean done = false;

        if(basicPokemon.size() == 0)
        {
            System.out.println("No more basic pokemon in hand");
            done = true;
        }
        else
        {
            //Print out the basic pokemon cards in the players hand
            System.out.println("Please select the basic pokemon you want to put on the bench");
            while(!done || player.getBench().size() == 5)
            {
                System.out.println("0. Done");
                for(int i = 0; i < basicPokemon.size(); i++)
                {
                    System.out.println((i + 1) + ". " + basicPokemon.get(i).getName());
                }

                

                choice = input.nextInt();
                input.nextLine();

                //Places the basic pokemon on the bench and removes it from the hand
                player.getBench().add((Pokemon) player.getHand().get(pokemonIndex.get(choice - 1)));
                player.getHand().remove(pokemonIndex.get(choice - 1));
                pokemonIndex.remove(choice - 1);
                basicPokemon.remove(choice - 1);

                if(choice == 0 || basicPokemon.size() == 0)
                {
                    done = true;
                }
            }
        }

        return mulligans;
    }

    private void deckBuilder(Player player)
    {
        boolean done = false;
        int choice = 0;

        System.out.println(player.getName() + ", Would You like to use a premade deck or create your own?");
        System.out.println("1. Premade");
        System.out.println("2. Create your own");

        while(!done)
        {

            choice = input.nextInt();
            input.nextLine();
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
