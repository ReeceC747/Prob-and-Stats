package PokemonCardGame;

import PokemonCardGame.CardTypes.EnergyCards.*;
import PokemonCardGame.CardTypes.PokemonCards.MetalType.*;
import PokemonCardGame.CardTypes.TrainerCards.*;
import PokemonCardGame.CardTypes.PokemonCards.*;
import PokemonCardGame.CardTypes.PokemonCards.ElectricType.*;

import java.util.Scanner;
import java.util.ArrayList;
import PokemonCardGame.CardTypes.Card;
import java.util.Collections;

public class Player 
{
    private ArrayList<Card> premadeDeck;
    private ArrayList<Card> customDeck;
    private ArrayList<Card> activeDeck;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Card> prizePool = new ArrayList<Card>();
    private Pokemon activePokemon;
    private ArrayList<Pokemon> bench = new ArrayList<>();
    private boolean winner = false;
    private int pokemonFainted = 0;
    private boolean canPlayTrainer = true;
    private Player player;
    private Player opponent;
    private ArrayList<Card> discardPile = new ArrayList<Card>();
    private String name;
    private boolean canPlayEnergy = true;
    private boolean canRetreat = true;
    private boolean prizeClaimable = true;
    private Scanner input = new Scanner(System.in);

    public Player()
    {
    }
    /**
     * Constructor for the Player class, comes with a premade deck
     */
    public Player(String nameP)
    {
        opponent = null;
        name = nameP;
        premadeDeck = new ArrayList<Card>();
        premadeDeck();
    }

    private void premadeDeck()
    {
        //Puts 6 Lightning and 6 Metal energy cards into the deck (12 energy cards total)
        for(int i = 0; i < 6; i++)
        {
            premadeDeck.add(new ElectricEnergy());
            premadeDeck.add(new MetalEnergy());
        }

        //Puts 4 of each trainer card into the deck (36 trainer cards total)
        for(int i = 0; i < 4; i++)
        {
            premadeDeck.add(new BattleCompressor(player));
            premadeDeck.add(new EnhancedHammer(player));
            premadeDeck.add(new EvolutionIncense(player));
            premadeDeck.add(new MetalSaucer(player));
            premadeDeck.add(new SpecialCharge(player));
            premadeDeck.add(new Switch(player));
            premadeDeck.add(new TrainersMail(player));
            premadeDeck.add(new UltraBall(player));
        }

        //Puts 12 Pokemon cards into the deck (12 Pokemon cards total)
        premadeDeck.add(new Joltik(player));
        premadeDeck.add(new Luxio(player));
        premadeDeck.add(new Luxray(player));
        premadeDeck.add(new Magnemite(player));
        premadeDeck.add(new Magneton(player));
        premadeDeck.add(new Shinx(player));
        premadeDeck.add(new Bronzong(player));
        premadeDeck.add(new Bronzor(player));
        premadeDeck.add(new Cufant(player));
        premadeDeck.add(new Dialga(player));
        premadeDeck.add(new Klink(player));
        premadeDeck.add(new Togedemaru(player));        
    }

    public void chooseDeck(int choiceP)
    {
        if(choiceP == 1)
        {
            activeDeck = premadeDeck;
        }
        else if(choiceP == 2)
        {
            activeDeck = customDeck;
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }

    /**
     * add a card to the players hand, remove the card at the top of the deck
     * @return true (the player drew a card), false (the players deck has no more cards)
     */
    public boolean drawCard()
    {
        if(activeDeck.size() == 0)
        {
            return false;
        }
        hand.add(activeDeck.get(0));
        activeDeck.remove(0);
        return true;
    }

    public void mulligan()
    {

        for(int i = 0; i < hand.size(); i++)
        {
            activeDeck.add(hand.get(i));
            hand.remove(i);
        }
        Collections.shuffle(activeDeck);
        for(int i = 0; i < 7; i++)
        {
            drawCard();
        }
    }

    public void drawPrizes()
    {
        for(int i = 0; i < 6; i++)
        {
            prizePool.add(activeDeck.get(0));
            activeDeck.remove(0);
        }
    }

    /**
     * Checks if the player has a pokemon (basic) in their hand
     */
    public boolean hasPokemon()
    {
        for(Card card : hand)
        {
            if(card instanceof Pokemon && ((Pokemon)card).getStage() == 0)
            {
                return true;
            }
        }
        return false;
    }

    public void turn(Player opponent)
    {
        boolean done = false;
        System.out.println("its " + name + "'s turn\n" +
        "1. Moves (" + getActivePokemon().getName() + ")\n" +
        "2. View Hand\n" +
        "3. View Bench\n" +
        "4. Play Pokemon\n" +
        "5. Play Trainer\n" +
        "6. Play Energy\n" +
        "7. Retreat\n" +
        "0. End Turn");

        prizeClaimable = true;
        while(!done)
        {
            int choice = 0;
            if (input.hasNextInt()) 
            {
                choice = input.nextInt();
            }
            input.nextLine();

            if(choice == 1)
            {
                if(getActivePokemon().getParalyzed() == false)
                {
                    Moves(opponent);
                    done = true;
                }
                else
                {
                    System.out.println("You cannot or have already attacked with this pokemon this turn");
                }
            }
            else if(choice == 2)
            {
                viewHand();
            }
            else if(choice == 3)
            {
                viewBench();
            }
            else if(choice == 4)
            {
                playPokemon();
            }
            else if(choice == 5)
            {
                if(canPlayTrainer == true)
                {
                    playTrainer();
                }
                else
                {
                    System.out.println("You cannot play a trainer card this turn");
                }
                
            }
            else if(choice == 6)
            {
                playEnergy();
                canPlayEnergy = false;
            }
            else if(choice == 7)
            {
                reteat();
                canRetreat = false;
            }
            else if(choice == 0)
            {
                System.out.println("Turn Ended");
                done = true;
            }
            else
            {
                System.out.println("Invalid choice");
            }
        }
        canPlayTrainer = true;
        canPlayEnergy = true;
        canRetreat = true;

        if(opponent.getActivePokemon().getHp() <= 0)
        {
            System.out.println(opponent.getActivePokemon().getName() + " has fainted");
            if(opponent.getPrizeClaimable() == false)
            {
                System.out.println("You cannot claim a prize this turn due to a special effect");
            }
            else
            {
                System.out.println("you get a prize card");
                hand.add(prizePool.get(0));
                prizePool.remove(0);
                if(prizePool.size() == 0)
                {
                    System.out.println(name + " has claimed all prize cards");
                } 
            }
        }
    }

    public void Moves(Player opponent)
    {
        activePokemon.viewMoves(opponent);
    }

    public void viewHand()
    {
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.println((i + 1) + ". " + hand.get(i).getName());
        }
    }

