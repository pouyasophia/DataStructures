import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class percolation {

    private int gridSize;
    private int openSites = 0;
    private WeightedQuickUnionUF wquObj;
    private boolean [] gridOnly;
    private int topPt = 0;
    private int bottomPt;

    // creates n-by-n grid, with all sites initially blocked
    public percolation(int n) {
        
        gridSize = n;
        //1D array to hold all point (including top and bottom pt)
        wquObj = new WeightedQuickUnionUF(gridSize*gridSize+2);
        gridOnly = new boolean[gridSize*gridSize];
        bottomPt = (gridSize*gridSize+1); 

        if(n<=0){
            throw new IllegalArgumentException("Grid must have dimensions greater than zero");
        }
        else{
            for(int i =0; i<gridSize*gridSize; ++i){
                gridOnly[i] = false;
            }
        }
    }

    //checks for illegal rows/cols
    public void argumentCheck(int row, int col){
        if(row <= 0 || col <= 0 || row>gridSize || col>gridSize){
            throw new IllegalArgumentException("Row and Column must be greater than or equal to 1 and less than grid size");
        }
    }

    //  returns the index of the array that row, col corresponds to
    public int indxAt(int row, int col){
        return ((col+(row*gridSize-gridSize)));
    }

    //  opens the site (row, col) if it is not open already
    public void open(int row, int col){

        //make sure row and col are within bounds
        argumentCheck(row, col);
        
        //open the site from the input
        gridOnly[indxAt(row, col)-1]=true;

        //increment open sites
        ++openSites;

        //connect the sites to their neighbors
        //case 1-> first row gets connected to top point
        if(row ==1){
            wquObj.union(indxAt(row, col), topPt);
            // System.out.println("connecting indx " + indxAt(row, col)+" to top pt " + topPt);
        }

        //case 2 -> bottom row gets connected to bottom point
        if(row == gridSize){
            wquObj.union(indxAt(row, col), bottomPt);
            // System.out.println("connecting indx " + indxAt(row, col)+" to bottom pt " + bottomPt);
        }

        //case 3 -> if row and col in bounds, connect it from right, left, up, and down
        //case 3.1 -> connect up
        if((row-1)>=1 && isOpen(row-1,col)){
            wquObj.union(indxAt(row, col),indxAt(row-1, col));
            // System.out.println("connecting indx " + indxAt(row, col)+" to pt above " + indxAt(row-1, col));
        }
        //case 3.2 -> connect down
        if((row+1)<=gridSize && isOpen(row+1,col)){
            wquObj.union(indxAt(row, col),indxAt(row+1, col));
            // System.out.println("connecting indx " + indxAt(row, col)+" to pt below " + indxAt(row+1, col));
        
        }
        //case 3.1 -> connect right
        if((col+1)<=gridSize && isOpen(row,col+1)){
            wquObj.union(indxAt(row, col),indxAt(row, col+1));
            // System.out.println("connecting indx " + indxAt(row, col)+" to right pt " + indxAt(row, col+1));
        }
        //case 3.1 -> connect left
        if((col-1)>=1 && isOpen(row,col-1)){
            wquObj.union(indxAt(row, col),indxAt(row, col-1));
            // System.out.println("connecting indx " + indxAt(row, col)+" to left pt " + indxAt(row, col-1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return (gridOnly[indxAt(row,col)-1]==true);
    }

    // is the site (row, col) full?
    //full site is an open site that can be connected to an open site in the top row via a chain of neighboring open sits
    public boolean isFull(int row, int col){
        //if the root of find is the same as top, the site is full
        return (wquObj.find(indxAt(row, col))==wquObj.find(topPt));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return (wquObj.find(bottomPt) == wquObj.find(topPt));
    }
}

