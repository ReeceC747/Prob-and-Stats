package PokemonCardGame.CardTypes.PokemonCards.MetalType;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.MetalEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import java.util.Random;

public class Cufant extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Cufant, metal type
     * 100 hp
     * Stomp (2 metal): 20 damage, flip a coin, if heads, 20 more damage
     * weak to fire 2x
     * resists grass -30
     * retreat: 3 neutral
     */ 
    public Cufant(Player owner)
    {
        super(0, "Cufant", 100, owner, new MetalEnergy(), 3);
    }

    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            stomp(target);
        }
    }

    public void stomp(Pokemon target)
    {
        Random rng = new Random();
        target.takeDamage(20);
        if(rng.nextInt(2) == 0)
        {
            target.takeDamage(20);
        }
    }
}
