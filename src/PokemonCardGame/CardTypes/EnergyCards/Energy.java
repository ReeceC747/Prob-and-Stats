package PokemonCardGame.CardTypes.EnergyCards;

import PokemonCardGame.Player;

public class Energy extends PokemonCardGame.CardTypes.Card
{

    /**
     * Constructor for Energy
     * @param ownerP
     */
    public Energy(Player ownerP, String nameP)
    {
        super(nameP, ownerP);
    }

    /**
     * Default constructor for Energy
     */
    public Energy()
    {
        super("Energy", new Player());
    }
}
