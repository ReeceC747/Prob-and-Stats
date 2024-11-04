package PokemonCardGame.CardTypes.TrainerCards;

import PokemonCardGame.Player;

public class EnhancedHammer extends PokemonCardGame.CardTypes.TrainerCards.Trainer
{
    /**
     * discard an energy card from opponent's active pokemon
     */
    
    public EnhancedHammer(Player ownerP)
    {
        super("Enhanced Hammer", ownerP);
    }

    @Override
    public void play(Player owner) 
    {
        if(owner.getOpponent().getActivePokemon().getAttatchedEnergy().size() == 0)
        {
            System.out.println("Opponent has no energy to discard");
            owner.getHand().add(EnhancedHammer.this);
        }
        else
        {
            Player opponent = owner.getOpponent();
            opponent.discardEnergy(opponent.getActivePokemon());
        }

    }
}
