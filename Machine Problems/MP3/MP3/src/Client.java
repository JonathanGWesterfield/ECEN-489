// import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;

public class Client
{
    private String hostName;
    private int portNumber;
    private String message;

    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("Usage: java Client <hostname> <portNumber> <message>");
            System.exit(11);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String message = args[2];

        Client client = new Client(hostName, portNumber, message);
        client.startClient();
    }

    public Client(String hostName, int portNumber, String message)
    {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.message = message;
    }

    public void startClient()
    {
        System.out.println("Starting Client!");
        try (
                // create socket, reader from server and writer to server
                Socket sock = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        )
        {

            Scanner keyboard = new Scanner(System.in);
            String fromServer;
            String fromUser;

            out.println(message);

            // MUST SEND MESSAGE WITH AN ENDLINE '\n' CHARACTER OR ELSE THE WRITER
            // NEVER CLOSES AND THE SERVER HANGS AND DEADLOCKS
            while ((fromServer = in.readLine()) != null)
            {
                if (fromServer.equalsIgnoreCase("bye"))
                    break;

                System.out.println("Client Read: " + fromServer);

                // read from keyboard to send a user input message back to
                // server after initial response
                System.out.print("Send a message back: ");
                fromUser = keyboard.nextLine();
                if (fromUser != null)
                {
                    System.out.println("Client Sends: " + fromUser );
                    out.println(fromUser);
                }
            }
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
