package PokemonCardGame.CardTypes.TrainerCards;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.Player;

public class TrainersMail extends PokemonCardGame.CardTypes.TrainerCards.Trainer
{
    /**
     * look at the top 4 cards of your deck, put a trainer card you find there (besides trainers mail)
     *  into your hand, shuffle the other cards back into your deck
     */

    public TrainersMail(Player owner)
    {
        super("Trainers Mail", owner);
    }

    @Override
    public void play(Player owner)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < 4; i++) 
        {
            if(owner.getActiveDeck().size() == 0)
            {
                break;
            }
            if(owner.getActiveDeck().get(i) instanceof Trainer && owner.getActiveDeck().get(i) instanceof TrainersMail == false)
            {
                indexes.add(i);
            }
        }

        if(indexes.size() == 0)
        {
            System.out.println("No valid trainers in top 4 cards");
            owner.getHand().add(TrainersMail.this);
        }
        else
        {
            System.out.println("Select a trainer to add to your hand");
            for(int i = 0; i < indexes.size(); i++)
            {
                System.out.println((i + 1) + ": " + owner.getActiveDeck().get(indexes.get(i)).getName());
            }
            int choice = input.nextInt();
            input.nextLine();

            owner.getHand().add(owner.getActiveDeck().get(indexes.get(choice - 1)));
            owner.getActiveDeck().remove((int)indexes.get(choice - 1));
        }
        input.close();

    }

}
