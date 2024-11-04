package MonteCarlo.ThreeDoors;

public class ThreeDoorsTest 
{
    public static void main(String[] args)  
    {
        ThreeDoors play = new ThreeDoors();
        int games = 10000;
        play.playDealNoDeal(games);
        play.playDealNoDealChange(games);
    }
    
}
