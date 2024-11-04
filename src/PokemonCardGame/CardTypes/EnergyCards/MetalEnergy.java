package PokemonCardGame.CardTypes.EnergyCards;

import PokemonCardGame.Player;

public class MetalEnergy extends PokemonCardGame.CardTypes.EnergyCards.Energy
{

    /**
     * Constructor for MetalEnergy
     * @param ownerP
     */
    public MetalEnergy(Player ownerP)
    {
        super(ownerP);
    }

    /**
     * Default constructor for MetalEnergy
     */
    public MetalEnergy()
    {
        super(new Player());
    }
}
