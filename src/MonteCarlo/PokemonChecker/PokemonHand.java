package MonteCarlo.PokemonChecker;

import java.util.ArrayList;
import java.util.Random;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.Energy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import PokemonCardGame.CardTypes.TrainerCards.Trainer;
import java.util.Collections;


public class PokemonHand 
{
    //Problem, find out how to properly shuffle the pokemon cards in the deck without them being most likely at the start
    //Solution, fill the deck with 60 energy and trainer cards then replace x of them randomly with pokemon cards, maybe in player
    public static void main(String[] args)
    {
        //Initalized objects and variables
        PokemonHand experiment = new PokemonHand();
        int runs = 1000;
        Random rng = new Random();
        ArrayList<Double> successRates = new ArrayList<>();

        //For each pokemon count from 0 to 60
        for(int pokemonCount = 0; pokemonCount < 60; pokemonCount++)
        {
            //number of successful runs
            int success = 0;
            //Run the experiment x times
            for(int j = 0; j < runs; j++)
            {
                //new player and deck for each run
                Player player = new Player();
                ArrayList<Card> deck = new ArrayList<>();
                // Fill deck with 60 cards of either energy or trainer
                for(int k = 0; k < 60; k++)
                {
                    int cardType = rng.nextInt(2);
                    if(cardType == 0)
                    {
                        deck.add(new Energy());
                    }
                    else
                    {
                        deck.add(new Trainer());
                    }
                }
                // Replace pokemonCount cards with pokemon cards, then shuffle the deck
                for(int k = 0; k < pokemonCount; k++)
                {
                    deck.add(new Pokemon());
                    deck.remove(0);
                }
                Collections.shuffle(deck);

                //Set the active deck for the player and draw 7 cards
                player.setActiveDeck(deck);
                for(int i = 0; i < 7; i++)
                {
                    player.drawCard();
                }
                if(experiment.hasPokemon(player))
                {
                    success++;
                }
            }
            //Add the success rate to the list
            successRates.add((double)success / runs);  
        }
        //Print the success rates
        System.out.println("Chances of getting a pokemon in your starting hand from 0 to 60 pokemon: " + successRates);
    }

    /**
     * Checks if the player has a pokemon in their hand
     * @param player
     * @return true if the player has a pokemon in their hand, false otherwise
     */
    private boolean hasPokemon(Player player)
    {
        //Get the players hand
        ArrayList<Card> hand = player.getHand();
        //Checks each card in the hand to see if it is a pokemon
        for(Card card : hand)
        {
            //If the card is a pokemon, return true
            if(card instanceof Pokemon)
            {
                return true;
            }
        }
        //If no pokemon is found, return false
        return false;
    }    
}



