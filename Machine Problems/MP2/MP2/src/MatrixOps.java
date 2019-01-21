import java.util.*;
import java.io.*;

public class MatrixOps implements Runnable
{
    private ArrayList<ArrayList<Integer>> rowI;
    private ArrayList<ArrayList<Integer>> matrixJ;
    private ArrayList<ArrayList<Integer>> resultMat;

    private Thread t;

    public MatrixOps(ArrayList<ArrayList<Integer>> rowI, ArrayList<ArrayList<Integer>> matrixJ)
    {
        System.out.println("Creating Thread: " + Thread.currentThread() + "\n");
        this.rowI = rowI;
        this.matrixJ = matrixJ;
    }

    public void start ()
    {
        System.out.println("Starting Thread: ");
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    public ArrayList<ArrayList<Integer>> getResultMat()
    {
        return this.resultMat;
    }

    public void run()
    {

        resultMat = rowOps();
    }

    private ArrayList<ArrayList<Integer>> rowOps()
    {
        ArrayList<ArrayList<Integer>> newMat = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> newRow;
        for(int i = 0; i < this.rowI.size(); i++)
        {
            newRow = new ArrayList<Integer>();

            for(int j = 0; j < matrixJ.size(); j++)
            {
                newRow.add(rowColMult(this.rowI.get(i), this.matrixJ.get(j)));
            }
            newMat.add(newRow);

            /* System.out.println("New Row: ");
            for(int k = 0; k < newRow.size(); k++)
            {
                System.out.print(newRow.get(k) + " ");
            }
            System.out.println();*/
        }

        return newMat;

        //TODO: figure out how to print the new rows to verify it works
    }

    // Multiplies a row by a column
    private int rowColMult(ArrayList<Integer> row, ArrayList<Integer> column)
    {
        int sum = 0;

        for(int i = 0; i < row.size(); i++)
        {
            // (i[0] * j[0]) + (i[1] * j[1])
            sum += (row.get(i) * column.get(i));
        }

        // System.out.println("Element Op: " + sum);
        return sum;
    }
}
