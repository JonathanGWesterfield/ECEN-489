import java.util.*;
import java.io.*;

public class MatrixOps implements Runnable
{
    private ArrayList<ArrayList<Integer>> rowI;
    private ArrayList<ArrayList<Integer>> matrixJ;
    private ArrayList<ArrayList<Integer>> resultMat;

    public MatrixOps(ArrayList<ArrayList<Integer>> rowI, ArrayList<ArrayList<Integer>> matrixJ)
    {
        this.rowI = rowI;
        this.matrixJ = matrixJ;
    }

    public void run()
    {
        resultMat = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> resultRow = new ArrayList<>();
        for(int i = 0; i < this.rowI.size(); i++)
        {
            for(int j = 0; j < this.rowI.get(i).size(); j++)
            {
                // do row column operations
            }
        }
    }

    // Multiplies a row by a column
    public int rowColMult(ArrayList<Integer> row, ArrayList<Integer> column)
    {
        int sum = 0;

        for(int i = 0; i < row.size(); i++)
        {
            // (i[0] * j[0]) + (i[1] * j[1])
            sum += (row.get(i) * column.get(i));
        }

        return sum;
    }
}
