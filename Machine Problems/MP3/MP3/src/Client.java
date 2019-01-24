// import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;

public class Client
{
    BufferedReader reader;
    BufferedWriter writer;
    OutputStreamWriter streamWriter;
    Socket sock;
    String hostName;
    int portNumber;
    String message;

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
        setUpNetworking();
        sendMessage();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }

    public void setUpNetworking()
    {
        try
        {
            this.sock = new Socket(this.hostName, this.portNumber);
            InputStreamReader streamReader = new InputStreamReader(this.sock.getInputStream());
            this.reader = new BufferedReader(streamReader);
            this.streamWriter = new OutputStreamWriter(this.sock.getOutputStream());
            this.writer = new BufferedWriter(this.streamWriter);

            System.out.println("Network Connection Established\n");
        }
        catch (IOException e)
        {
            System.err.println("\nERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendMessage()
    {
        try
        {
            writer.write(message);
            System.out.println("Message Sent!");
            writer.flush();
        }
        catch (IOException e)
        {
            System.err.println("\nERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public class IncomingReader implements Runnable
    {
        public void run()
        {
            String message;
            try
            {
                while((message = reader.readLine()) != null)
                {
                    System.out.println("Read: " + message);
                }
            }
            catch (Exception e)
            {
                System.err.println("\nERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
