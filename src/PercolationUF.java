public class PercolationUF implements IPercolate {
    private boolean[][] myGrid;
    private int myOpenCount;
    private IUnionFind myFinder;
    private final int VTOP;
    private final int VBOTTOM;

    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        finder.initialize(size*size+2);
        myFinder = finder;
        VTOP = size*size;
        VBOTTOM = size*size+1;
        myOpenCount = 0;
    }

    @Override
    public void open(int row, int col) {
        if (! inBounds(row,col))
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        if (isOpen(row,col))
            return;
        myOpenCount++;
        myGrid[row][col] = true;

        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};
        for (int i=0;i< rowDelta.length;i++) {
            int r = row + rowDelta[i];
            int c = col + colDelta[i];
            if (inBounds(r,c) && myGrid[r][c])
                myFinder.union(row*myGrid.length + col, r*myGrid.length + c);
        }
        if (row==0)
            myFinder.union(VTOP, row*myGrid.length + col);
        else if (row==myGrid.length-1)
            myFinder.union(VBOTTOM, row*myGrid.length + col);
    }

    private boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[row].length) return false;
        return true;
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row,col))
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col))
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));

        return myFinder.connected(VTOP,row*myGrid.length + col);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
