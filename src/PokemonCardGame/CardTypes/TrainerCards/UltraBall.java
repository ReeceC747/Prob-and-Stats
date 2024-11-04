package PokemonCardGame.CardTypes.TrainerCards;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class UltraBall extends PokemonCardGame.CardTypes.TrainerCards.Trainer
{
    /**
     * discard 2 cards from hand, search deck for a pokemon, put it in hand
     */

    public UltraBall(Player owner)
    {
        super("Ultra Ball", owner);
    }

    @Override
    public void play(Player owner)
    {
        Scanner input = new Scanner(System.in);
        int discardCount = 0;
        ArrayList<Integer> pokemon = new ArrayList<Integer>();
        if(owner.getHand().size() < 2)
        {
            System.out.println("Not enough cards in hand");
        }
        else
        {
            while(discardCount != 2)
            {
                System.out.println("Choose 2 cards to discard from your hand");
                for(int i = 0; i < owner.getHand().size(); i++)
                {
                    System.out.println((i + 1) + ": " + owner.getHand().get(i).getName());
                }

                int choice = input.nextInt();
                input.nextLine();

                if(choice < 0 || choice > owner.getHand().size())
                {
                    System.out.println("Invalid choice");
                }
                else
                {
                    owner.getDiscardPile().add(owner.getHand().get(choice - 1));
                    owner.getHand().remove(choice - 1);
                    discardCount++;
                }
            }

            for(int i = 0; i < owner.getActiveDeck().size(); i++)
            {
                if(owner.getActiveDeck().get(i) instanceof Pokemon)
                {
                    pokemon.add(i);
                }
            }

            System.out.println("Select a pokemon to add to your hand");
            for(int i = 0; i < pokemon.size(); i++)
            {
                System.out.println((i + 1) + ": " + owner.getActiveDeck().get(pokemon.get(i)).getName());
            } 

            int choice = input.nextInt();
            input.nextLine();

            owner.getHand().add(owner.getActiveDeck().get(pokemon.get(choice - 1)));
            owner.getActiveDeck().remove((int)pokemon.get(choice - 1));
        }
        input.close();
    }
    
}
