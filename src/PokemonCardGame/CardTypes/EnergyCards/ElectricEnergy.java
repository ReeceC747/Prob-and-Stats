package PokemonCardGame.CardTypes.EnergyCards;

import PokemonCardGame.Player;

public class ElectricEnergy extends PokemonCardGame.CardTypes.EnergyCards.Energy
{
    /**
     * Constructor for ElectricEnergy
     * @param ownerP
     */
    public ElectricEnergy(Player ownerP)
    {
        super(ownerP);
        setName("Electric Energy");
    }

    /**
     * Default constructor for ElectricEnergy
     */
    public ElectricEnergy()
    {
        super(new Player());
    }
    
}
