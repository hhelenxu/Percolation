import java.util.*;

public class PercolationBFS extends PercolationDFSFast {
    public PercolationBFS(int n) {
        super(n);
    }

    @Override
    public void dfs(int row, int col) {
        // out of bounds?
        if (! inBounds(row,col)) return;

        // full or NOT open, don't process
        if (isFull(row, col) || !isOpen(row, col)) return;

        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        Queue<int[]> qp = new LinkedList<>();
        myGrid[row][col] = IPercolate.FULL;
        qp.add(new int[]{row,col});
        while (qp.size() != 0){
            int[] p = qp.remove();
            for(int i=0; i < rowDelta.length; i++){
                row = p[0] + rowDelta[i];
                col = p[1] + colDelta[i];
                if (inBounds(row,col) && isOpen(row,col) && !isFull(row,col)) {
                    myGrid[row][col] = IPercolate.FULL;
                    qp.add(new int[]{row,col});
                }
            }
        }

    }
}
