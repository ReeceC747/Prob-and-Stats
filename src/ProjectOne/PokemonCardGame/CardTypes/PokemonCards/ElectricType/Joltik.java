package PokemonCardGame.CardTypes.PokemonCards.ElectricType;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.ElectricEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import java.util.Random;

public class Joltik extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Joltik, electric type
     * 40 hp
     * Flail around (1 electric): flip 3 coins, do 10 damage for each heads
     * weak to fighting 2x
     * retreat: 1 neutral
     */

    /**
     * Constructor for Joltik
     * @param owner Owner of the card
     */
    public Joltik(Player owner)
    {

        super(0, "Joltik", 40, owner, new ElectricEnergy(), 1);
        moves.add("1. 1 Electric: Flail Around: Flip 3 coins, do 10 damage for each one that lands heads");
    }

    /**
     * Flail around move for Joltik
     * @param target Pokemon to attack
     */
    public void flailAround(Pokemon target)
    {
        Random rng = new Random();
        int damage = 0;
        for(int i = 0; i < 3; i++)
        {
            if(rng.nextInt(2) == 0)
            {
                damage += 10;
            }
        }
        target.takeDamage(damage);
    }

    /**
     * Use moves for Joltik
     * @param move Move to use
     * @param opponent Opponent to attack
     */
    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            flailAround(target);
        }
    }
}
