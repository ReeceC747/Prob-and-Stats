package PokemonCardGame.CardTypes.PokemonCards.MetalType;

import java.util.Random;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.MetalEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class Togedemaru extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Togedemaru is a Metal type Pokemon card.
     * 80 HP
     * Toge Dash (1 electric) 10 damage, flip a coin, if heads, next turn if your opponent knocks this out they dont get to claim a prize card for it
     * weak to fire 2x
     * resists grass - 30
     * retreat 2 neutral
     */
    public Togedemaru(Player owner)
    {
        super(0, "Togedemaru", 80, owner, new MetalEnergy(), 2);
    }

    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            togeDash(target);
        }
    }

    public void togeDash(Pokemon target)
    {
        Random rng = new Random();
        target.takeDamage(10);
        if(rng.nextInt(2) == 0)
        { 
            getOwner().setPrizeClaimable(false);
        }
    }
}
