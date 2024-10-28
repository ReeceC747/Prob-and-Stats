package PokemonCardGame.CardTypes.PokemonCards;

import PokemonCardGame.CardTypes.Card;

public class Pokemon extends Card 
{
    private int stage;
    private String name;

    public Pokemon(int stageP, String nameP)
    {
        stage = stageP;
        String name = nameP;
    }

    // Getters and Setters --------------------------------------------

    public int getStage()
    {
        return stage;
    }

    public void setStage(int stageP)
    {
        stage = stageP;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String nameP)
    {
        name = nameP;
    }
    
}
