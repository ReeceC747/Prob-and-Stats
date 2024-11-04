package PokemonCardGame.CardTypes.EnergyCards;

import PokemonCardGame.Player;

public class Energy extends PokemonCardGame.CardTypes.Card
{

    private String name;

    public Energy(Player ownerP)
    {
        super("Energy", ownerP);
    }

    public Energy()
    {
        super("Energy", new Player());
    }

//Getters and Setters --------------------------------------------

    public String getName()
    {
        return name;
    }

    public void setName(String nameP)
    {
        name = nameP;
    }
    
}
