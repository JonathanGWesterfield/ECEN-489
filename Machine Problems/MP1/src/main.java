import java.util.*;
import java.io.*;

public class main
{
    private Scanner keyboard;
    private Calculator mycalc;

    public static void main(String[] args)
    {
        System.out.println("Starting Calculator");
        Calculator mycalc = new Calculator();
        intro(mycalc);

        boolean startOver = true;

        while(startOver)
        {
            intro(mycalc);
            startOver = programLoop(mycalc);
        }

    }

    // Set the name
    public static Calculator intro(Calculator mycalc)
    {
        mycalc.setName("Jonathan Westerfield");
        return mycalc;
    }

    public static boolean programLoop(Calculator mycalc)
    {
        boolean quit = false;

        while(!quit)
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter 'A' to Add, 'S' to Subtract, 'M' to Multiply, and 'Q' to quit: ");
            String choice = keyboard.next();

            if (choice.compareToIgnoreCase("Q") == 0) {
                System.out.println("Quitting!!!");
                quit = true;
                break;
            }

            System.out.print("Enter Argument 1: ");
            Float arg1 = getArg();
            System.out.print("Enter Argument 2: ");
            Float arg2 = getArg();

            if (arg1 == (float)-9999999999.0 || arg2 ==  (float)-9999999999.0)
            {
                quit = true;
                return true;
            }

            if (choice.compareToIgnoreCase("A") == 0)
            {
                System.out.println("The Sum of argument 1 and argument 2 is the answer: " + mycalc.Addition(arg1, arg2));
            }
            else if (choice.compareToIgnoreCase("S") == 0)
            {
                System.out.println("The Difference of argument 1 and argument 2 is the answer: " + mycalc.Subtraction(arg1, arg2));
            }
            else if (choice.compareToIgnoreCase("M") == 0)
            {
                System.out.println("The Difference of argument 1 and argument 2 is the answer: " + mycalc.Multiplication(arg1, arg2));
            }
        }
        return false;
    }

    public static Float getArg()
    {
        try
        {
            Scanner keyboard = new Scanner(System.in);
            return keyboard.nextFloat();
        }
        catch (Exception e)
        {
            return (float)-9999999999.0;
        }
    }
}