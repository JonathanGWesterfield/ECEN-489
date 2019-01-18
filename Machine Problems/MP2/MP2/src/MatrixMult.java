import java.util.*;

public class MatrixMult
{
    public static void main(String[] args)
    {
        ArrayList<ArrayList<Integer>> mat1 = populateMatrix();
        ArrayList<ArrayList<Integer>> mat2 = populateMatrix();

        printMatrix(mat1);
        printMatrix(mat2);

    }

    public static ArrayList<ArrayList<Integer>> populateMatrix()
    {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> inner = new ArrayList<>();

        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                inner.add(genRandNum());
            }
            matrix.add(inner);
        }

        return matrix;
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> matrix)
    {
        // Print the matrix
        for(int i = 0; i < matrix.size(); i++)
        {
            System.out.print("[ ");
            for(int j = 0; j < matrix.get(i).size(); j++)
            {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println("]");
        }
        System.out.println("\n");
    }

    public static int genRandNum()
    {
        // 100 is the maximum and the 1 is our minimum.
        return (int)(Math.random() * 100 + 1);
    }
}
