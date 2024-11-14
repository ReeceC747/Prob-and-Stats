package PokemonCardGame.CardTypes.PokemonCards.MetalType;

import java.util.ArrayList;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.MetalEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

import java.util.Scanner;

public class Dialga extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Dialga metal type
     * 130 hp
     * Temporal Backflow (1 metal) put a card from the discard pile into your hand
     * Metal Blast (3 neutral) 60 damage does 20 more damage for metal each energy attached to this pokemon
     * weak to fire 2x
     * resists grass -30
     * retreat: 2 neutral 
     */

    private Scanner input;

    public Dialga(Player owner)
    {
        super(0, "Dialga", 130, owner, new MetalEnergy(), 2);
    }

    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        { 
            temporalBackflow();
        }
        else if(move == 2)
        {
            metalBlast(target);
        }
    }

    public void temporalBackflow()
    {
        ArrayList<Card> discardPile = getOwner().getDiscardPile();
        if(hasDisardPile())
        {
            input = new Scanner(System.in);
            System.out.println("Select a card from the discard pile to add to your hand");
            for(int i = 0; i < discardPile.size(); i++)
            {
                System.out.println((i + 1) + ": " + discardPile.get(i).getName());
            }
            int choice = input.nextInt();
            input.nextLine();

            getOwner().getHand().add(discardPile.get(choice - 1));
        }
        else
        {
            System.out.println("No cards in discard pile");
        }
    }

    public boolean hasDisardPile()
    {
        if(getOwner().getDiscardPile().size() > 0)
        {
            return true;
        }
        return false;
    }

    public void metalBlast(Pokemon target)
    {
        int damage = 60;
        damage += 20 * getAttatchedEnergy().size();
        target.takeDamage(damage);
    }
    
}
