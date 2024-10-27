package PokemonCardGame;

import PokemonCardGame.CardTypes.EnergyCards.*;
import PokemonCardGame.CardTypes.PokemonCards.LightningType.Joltik;
import PokemonCardGame.CardTypes.PokemonCards.LightningType.Luxio;
import PokemonCardGame.CardTypes.PokemonCards.LightningType.Luxray;
import PokemonCardGame.CardTypes.PokemonCards.LightningType.Magnemite;
import PokemonCardGame.CardTypes.PokemonCards.LightningType.Magneton;
import PokemonCardGame.CardTypes.PokemonCards.LightningType.Shinx;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.Bronzong;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.Bronzor;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.Cufant;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.Dialga;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.Klink;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.Togedemaru;
import PokemonCardGame.CardTypes.TrainerCards.BattleCompressor;
import PokemonCardGame.CardTypes.TrainerCards.EnhancedHammer;
import PokemonCardGame.CardTypes.TrainerCards.EvolutionIncense;
import PokemonCardGame.CardTypes.TrainerCards.MetalSaucer;
import PokemonCardGame.CardTypes.TrainerCards.SpecialCharge;
import PokemonCardGame.CardTypes.TrainerCards.Switch;
import PokemonCardGame.CardTypes.TrainerCards.TrainersMail;
import PokemonCardGame.CardTypes.TrainerCards.UltraBall;

import java.util.ArrayList;
import PokemonCardGame.CardTypes.Card;

public class Player 
{
    private ArrayList<Card> premadeDeck;
    private ArrayList<Card> customDeck;
    /**
     * Constructor for the Player class, comes with a premade deck
     */
    public Player()
    {
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
}
