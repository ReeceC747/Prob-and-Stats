package MonteCarlo.PokemonChecker;

import java.util.ArrayList;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.Energy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import PokemonCardGame.CardTypes.TrainerCards.Trainer;
import java.util.Collections;


public class Prizes 
{
    public static void main(String[] args)
    {
        
        Prizes experiment = new Prizes();
        ArrayList<Double> successRates = experiment.brickedDeck();
        System.out.println("Chance of deck bricking with 0 to 4 trainer cards in the deck " + successRates);
        //find out how to print the prob of when you have 0-4 trainer cards, the chance of getting 0-4 trainer cards in the prizes

        //Chance of getting 0 trainer cards in the prizes when you have 0 trainer cards in the deck
        System.out.println("Chance of getting 0 trainer cards in the prizes when you have 0 trainer cards in the deck: " + experiment.trainerInPrizes(0, 0));
        
        //Chance of getting 0, 1 trainer cards in the prizes when you have 1 trainer cards in the deck
        for(int i = 0; i < 2; i++)
        {
            System.out.println("Chance of getting " + 1 + " trainer cards in the prizes when you have 1 trainer cards in the deck: " + experiment.trainerInPrizes(1, i));
        }

        //Chance of getting 0, 1, 2 trainer cards in the prizes when you have 2 trainer cards in the deck
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Chance of getting " + i + " trainer cards in the prizes when you have 2 trainer cards in the deck: " + experiment.trainerInPrizes(2, i));
        }

        //Chance of getting 0, 1, 2, 3 trainer cards in the prizes when you have 3 trainer cards in the deck
        for(int i = 0; i < 4; i++)
        {
            System.out.println("Chance of getting " + i + " trainer cards in the prizes when you have 3 trainer cards in the deck: " + experiment.trainerInPrizes(3, i));
        }

        //Chance of getting 0, 1, 2, 3, 4 trainer cards in the prizes when you have 4 trainer cards in the deck
        for(int i = 0; i < 5; i++)
        {
            System.out.println("Chance of getting " + i + " trainer cards in the prizes when you have 4 trainer cards in the deck: " + experiment.trainerInPrizes(4, i));
        }

    }

    /**
     * Method to determine the success rate of getting x number of trainer cards in the prizes when you have y number of trainer cards in the deck
     */
    private double trainerInPrizes(int numberOfTrainerCards, int numerOfTrainerInPrize)
    {
        Prizes experiment = new Prizes();
        int runs = 1000;
        double success = 0;
        for(int j = 0; j < runs; j++)
        {
            Player player = new Player();
            ArrayList<Card> deck = new ArrayList<>();
            // Fill deck with (48 - numberOfTrainerCards) energy cards
            for(int k = 0; k < 48 - numberOfTrainerCards; k++)
            {
                deck.add(new Energy());
            }
            // Add 12 pokemon cards
            for(int k = 0; k < 12; k++)
            {
                deck.add(new Pokemon());
            }
            // Add numberOfTrainerCards trainer cards
            for(int k = 0; k < numberOfTrainerCards; k++)
            {
                deck.add(new Trainer());
            }

            //shuffle the deck
            Collections.shuffle(deck);

            //Set the active deck for the player and draw 7 cards
            player.setActiveDeck(deck);
            for(int i = 0; i < 7; i++)
            {
                player.drawCard();
            }
            //Mulligan until a pokemon is found
            while(!experiment.hasPokemon(player))
            {
                player.mulligan();
            }
            //Draw prizes
            player.drawPrizes();
            //check if the prize pool has atleast numerOfTrainerInPrize trainer cards, if so increment success
            if(experiment.isBricked(player, numerOfTrainerInPrize))
            {
                success++;
            }
        }
        return success / runs;
    }

    /**
     * Method to determine the success rate of a player bricking their deck
     */
    private ArrayList<Double> brickedDeck()
    {
        //Initalized objects/variables
        Prizes experiment = new Prizes();
        int runs = 1000;
        ArrayList<Double> successRates = new ArrayList<>();

        for(int trainerCount = 0; trainerCount < 5; trainerCount++)
        {
            int success = 0;
            for(int j = 0; j < runs; j++)
            {
                Player player = new Player();
                ArrayList<Card> deck = new ArrayList<>();
                // Fill deck with 60 cards of either energy or trainer
                for(int k = 0; k < 48 - trainerCount; k++)
                {
                    deck.add(new Energy());
                }
                // Replace pokemonCount cards with pokemon cards, then shuffle the deck
                for(int k = 0; k < 12; k++)
                {
                    deck.add(new Pokemon());
                }
                for(int k = 0; k < trainerCount; k++)
                {
                    deck.add(new Trainer());
                }
                Collections.shuffle(deck);

                //Set the active deck for the player and draw 7 cards
                player.setActiveDeck(deck);
                for(int i = 0; i < 7; i++)
                {
                    player.drawCard();
                }
                //Mulligan until a pokemon is found
                while(!experiment.hasPokemon(player))
                {
                    player.mulligan();
                }
                //Draw prizes
                player.drawPrizes();
                //Check if the player is bricked
                if(experiment.isBricked(player, trainerCount))
                {
                    success++;
                }
            }
            successRates.add((double)success / runs);
        }

        return successRates;      
    }

    /**
     * Checks if the player has a pokemon in their hand
     */
    private boolean hasPokemon(Player player)
    {
        ArrayList<Card> hand = player.getHand();
        for(Card card : hand)
        {
            if(card instanceof Pokemon)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player has enough trainer cards in their prize pool to be bricked
     */
    private boolean isBricked(Player player, int prizeCount)
    {
        ArrayList<Card> prizePool = player.getPrizePool();
        int prizesFound = 0;
        for(Card card : prizePool)
        {
            if(card instanceof Trainer)
            {
                prizesFound++;
            }
        }
        if(prizesFound >= prizeCount)
        {
            return true;
        }
        return false;
    }
}



