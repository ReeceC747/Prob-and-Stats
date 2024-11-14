package PokemonCardGame.CardTypes.PokemonCards.ElectricType;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.ElectricEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class Luxio extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Luxio is an Electric type Pokémon.
     * Luxio (stage 1) evolves from Shinx.
     * 80 HP
     * Disconnect (1 neutral) 30 damage opponent cant play trainer cards next turn
     * weak to fighting 2x
     * resists metal -20
     * retreat: 1 neutral
     */

    /**
     * Constructor for the Luxio class
     * @param owner Owner of the pokemon card
     */
    public Luxio(Player owner)
    {
        super(1, "Luxio", 80, owner, new ElectricEnergy(), 1);
    }

    /**
     * Function to use the moves of the pokemon
     * @param move Move to be used
     * @param opponent Opponent player
     */
    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            Disonnect(opponent, target);
        }
    }

    public void Disonnect(Player Opponent, Pokemon target)
    {
        target.takeDamage(30);
        Opponent.setCanPlayTrainer(false);
    }
}
