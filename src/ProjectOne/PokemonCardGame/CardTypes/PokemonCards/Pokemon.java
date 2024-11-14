package PokemonCardGame.CardTypes.PokemonCards;

import java.util.ArrayList;
import java.util.Scanner;

import PokemonCardGame.Player;
import PokemonCardGame.CardTypes.Card;
import PokemonCardGame.CardTypes.EnergyCards.Energy;

public class Pokemon extends Card 
{
    private int stage;
    protected ArrayList<String> moves;
    private int hp;
    private Player owner;
    private boolean paralyzed;
    private ArrayList<Energy> attatchedEnergy;
    private Energy type;
    private int retreatCost;

    public Pokemon(int stageP, String nameP, int hpP, Player ownerP, Energy typeP, int retreatCostP)
    {
        super(nameP, ownerP);
        type = typeP;
        hp = hpP;
        stage = stageP;
        moves = new ArrayList<>();
        attatchedEnergy = new ArrayList<>();
        paralyzed = false;
        retreatCost = retreatCostP;
    }
 
    public Pokemon()
    {
        super("Pokemon", new Player());
    }

    public void viewMoves(Player opponent)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Moves for " + getName() + ":");
        for(int i = 0; i < moves.size(); i++)
        {
            System.out.println(moves.get(i));
        }
        int choice = input.nextInt();
        input.nextLine();

        if(choice - 1 > moves.size() || choice < 1)
        {
            viewMoves(opponent);
        }
        else
        {
            useMoves(choice, opponent);
        }
        input.close();

    }

    public void useMoves(int choiceP, Player opponent)
    {
        //override this method in each pokemon class
        System.out.println("This pokemon has no moves");
    }

    public void takeDamage(int damage)
    {
        hp -= damage;
    }

    // Getters and Setters --------------------------------------------

    public int getStage()
    {
        return stage;
    }

    public void setStage(int stageP)
    {
        stage = stageP;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hpP)
    {
        hp = hpP;
    }

    public Player getOwner()
    {
        return owner;
    }

    public void setOwner(Player ownerP)
    {
        owner = ownerP;
    }

    public boolean getParalyzed()
    {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzedP)
    {
        paralyzed = paralyzedP;
    }

    public ArrayList<Energy> getAttatchedEnergy()
    {
        return attatchedEnergy;
    }

    public void setAttatchedEnergy(ArrayList<Energy> energyP)
    {
        attatchedEnergy = energyP;
    }

    public Energy getType()
    {
        return type;
    }

    public void setType(Energy typeP)
    {
        type = typeP;
    }

    public int getRetreatCost()
    {
        return retreatCost;
    }

    public void setRetreatCost(int retreatCostP)
    {
        retreatCost = retreatCostP;
    }
    
}
