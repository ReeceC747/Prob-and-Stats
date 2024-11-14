package PokemonCardGame.CardTypes.PokemonCards.MetalType;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.MetalEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class Bronzong extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Bronzong (stage 1), metal type
     * evolves from bronzor (basic)
     * 110 hp
     * ability: Metal Transfer - as many times you like during your turn, you may move a metal energy from one of your pokemon to another
     * zen headbutt (1 metal, 2 neutral): 70 damage
     * weak to fire 2x
     * resists grass -30
     * retreat: 3 neutral
     */

    /**
     * Constructor for Bronzong
     * @param owner
     */
    public Bronzong(Player owner)
    {
        super(1, "Bronzong", 110, owner, new MetalEnergy(), 3);
    }

    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            zenHeadbutt(target);
        }
    }

    public void zenHeadbutt(Pokemon target)
    {
        target.takeDamage(70);
    }

    

    
}
