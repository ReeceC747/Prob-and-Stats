package PokemonCardGame.CardTypes.PokemonCards.MetalType;

import java.util.ArrayList;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.MetalEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import java.util.Scanner;

public class Klink extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Klink, metal type
     * 60 hp
     * Call for backup (1 metal): search your deck for a metal pokemon and put it into your hand and shuffle the deck
     * weak to fire 2x
     * resists grass -30
     * retreat: 2 neutral 
     */

    private Scanner input;

    public Klink(Player owner)
    {
        super(0, "Klink", 60, owner, new MetalEnergy(), 2);
    }

    @Override
    public void useMoves(int move, Player opponent)
    {
        if(move == 1)
        {
            callForBackup();
        }
    }

    public void callForBackup()
    {
        input = new Scanner(System.in);
        Player owner = getOwner();
        ArrayList<Card> activeDeck = owner.getActiveDeck();
        ArrayList<Pokemon> metalPokemon = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < activeDeck.size(); i++)
        {
            if(activeDeck.get(i) instanceof Pokemon)
            {
                if(((Pokemon) activeDeck.get(i)).getType() instanceof MetalEnergy)
                {
                    metalPokemon.add((Pokemon) activeDeck.get(i));
                    indexes.add(i);
                }
            }
        }

        if(metalPokemon.size() == 0)
        {
            System.out.println("No metal pokemon found in deck");
        }
        else
        {
            System.out.println("Select a metal pokemon to add to your hand");
            for(int i = 0; i < metalPokemon.size(); i++)
            {
                System.out.println((i + 1) + ": " + metalPokemon.get(i).getName());
            }
            int choice = input.nextInt();
            input.nextLine();
            owner.getHand().add(activeDeck.get(indexes.get(choice - 1)));
            activeDeck.remove((int) indexes.get(choice - 1));
        }
        

    }
    
}
