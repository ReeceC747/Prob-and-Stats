package PokemonCardGame.CardTypes.EnergyCards;

import PokemonCardGame.Player;

public class ElectricEnergy extends PokemonCardGame.CardTypes.EnergyCards.Energy
{
    public ElectricEnergy(Player ownerP)
    {
        super(ownerP);
    }

    public ElectricEnergy()
    {
        super(new Player());
    }
    
}
