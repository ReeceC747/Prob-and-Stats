package PokemonCardGame.CardTypes;

import PokemonCardGame.Player;

public class Card 
{
    private String name;
    private Player owner;

    public Card(String nameP, Player ownerP)
    {
        name = nameP;
        owner = ownerP;
    }

    // Getters and Setters --------------------------------------------
    public String getName()
    {
        return name;
    } 

    public void setName(String nameP)
    {
        name = nameP;
    }

    public Player getOwner()
    {
        return owner;
    }

    public void setOwner(Player ownerP)
    {
        owner = ownerP;
    }
}
