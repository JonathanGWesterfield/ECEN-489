import java.util.*;
import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String[] args)
    {
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try
        {
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        }
        catch (IOException e)
        {
            System.err.println("\nERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void createConn()
    {

    }
}
