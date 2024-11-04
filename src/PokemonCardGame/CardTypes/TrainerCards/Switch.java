package PokemonCardGame.CardTypes.TrainerCards;

import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

public class Switch extends PokemonCardGame.CardTypes.TrainerCards.Trainer
{
    /**
     * Switch the active pokemon with a benched pokemon
     */

    public Switch(Player owner)
    {
        super("Switch", owner);
    }

    @Override
    public void play(Player owner)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Select a benched pokemon to switch with the active pokemon");
        Pokemon temp = owner.getActivePokemon();
        for(int i = 0; i < owner.getBench().size(); i++)
        {
            System.out.println((i + 1) + ". " + owner.getBench().get(i).getName());
        }

        int choice = input.nextInt();
        input.nextLine();
 
        owner.setActivePokemon(owner.getBench().get(choice - 1));
        owner.getBench().add(temp);
        owner.getBench().remove(choice - 1);
        input.close();
        
    }

}
