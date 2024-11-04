package PokemonCardGame.CardTypes.PokemonCards.ElectricType;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.ElectricEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import PokemonCardGame.CardTypes.TrainerCards.Trainer;

public class Magneton extends PokemonCardGame.CardTypes.PokemonCards.Pokemon
{
    /**
     * Magneton (stage 1) is electric
     * it evolves from Magnemite (basic)
     * 90 hp
     * 
     * Junk magnet (1 electric): put up to 2 trainer cards from discard pile into your hand
     * Head bolt (1 electric 2 neutral): 60 damage
     * weak to fighting 2x
     * retreat: 2 neutral
    */
    
    //scanner for user input
    private Scanner input;

    /**
     * Constructor for Magneton
     * @param owner
     */
    public Magneton(Player owner)
    {
        super(1, "Magneton", 90, owner, new ElectricEnergy(), 2);
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
            junkMagnet();
        }
        else if(move == 2)
        {
            headBolt(target);
        }
    }
    
    /**
     * Method to put up to 2 trainer cards from discard pile into your hand
     */
    public void junkMagnet()
    {
        input = new Scanner(System.in);
        Player owner = getOwner();
        int trainersAdded = 0;
        ArrayList<Card> discard = owner.getDiscardPile();
        int choice = 0;
        if(hasTrainers(discard).size() == 0)
        {
            System.out.println("No trainers in discard pile");
        }
        else
        {
            System.out.println("Select up to 2 trainers to add to your hand");
            for(int i = 0; i < discard.size(); i++)
            {
                System.out.println((i + 1) + ". " + discard.get(i).getName());
            }
            System.out.println("0. Done");

            while(trainersAdded < 2 || choice == 0)
            {
                choice = input.nextInt();
                input.nextLine();
                if(choice < 0 || choice > discard.size())
                {
                    System.out.println("Invalid choice");
                }
                else
                {
                    owner.getHand().add(discard.get(choice - 1));
                    discard.remove(choice - 1);
                }

            }
        }
    }

    /**
     * Method to check if there are trainers in the discard pile
     * @param discard
     * @return
     */
    public ArrayList<Trainer> hasTrainers(ArrayList<Card> discard)
    {
        ArrayList<Trainer> trainers = new ArrayList<Trainer>();
        for(int i = 0; i < discard.size(); i++)
        {
            if(discard.get(i) instanceof Trainer)
            {
                trainers.add((Trainer) discard.get(i));
            }
        }
        return trainers;
    }

    /**
     * Method to deal 60 damage
     * @param target
     */
    public void headBolt(Pokemon target)
    {
        target.takeDamage(60);
    }
}