    public void viewBench()
    {
        for(int i = 0; i < bench.size(); i++)
        {
            System.out.println((i + 1) + ". " + bench.get(i).getName());
        }
    }

    public void playPokemon()
    {
    
        boolean done = false;
        if(bench.size() >= 5)
        {
            System.out.println("bench is full");
        }
        else
        {
            System.out.println("Select basic pokemon to put on the bench");
            while(!done)
            {
                for(int i = 0; i < hand.size(); i++)
                {
                    if(hand.get(i) instanceof Pokemon && ((Pokemon)hand.get(i)).getStage() == 0)
                    {
                        System.out.println((i + 1) + ". " + hand.get(i).getName());
                    }
                }
                System.out.println("0. Done");
                int choice = 0;
                if (input.hasNextInt()) 
                {
                    choice = input.nextInt();
                }
                input.nextLine();

                if(choice - 1 < 0 || choice - 1 >= hand.size())
                {
                    System.out.println("Invalid choice");
                }
                else if(choice == 0)
                {
                    done = true;
                }
                else
                {
                    bench.add((Pokemon)hand.get(choice - 1));
                    hand.remove(choice - 1);
                    done = true;
                }
            }
        }

    }

    public void playTrainer()
    {
    
        ArrayList<Card> trainers = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        System.out.println("Select a trainer card to play");
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i) instanceof Trainer)
            {
                trainers.add(hand.get(i));
            }
        }

        for(int i = 0; i < trainers.size(); i++)
        {
            System.out.println((i + 1) + ". " + trainers.get(i).getName());
        }

        int choice = 0;
        if (input.hasNextInt()) 
        {
            choice = input.nextInt();
        }
        input.nextLine();

        if(choice - 1 < 0 || choice - 1 >= trainers.size())
        {
            System.out.println("Invalid choice");
        }
        else
        {
            Trainer card = (Trainer)trainers.get(choice - 1);
            hand.remove(indexes.get(choice - 1));            
            card.play(player);
            discardPile.add(card);
        }

    }

    public void playEnergy()
    {
    
        if(canPlayEnergy)
        {
            ArrayList<Integer> indexes = new ArrayList<>();
            for(int i = 0; i < hand.size(); i++)
            {
                if(hand.get(i) instanceof Energy)
                {
                    indexes.add(i);
                }
            }

            if(indexes.size() == 0)
            {
                System.out.println("No energy cards in hand");
            }
            else
            {
                System.out.println("Select an energy card to play");
                for(int i = 0; i < indexes.size(); i++)
                {
                    System.out.println((i + 1) + ". " + hand.get(indexes.get(i)).getName());
                }

                int choice = 0;
                if (input.hasNextInt()) 
                {
                    choice = input.nextInt();
                }
                input.nextLine();

                if(choice - 1 < 0 || choice - 1 >= indexes.size())
                {
                    System.out.println("Invalid choice");
                }
                else
                {
                    System.out.println("Select a pokemon to attach the energy to");
                    System.out.println("1. " + activePokemon.getName() + " (active)");
                    for(int i = 0; i < bench.size(); i++)
                    {
                        System.out.println((i + 2) + ". " + bench.get(i).getName());
                    }


                    if (input.hasNextInt()) 
                    {
                        choice = input.nextInt();
                    }
                    input.nextLine();

                    if(choice == 1)
                    {
                        activePokemon.getAttatchedEnergy().add((Energy)hand.get(indexes.get(choice - 1)));
                        hand.remove(indexes.get(choice - 1));
                    }
                    else if(choice - 1 < 0 || choice - 1 >= bench.size())
                    {
                        System.out.println("Invalid choice");
                    }
                    else
                    {
                        bench.get(choice - 2).getAttatchedEnergy().add((Energy)hand.get(indexes.get(choice - 1)));
                        hand.remove(indexes.get(choice - 1));
                    }
                }

            }
        }
    }

    public void reteat()
    {
        
        if(activePokemon.getRetreatCost() > activePokemon.getAttatchedEnergy().size())
        {
            System.out.println("Not enough energy to retreat");
        }
        else if(canRetreat == false)
        {
            System.out.println("Either this pokemon cannot retreat or you have already retreated this turn");
        }
        else
        {
            System.out.println("Select " + activePokemon.getRetreatCost() + " energy cards to discard");
            for(int i = 0; i < activePokemon.getAttatchedEnergy().size(); i++)
            {
                System.out.println((i + 1) + ". " + activePokemon.getAttatchedEnergy().get(i).getName());
            }

            for(int i = 0; i < activePokemon.getRetreatCost(); i++)
            {
                int choice = 0;
                if (input.hasNextInt()) 
                {
                    choice = input.nextInt();
                }
                input.nextLine();
                discardPile.add(activePokemon.getAttatchedEnergy().get(choice - 1));
                activePokemon.getAttatchedEnergy().remove(choice - 1);
            }

            Pokemon temp = activePokemon;

            System.out.println("Select a pokemon to switch with");

            for(int i = 0; i < bench.size(); i++)
            {
                System.out.println((i + 1) + ". " + bench.get(i).getName());
            }

            int choice = 0;
            if (input.hasNextInt()) 
            {
                choice = input.nextInt();
            }
            input.nextLine();

            activePokemon = bench.get(choice - 1);
            bench.remove(choice - 1);
            bench.add(temp);
        }

    }

    public void discardEnergy(Pokemon pokemon)
    {
        
        if(pokemon.getAttatchedEnergy().size() == 0)
        {
            System.out.println("No energy cards to discard");
        }
        else
        {
            System.out.println("Select an energy card to discard");
            for(int i = 0; i < pokemon.getAttatchedEnergy().size(); i++)
            {
                System.out.println((i + 1) + ". " + pokemon.getAttatchedEnergy().get(i).getName());
            }

            int choice = 0;
            if (input.hasNextInt()) 
            {
                choice = input.nextInt();
            }
            input.nextLine();

            if(choice - 1 < 0 || choice - 1 >= pokemon.getAttatchedEnergy().size())
            {
                System.out.println("Invalid choice");
            }
            else
            {
                discardPile.add(pokemon.getAttatchedEnergy().get(choice - 1));
                pokemon.getAttatchedEnergy().remove(choice - 1);
            }
        }

    }


    //Getters and Setters ----------------------------------------------
    public String getName()
    {
        return name;
    }

    public void setName(String nameP)
    {
        name = nameP;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void setHand(ArrayList<Card> handP)
    {
        hand = handP;
    }

    public ArrayList<Card> getActiveDeck()
    {
        return activeDeck;
    }

    public void setActiveDeck(ArrayList<Card> activeDeckP)
    {
        activeDeck = activeDeckP;
    }

    public ArrayList<Card> getPrizePool()
    {
        return prizePool;
    }

    public void setPrizePool(ArrayList<Card> prizePoolP)
    {
        prizePool = prizePoolP;
    }

    public Pokemon getActivePokemon()
    {
        return activePokemon;
    }

    public void setActivePokemon(Pokemon activePokemonP)
    {
        activePokemon = activePokemonP;
    }

    public ArrayList<Pokemon> getBench()
    {
        return bench;
    }

    public void setBench(ArrayList<Pokemon> benchP)
    {
        bench = benchP;
    }

    public boolean getWinner()
    {
        return winner;
    }

    public void setWinner(boolean winnerP)
    {
        winner = winnerP;
    }

    public int getPokemonFainted()
    {
        return pokemonFainted;
    }

    public void setPokemonFainted(int pokemonFaintedP)
    {
        pokemonFainted = pokemonFaintedP;
    }

    public boolean getCanPlayTrainer()
    {
        return canPlayTrainer;
    }

    public void setCanPlayTrainer(boolean canPlayTrainerP)
    {
        canPlayTrainer = canPlayTrainerP;
    }

    public ArrayList<Card> getDiscardPile()
    {
        return discardPile;
    }

    public void setDiscardPile(ArrayList<Card> discardPileP)
    {
        discardPile = discardPileP;
    }

    public Player getOpponent()
    {
        return opponent;
    }

    public void setOpponent(Player opponentP)
    {
        opponent = opponentP;
    }

    public boolean getPrizeClaimable()
    {
        return prizeClaimable;
    }

    public void setPrizeClaimable(boolean prizeClaimableP)
    {
        prizeClaimable = prizeClaimableP;
    }

}
