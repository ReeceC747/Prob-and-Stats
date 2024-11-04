package PokemonCardGame.CardTypes.PokemonCards.ElectricType;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.ElectricEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class Luxray extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Luxray is an Electric type Pokémon.
     * Luxray (Stage 2) evolves from Luxio (Stage 1)
     * 150 HP
     * Ability Swelling Flash: once during your turn when this is in hand and you have more prize cards than your opponent you may play this card to your bench
     * wild charge (1 electric, 2 neutral) 180 damage, 20 damage to self
     * weak to fighting 2x
     * retreat: 1 neutral
     */

    /*
     * Constructor for Luxray that takes in the player it belongs to
     */
    public Luxray(Player owner)
    {
        super(2, "Luxray", 150, owner, new ElectricEnergy(), 1);
    }

    /*
     * Method that uses the moves of Luxray
     */
    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            wildCharge(target);
        }
    }

    /*
     * Method that represents the move wild charge
     */
    public void wildCharge(Pokemon target)
    {
        target.takeDamage(180);
        takeDamage(20);
    }


}
