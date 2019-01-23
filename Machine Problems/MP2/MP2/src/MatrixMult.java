import java.lang.reflect.Array;
import java.rmi.activation.ActivationGroup_Stub;
import java.util.*;

public class MatrixMult
{
    public static void main(String[] args)
    {
        ArrayList<ArrayList<Integer>> mat1;
        ArrayList<ArrayList<Integer>> mat2;
        ArrayList<ArrayList<Integer>> resultMat;

        boolean runSmallMatrix = getSizeChoice();
        boolean runMultThread = getThreadChoice();
        if(runSmallMatrix)
        {
            mat1 = populateMiniMatrix();
            mat2 = populateMiniMatrix();

            System.out.println("Matrix 1: ");
            printMatrix(mat1);

            System.out.println("Matrix 2: ");
            printMatrix(mat2);

            mat2 = rotateMatrix(mat2);
            System.out.println("Rotated Matrix 2: ");
            printMatrix(mat2);

            // System.out.println(runMultThread);
            if(runMultThread)
            {
                System.out.println("Multithreaded Matrix Operations");
                resultMat = miniMatrixOps(mat1, mat2);
            }
            else
            {
                System.out.println("Single Threaded Matrix Operations");
                resultMat = singleThreadMatrixOps(mat1, mat2);
            }
        }
        else
        {
            mat1 = populateMatrix();
            mat2 = populateMatrix();

            System.out.println("Matrix 1: ");
            printMatrix(mat1);

            System.out.println("Matrix 2: ");
            printMatrix(mat2);

            mat2 = rotateMatrix(mat2);
            System.out.println("Rotated Matrix 2: ");
            printMatrix(mat2);

            // System.out.println(runMultThread);
            if(runMultThread)
            {
                ArrayList<ArrayList<ArrayList<Integer>>> argMatrix = argMatrixI(mat1);
                // print3DMatrix(argMatrix);

                System.out.println("Multithreaded Matrix Operations");
                resultMat = matrixOps(argMatrix, mat2);
            }
            else
            {
                System.out.println("Single Threaded Matrix Operations");
                resultMat = singleThreadMatrixOps(mat1, mat2);
            }
        }

        System.out.println("\nResult Matrix: ");
        printMatrix(resultMat);

        // TODO: Used 3d matrix to send corresponding rows to the threads

    }

    public static boolean getSizeChoice()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Populate Miniature Matrix? (y/n)");
        String choice = keyboard.next();

