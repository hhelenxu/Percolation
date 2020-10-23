public class PercolationDFSFast extends PercolationDFS {
    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    public void updateOnOpen(int row, int col) {
        boolean neighbor = false;
        if (inBounds(row-1,col) && isFull(row-1,col)) neighbor = true;
        if (inBounds(row+1,col) && isFull(row+1,col)) neighbor = true;
        if (inBounds(row,col-1) && isFull(row,col-1)) neighbor = true;
        if (inBounds(row,col+1) && isFull(row,col+1)) neighbor = true;
        if (row==0 || neighbor)
            dfs(row,col);
    }
}
