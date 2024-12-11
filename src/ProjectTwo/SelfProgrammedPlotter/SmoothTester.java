package ProjectTwo.SelfProgrammedPlotter;

import java.io.File;

public class SmoothTester 
{
    public static void main(String[]args)
    {
        File file = new File("smoothedDataOneIterTwelve.csv");
        Plotter plot = new Plotter();
        try 
        {
            file = plot.smoothData(file, "smoothedDataOneIterThirteen.csv");
            file = plot.smoothData(file, "smoothedDataOneIterFourteen.csv");
            file = plot.smoothData(file, "smoothedDataOneIterFifthteen.csv");
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
