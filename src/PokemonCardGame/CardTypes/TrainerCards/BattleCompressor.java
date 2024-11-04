package PokemonCardGame.CardTypes.TrainerCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;

public class BattleCompressor extends PokemonCardGame.CardTypes.TrainerCards.Trainer
{
    /**
     * discard 3 cards from deck
     */

    public BattleCompressor(Player ownerP)
    {
        super("Battle Compressor", ownerP);
    }

    @Override
    public void play(Player owner)
    {
        Scanner input = new Scanner(System.in);
        boolean done = false;
        int cardsDiscarded = 0;
        ArrayList<Card> deck = getOwner().getActiveDeck();
        while(!done || cardsDiscarded < 3 || deck.size() != 0)
        {
            System.out.println("Choose a card to discard from your deck");
            for(int i = 0; i < deck.size(); i++)
            {
                System.out.println((i + 1) + ": " + deck.get(i).getName());
            }
            System.out.println("0: Done");

            int choice = input.nextInt();
            input.nextLine();

            if(choice == 0)
            {
                done = true;
            }
            else if(choice < 0 || choice > deck.size())
            {
                System.out.println("Invalid choice");
            }
            else
            {
                getOwner().getDiscardPile().add(deck.get(choice - 1));
                deck.remove(choice - 1);
                cardsDiscarded++;
            }
        }
        input.close(); 

        Collections.shuffle(deck);
    }

}
