package PokemonCardGame.CardTypes.PokemonCards.ElectricType;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.ElectricEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import java.util.Random;

public class Shinx extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Shinx, electric type, 
     * 60hp
     * paralyzing gaze(1 neutral): flip a coin, if heads, the defending pokemon is paralyzed
     * static shock(2 electric): 30 damage 
     * weak to fighting 2x
     * resists metal -20
     * retreat: 1 neutral
     */

    /**
     * Constructor for Shinx
     * @param owner
     */
    public Shinx(Player owner)
    {
        super(0, "Shinx", 60, owner, new ElectricEnergy(), 1);
    }

    /**
     * Use moves for Shinx
     * @param move
     * @param opponent
     */
    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            paralyzingGaze(target);
        }
        else if(move == 2)
        {
            staticShock(target);
        }
    }

    /**
     * Paralyzing gaze move for Shinx
     * @param target
     */
    public void paralyzingGaze(Pokemon target)
    {
        Random rng = new Random();
        if(rng.nextInt(2) == 0)
        {
            target.setParalyzed(true);
        }
    }

    /**
     * Static shock move for Shinx
     * @param target
     */
    public void staticShock(Pokemon target)
    {
        target.takeDamage(30);
    }


}
