public class PercolationDFSFast extends PercolationDFS {
    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    public void updateOnOpen(int row, int col) {
        boolean neighbor = false;
        if (row > 0 && myGrid[row-1][col]==IPercolate.FULL) neighbor = true;
        if (row < myGrid.length-1 && myGrid[row+1][col]==IPercolate.FULL) neighbor = true;
        if (col > 0 && myGrid[row][col-1]==IPercolate.FULL) neighbor = true;
        if (col < myGrid[row].length-1 && myGrid[row][col+1]==IPercolate.FULL) neighbor = true;
        if (row==0 || neighbor)
            dfs(row,col);
    }
}
