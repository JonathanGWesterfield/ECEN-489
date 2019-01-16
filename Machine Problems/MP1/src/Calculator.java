public class Calculator
{
    private String Name;

    public String getName()
    {
        return this.Name;
    }

    public void setName(String N)
    {
        this.Name = N;
        System.out.println("Welcome to the Calculator designed by " + this.Name + ".");
    }

    public Float Addition(Float A, Float B)
    {
        return (A + B);
    }

    public Float Subtraction(Float A, Float B)
    {
        return (A - B);
    }

    public Float Multiplication(Float A, Float B)
    {
        return (A * B);
    }
}