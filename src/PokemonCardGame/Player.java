package PokemonCardGame;

import PokemonCardGame.CardTypes.EnergyCards.*;
import java.util.ArrayList;
import PokemonCardGame.CardTypes.Card;

public class Player 
{
    private ArrayList<Card> premadeDeck;
    public Player()
    {
        //Puts 6 Lightning and 6 Metal energy cards into the deck
        for(int i = 0; i < 6; i++)
        {
            premadeDeck.add(new LightningEnergy());
        }
        for(int i = 0; i < 6; i++)
        {
            premadeDeck.add(new MetalEnergy());
        }
    }
}
