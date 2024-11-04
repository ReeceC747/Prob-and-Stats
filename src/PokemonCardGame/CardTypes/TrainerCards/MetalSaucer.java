package PokemonCardGame.CardTypes.TrainerCards;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.Energy;
import PokemonCardGame.CardTypes.EnergyCards.MetalEnergy;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;
import PokemonCardGame.Player;

public class MetalSaucer extends PokemonCardGame.CardTypes.TrainerCards.Trainer{
    /**
     * attach a metal energy from discard to a benched metal pokemon
     */

    public MetalSaucer(Player owner)
    {
        super("Metal Saucer", owner);
    }

    @Override 
    public void play(Player owner)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Card> discardPile = getOwner().getDiscardPile();
        ArrayList<Pokemon> benchedMetalPokemon = new ArrayList<>();
        int energyIndex = -1;
        ArrayList<Pokemon> bench = owner.getBench();

        for(int i = 0; i < discardPile.size(); i++)
        {
            if(discardPile.get(i) instanceof MetalEnergy)
            {
                energyIndex = i;
                break;
            }
        }

        if(energyIndex == -1)
        {
            System.out.println("No metal energy in discard pile");
            owner.getHand().add(MetalSaucer.this);
        }
        else
        {
            for(int i = 0; i < bench.size(); i++)
            {
                if(bench.get(i).getType() instanceof MetalEnergy)
                {
                    benchedMetalPokemon.add(bench.get(i));
                }
            }

            if(benchedMetalPokemon.size() == 0)
            {
                System.out.println("No benched metal pokemon");
                owner.getHand().add(MetalSaucer.this);
            }
            else
            {
                System.out.println("Select a benched metal pokemon to attach the metal energy to");
                for(int i = 0; i < benchedMetalPokemon.size(); i++)
                {
                    System.out.println((i + 1) + ". " + benchedMetalPokemon.get(i).getName());
                }
                int choice = input.nextInt();
                input.nextLine();
                benchedMetalPokemon.get(choice - 1).getAttatchedEnergy().add((Energy) discardPile.get(energyIndex));
                discardPile.remove(energyIndex);
            }
        }
        input.close();

    }
}

