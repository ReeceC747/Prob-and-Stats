package PokemonCardGame.CardTypes.TrainerCards;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class EvolutionIncense extends PokemonCardGame.CardTypes.TrainerCards.Trainer
{
    /**
     * search deck for an evolution pokemon and put it into your hand and shuffle deck
     */

    public EvolutionIncense(Player ownerP)
    {
        super("Evolution Incense", ownerP);
    }

    @Override
    public void play(Player owner)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Pokemon> evolvedPokemon = new ArrayList<Pokemon>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for(int i = 0; i < owner.getActiveDeck().size(); i++)
        {
            if(owner.getActiveDeck().get(i) instanceof Pokemon)
            {
                Pokemon card = (Pokemon) owner.getActiveDeck().get(i);
                if(card.getStage() > 0)
                {
                    evolvedPokemon.add(card);
                    indexes.add(i);
                }
            }
        }

        if(evolvedPokemon.size() == 0)
        {
            System.out.println("No evolution pokemon in deck");
            owner.getHand().add(EvolutionIncense.this);
        }
        else
        {
            System.out.println("Select an evolution pokemon to add to your hand");
            for(int i = 0; i < evolvedPokemon.size(); i++)
            {
                System.out.println((i + 1) + ": " + evolvedPokemon.get(i).getName());
            }
            int choice = input.nextInt();
            input.nextLine();

            owner.getHand().add(owner.getActiveDeck().get(indexes.get(choice - 1)));
            owner.getActiveDeck().remove((int)indexes.get(choice - 1)); 



        }
        input.close();
    }
}