        if(choice.compareToIgnoreCase("y") == 0)
        {
            System.out.println("Using Smaller Matrix!!!");
            return true;
        }
        else if(choice.compareToIgnoreCase("n") == 0)
        {
            System.out.println("Using Normal 20x20 Matrix");
        }
        else
        {
            System.out.println("Choice is not a listed option\nUsing Normal 20x20 Matrix\n");
        }
        return false;
    }

    public static boolean getThreadChoice()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Run Matrix Operations Multithreaded? (y/n)");
        String choice = keyboard.next();

        if(choice.compareToIgnoreCase("y") == 0)
        {
            System.out.println("Running Matrix Operations Multithreaded!!!");
            return true;
        }
        else if(choice.compareToIgnoreCase("n") == 0)
        {
            System.out.println("Running Matrix Operations Single Threaded");
        }
        else
        {
            System.out.println("Choice is not a listed option\nRunning Matrix Operations Single Threaded\n");
        }
        return false;
    }

    public static ArrayList<ArrayList<Integer>> singleThreadMatrixOps(ArrayList<ArrayList<Integer>> mat1,
                                                                      ArrayList<ArrayList<Integer>> mat2)
    {
        try
        {
            MatrixOps matOps = new MatrixOps(mat1, mat2);

            matOps.start();

            Thread.sleep(2000);

            return matOps.getResultMat();
        }
        catch (Exception e)
        {
            System.out.println("ERROR!!! \n" + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<ArrayList<Integer>> miniMatrixOps(ArrayList<ArrayList<Integer>> mat1,
                                                              ArrayList<ArrayList<Integer>> mat2)
    {
        ArrayList<ArrayList<Integer>> argMat1 = new ArrayList<ArrayList<Integer>>();
        argMat1.add(mat1.get(0));
        // System.out.println("First Arg: ");
        // printMatrix(argMat1);

        ArrayList<ArrayList<Integer>> argMat2 = new ArrayList<ArrayList<Integer>>();
        argMat2.add(mat1.get(1));
        // System.out.println("Second Arg: ");
        // printMatrix(argMat2);

        ArrayList<ArrayList<Integer>> argMat3 = new ArrayList<ArrayList<Integer>>();
        argMat3.add(mat1.get(2));
        // System.out.println("Third Arg: ");
        // printMatrix(argMat3);

        try
        {
            MatrixOps matOps1 = new MatrixOps(argMat1, mat2);
            MatrixOps matOps2 = new MatrixOps(argMat2, mat2);
            MatrixOps matOps3 = new MatrixOps(argMat3, mat2);

            Thread matrixOps1 = new Thread(matOps1);
            Thread matrixOps2 = new Thread(matOps2);
            Thread matrixOps3 = new Thread(matOps3);

            matrixOps1.start();
            matrixOps2.start();
            matrixOps3.start();

            matrixOps1.join();
            matrixOps2.join();
            matrixOps3.join();

            return miniStitchTogether(matOps1.getResultMat(), matOps2.getResultMat(), matOps3.getResultMat());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage() + "\n");
        }

        return null;
    }

    public static ArrayList<ArrayList<Integer>> matrixOps(ArrayList<ArrayList<ArrayList<Integer>>> mat1,
                                                          ArrayList<ArrayList<Integer>> mat2)
    {
        ArrayList<MatrixOps> matOps = new ArrayList<MatrixOps>();
        ArrayList<Thread> threads = new ArrayList<Thread>();

        try
        {
            // Create MatrixOps objects
            for(int i = 0; i < 5; i++)
            {
                matOps.add(new MatrixOps(mat1.get(i), mat2));
            }

            // Create threads
            for(int i = 0; i < 5; i++)
            {
                threads.add(new Thread(matOps.get(i)));
            }

            // Start Threads
            for(int i = 0; i < 5; i++)
            {
                threads.get(i).start();
            }

            // Join Threads
            for(int i = 0; i < 5; i++)
            {
                threads.get(i).join();
            }

            // piece together all of the matrices that were returned back into one matrix
            return stitchTogether(matOps.get(0).getResultMat(), matOps.get(1).getResultMat(), matOps.get(2).getResultMat(),
                    matOps.get(3).getResultMat(), matOps.get(4).getResultMat());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage() + "\n");
        }

        return null;
    }

    /* public static ArrayList<ArrayList<Integer>> matrixOps(ArrayList<ArrayList<ArrayList<Integer>>> mat1,
                                                          ArrayList<ArrayList<Integer>> mat2)
    {
        ArrayList<MatrixOps> matOps = new ArrayList<>();

        try
        {
            // Create threads
            for(int i = 0; i < 5; i++)
            {
                matOps.add(new MatrixOps(mat1.get(i), mat2));
            }

            // Start Threads
            for(int i = 0; i < 5; i++)
            {
                matOps.get(i).start();
            }

            Thread.sleep(1000);

            return stitchTogether(matOps.get(0).getResultMat(), matOps.get(1).getResultMat(), matOps.get(2).getResultMat(),
                    matOps.get(3).getResultMat(), matOps.get(4).getResultMat());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage() + "\n");
        }

        return null;
    } */

    public static ArrayList<ArrayList<Integer>> miniStitchTogether(ArrayList<ArrayList<Integer>> mat1, ArrayList<ArrayList<Integer>> mat2,
                                                               ArrayList<ArrayList<Integer>> mat3)
    {
        ArrayList<ArrayList<Integer>> newMat = new ArrayList<ArrayList<Integer>>();


        for(int i = 0; i < mat1.size(); i++)
        {
            newMat.add(mat1.get(i));
        }

        for(int i = 0; i < mat2.size(); i++)
        {
            newMat.add(mat2.get(i));
        }

        for(int i = 0; i < mat3.size(); i++)
        {
            newMat.add(mat3.get(i));
        }

        return newMat;
    }

    // Stitches the returned 4x20 matrices back together to make a 20x20 matrix again
    public static ArrayList<ArrayList<Integer>> stitchTogether(ArrayList<ArrayList<Integer>> mat1, ArrayList<ArrayList<Integer>> mat2,
                                                        ArrayList<ArrayList<Integer>> mat3, ArrayList<ArrayList<Integer>> mat4,
                                                        ArrayList<ArrayList<Integer>> mat5)
    {
        ArrayList<ArrayList<Integer>> newMat = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < mat1.size(); i++)
        {
            newMat.add(mat1.get(i));
        }

        for(int i = 0; i < mat2.size(); i++)
        {
            newMat.add(mat2.get(i));
        }

        for(int i = 0; i < mat3.size(); i++)
        {
            newMat.add(mat3.get(i));
        }

        for(int i = 0; i < mat4.size(); i++)
        {
            newMat.add(mat4.get(i));
        }

        for(int i = 0; i < mat5.size(); i++)
        {
            newMat.add(mat5.get(i));
        }

        return newMat;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> argMatrixI(ArrayList<ArrayList<Integer>> mat)
    {
        ArrayList<ArrayList<ArrayList<Integer>>> argMatrix = new ArrayList<ArrayList<ArrayList<Integer>>>();
        ArrayList<ArrayList<Integer>> newRow;

        int index = 0;

        for(int i = 0; i < 5; i++)
        {
            newRow = new ArrayList<ArrayList<Integer>>();

            for (int j = 0; j < 4; j++)
            {
                newRow.add(mat.get(index));
                index++;
            }

            argMatrix.add(newRow);
        }

        return argMatrix;
    }

    public static void print3DMatrix(ArrayList<ArrayList<ArrayList<Integer>>> argMatrix)
    {
        System.out.println("Printing 3D Arg matrix: ");
        for(int i = 0; i < argMatrix.size(); i++)
        {
            for(int j = 0; j < argMatrix.get(i).size(); j++)
            {
                for(int k = 0; k < argMatrix.get(i).get(j).size(); k++)
                {
                    System.out.print("Matrix: " + i + " Row " + j+ " size: " + argMatrix.get(i).get(j).get(k) + " || ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static ArrayList<ArrayList<Integer>> populateMiniMatrix()
    {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> inner;

        for(int i = 0; i < 3; i++)
        {
            inner = new ArrayList<>();

            for(int j = 0; j < 3; j++)
            {
                inner.add(genRandNum());
            }
            matrix.add(inner);
        }

        return matrix;
    }

    public static ArrayList<ArrayList<Integer>> populateMatrix()
    {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> inner;

        for(int i = 0; i < 20; i++)
        {
            inner = new ArrayList<>();

            for(int j = 0; j < 20; j++)
            {
                inner.add(genRandNum());
            }
            matrix.add(inner);
        }

        return matrix;
    }

    public static ArrayList<ArrayList<Integer>> rotateMatrix(ArrayList<ArrayList<Integer>> mat)
    {
        ArrayList<ArrayList<Integer>> newMat = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> newRow;

        for(int i = 0; i < mat.size(); i++)
        {
            newRow = new ArrayList<Integer>();

            for(int j = 0; j < mat.get(i).size(); j++)
            {
                newRow.add(mat.get(j).get(i));
            }

            newMat.add(newRow);
        }

        return newMat;
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
        return (int)(Math.random() * 5 + 1);
    }
}
