package PokemonCardGame.CardTypes.EnergyCards;

import PokemonCardGame.Player;

public class MetalEnergy extends PokemonCardGame.CardTypes.EnergyCards.Energy
{
    public MetalEnergy(Player ownerP)
    {
        super(ownerP);
    }

    public MetalEnergy()
    {
        super(new Player());
    }
}
