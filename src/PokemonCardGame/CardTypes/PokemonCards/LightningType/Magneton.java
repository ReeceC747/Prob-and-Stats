package PokemonCardGame.CardTypes.PokemonCards.LightningType;

import PokemonCardGame.CardTypes.PokemonCards.Pokemon;

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
    public Magneton()
    {
        super(1, "Magneton");
    }
}
