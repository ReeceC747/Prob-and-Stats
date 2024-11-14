package PokemonCardGame.CardTypes.PokemonCards.ElectricType;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.EnergyCards.ElectricEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class Magnemite extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Magnemite is an Electric type Pokémon.
     * 60 HP
     * Magnetic Switch (1 neutral) switch this pokemon with a benched one
     * Electro Ball (1 electric) 10 damage
     * weak to fighting 2x
     * retreat: 1 neutral
     */

    private Scanner input;

    /**
     * Constructor for Magnemite
     * @param owner
     */
    public Magnemite(Player owner)
    {
        super(0, "Magnemite", 60, owner, new ElectricEnergy(), 1);
        input = new Scanner(System.in);
    }


    /**
     * Method to use moves
     * @param move
     * @param opponent
     */
    @Override
    public void useMoves(int move, Player opponent)
    {
        Pokemon target = opponent.getActivePokemon();
        if(move == 1)
        {
            magneticSwitch();
        }
        else if(move == 2)
        {
            electroBall(target);
        }
    }

    /**
     * Method to switch this pokemon with a benched one
     */
    public void magneticSwitch()
    {
        //switch this pokemon with a benched one
        Player owner = getOwner();
        System.out.println("Select a pokemon to switch this one with");
        ArrayList<Pokemon> bench = owner.getBench();
        for(int i = 0; i < bench.size(); i++)
        {
            System.out.println((i + 1) + ". " + bench.get(i).getName());
        }
        int choice = input.nextInt();
        input.nextLine();

        Pokemon temp = owner.getActivePokemon();
        owner.setActivePokemon(bench.get(choice - 1));
        bench.add(temp);
    }

    public void electroBall(Pokemon target)
    {
        target.takeDamage(10);
    }

}

