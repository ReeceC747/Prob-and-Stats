package PokemonCardGame.CardTypes.TrainerCards;

import PokemonCardGame.Player;

public class Trainer extends PokemonCardGame.CardTypes.Card
{
    /**
     * Trainer cards are cards that have a one time use effect
     */

    private String name;

    public Trainer(String nameP, Player ownerP)
    {
        super(nameP, ownerP);
    }

    public Trainer()
    {
        super("Trainer", new Player());
    }
 
    public void play(Player owner)
    {
        System.out.println("Played " + name);
        //Override this method in each subclass
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
