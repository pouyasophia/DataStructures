import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class percolationStats {

    private percolation percObj;
    private int gridSize;
    private int numTrials;
    private double [] trialResults;
    private double confidenceInterval = 1.96;

    // perform independent trials on an n-by-n grid
    public percolationStats(int n, int trials){
        
        gridSize = n;
        numTrials = trials;
        trialResults = new double [numTrials];

        
        for(int trialNum = 0; trialNum<numTrials; ++trialNum){
        
        percObj = new percolation(gridSize);
            //random numbers for row and col w/n specific range
             while(!percObj.percolates()){
                
                //random number for row and column w/n range
                int randRow = StdRandom.uniformInt(1,gridSize+1);
                int randCol = StdRandom.uniformInt(1,gridSize+1);
                
                //continue to open sites until the system percolates
                if(percObj.isOpen(randRow, randCol) == true){
                        continue;
                }
                else {
                        percObj.open(randRow,randCol);
                }
             }

             //add result to trial array as fraction of open sites/ total sites
             trialResults[trialNum] = Double.valueOf(percObj.numberOfOpenSites())/(Double.valueOf(gridSize*gridSize));
             //System.out.println("Trial Results" + " = " + Arrays.toString(trialResults));
        }
           

    }

    // sample mean of percolation threshold
    public double mean(){
        return(StdStats.mean(trialResults));
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return(StdStats.stddev(trialResults));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return(mean()-(confidenceInterval*stddev()/Math.sqrt(numTrials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return(mean()+(confidenceInterval*stddev()/Math.sqrt(numTrials)));
    }

   // test client (see below)
   public static void main(String[] args){
    try{
       int num = Integer.valueOf(args[0]);
       int nT = Integer.valueOf(args[1]);
       
       percolationStats percStatsObj = new percolationStats(num, nT);
       
       System.out.println("mean                    =" + percStatsObj.mean());
       System.out.println("stddev                  =" + percStatsObj.stddev());
       System.out.println("95% confidence interval =" + "["+percStatsObj.confidenceLo()+", "+percStatsObj.confidenceHi()+"]");
       
        }  catch(Exception e){
                System.out.println("need two integer arguments: gridSize and number of trials must both be greater than 0");
        }
    
   }
}
