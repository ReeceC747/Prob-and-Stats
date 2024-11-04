package PokemonCardGame.CardTypes.PokemonCards.MetalType;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.MetalEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class Bronzor extends Pokemon
{
    /**
     * Bronzor, metal type
     * 50 hp
     * Ability: Evolutionary Advantage - If you go second, you may evolve this during your first turn
     * Tackle (1 metal, 1 neutral): 20 damage
     * weak to fire 2x
     * resists psychic -20 
     * retreat: 1 neutral
     */
    public Bronzor(Player owner)
    {
        super(0, "Bronzor", 50, owner, new MetalEnergy(), 1);
    }

    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            tackle(target);
        }
    }

    public void tackle(Pokemon target)
    {
        target.takeDamage(20);
    }
}
