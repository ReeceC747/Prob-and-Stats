package PokemonCardGame;

import PokemonCardGame.CardTypes.EnergyCards.*;
import PokemonCardGame.CardTypes.PokemonCards.LightningType.*;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.*;
import PokemonCardGame.CardTypes.TrainerCards.*;

import java.util.ArrayList;
import PokemonCardGame.CardTypes.Card;

public class Player 
{
    private ArrayList<Card> premadeDeck;
    private ArrayList<Card> customDeck;
    private ArrayList<Card> activeDeck;
    private ArrayList<Card> hand = new ArrayList<Card>();

    private String name;

    public Player()
    {
        name = "unknown";
    }
    /**
     * Constructor for the Player class, comes with a premade deck
     */
    public Player(String nameP)
    {
        name = nameP;
        premadeDeck();
    }

    private void premadeDeck()
    {
        //Puts 6 Lightning and 6 Metal energy cards into the deck (12 energy cards total)
        for(int i = 0; i < 6; i++)
        {
            premadeDeck.add(new LightningEnergy());
            premadeDeck.add(new MetalEnergy());
        }

        //Puts 4 of each trainer card into the deck (36 trainer cards total)
        for(int i = 0; i < 4; i++)
        {
            premadeDeck.add(new BattleCompressor());
            premadeDeck.add(new EnhancedHammer());
            premadeDeck.add(new EvolutionIncense());
            premadeDeck.add(new MetalSaucer());
            premadeDeck.add(new SpecialCharge());
            premadeDeck.add(new Switch());
            premadeDeck.add(new TrainersMail());
            premadeDeck.add(new UltraBall());
        }

        //Puts 12 Pokemon cards into the deck (12 Pokemon cards total)
        premadeDeck.add(new Joltik());
        premadeDeck.add(new Luxio());
        premadeDeck.add(new Luxray());
        premadeDeck.add(new Magnemite());
        premadeDeck.add(new Magneton());
        premadeDeck.add(new Shinx());
        premadeDeck.add(new Bronzong());
        premadeDeck.add(new Bronzor());
        premadeDeck.add(new Cufant());
        premadeDeck.add(new Dialga());
        premadeDeck.add(new Klink());
        premadeDeck.add(new Togedemaru());        
    }

    public void chooseDeck(int choiceP)
    {
        if(choiceP == 1)
        {
            activeDeck = premadeDeck;
        }
        else if(choiceP == 2)
        {
            activeDeck = customDeck;
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }

    public void drawCard()
    {
        hand.add(activeDeck.get(0));
        activeDeck.remove(0);
    }


    //Getters and Setters ----------------------------------------------
    public String getName()
    {
        return name;
    }

    public void setName(String nameP)
    {
        name = nameP;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void setHand(ArrayList<Card> handP)
    {
        hand = handP;
    }

    public ArrayList<Card> getActiveDeck()
    {
        return activeDeck;
    }

    public void setActiveDeck(ArrayList<Card> activeDeckP)
    {
        activeDeck = activeDeckP;
    }
}
