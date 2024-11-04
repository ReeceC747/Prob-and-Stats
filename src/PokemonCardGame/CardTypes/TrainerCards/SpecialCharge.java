package PokemonCardGame.CardTypes.TrainerCards;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.Energy;

public class SpecialCharge extends PokemonCardGame.CardTypes.TrainerCards.Trainer
{
    /**
     * shuffle 2 special energy cards from discard pile into deck, changed to basic since currently no special energy cards
     */

    public SpecialCharge(Player owner)
    {
        super("Special Charge", owner);
    }

    @Override
    public void play(Player owner)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Card> discardPile = owner.getDiscardPile();
        ArrayList<Card> energy = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        boolean done = false;
        int energiesAdded = 0;
        for(int i = 0; i < discardPile.size(); i++)
        {
            if(discardPile.get(i) instanceof Energy)
            {
                energy.add(discardPile.get(i));
                indexes.add(i);
            } 
        }

        if(energy.size() == 0)
        {
            System.out.println("No energy in discard pile");
            owner.getHand().add(SpecialCharge.this);
        }
        else
        {
            System.out.println("Select up to2 energy cards to shuffle into deck");
            while(!done || energiesAdded == 2)
            {
                if(energy.size() == 0)
                {
                    System.out.println("No more energy in discard pile");
                    done = true;
                    break;
                }
                for(int i = 0; i < indexes.size(); i++)
                {
                    System.out.println((i + 1) + ": " + energy.get(i).getName());
                }               

                int choice = input.nextInt();
                input.nextLine();

                if(choice < 0 || choice > energy.size())
                {
                    System.out.println("Invalid choice");
                }
                else
                {
                    owner.getActiveDeck().add(energy.get(choice - 1));
                    discardPile.remove((int)indexes.get(choice - 1));
                    energiesAdded++;
                }
            }

        }
        input.close();
    }
}
