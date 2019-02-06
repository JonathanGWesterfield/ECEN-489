import java.lang.*;
import java.io.*;
import java.rmi.ServerError;
import java.util.*;
import java.net.*;

public class Server
{
    private ServerSocket servSock;

    private ArrayList<PrintWriter> clientOutputStreams;

    private int maxNumClients;
    private int numClients;
    private int portNum;
    private String sendMessage;

    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("Usage: java Server <port number> <max # clients> <message>");
            System.exit(11);
        }

        int portNum = Integer.parseInt(args[0]);
        int maxNumClients = Integer.parseInt(args[1]);
        String sendMessage = args[2];

        Server server = new Server(portNum, maxNumClients, sendMessage);
        server.startServer();

    }

    public Server()
    {
        //Empty Default constructor
    }

    public Server(int portNum, int maxNumClients, String sendMessage)
    {
        this.portNum = portNum;
        this.maxNumClients = maxNumClients;
        this.sendMessage = sendMessage;
        this.numClients = 0;
    }

    public void startServer()
    {
        System.out.println("Starting Server\n");
        try
        {
            this.servSock = new ServerSocket(this.portNum);
            while (this.numClients <= this.maxNumClients)
            {
                Socket clientSocket = this.servSock.accept();

                Thread t = new Thread(new ServerThread(clientSocket, sendMessage));
                t.start();

                System.out.println("Got a connection");
                this.numClients += 1;

            }

            System.out.println("\nExceeded Max Number of Connections!!!");
            System.out.println("Max Number of Connections Allowed: " + this.maxNumClients);
            System.out.println("\nExiting!");
            System.exit(-1);
        }
        catch (IOException e)
        {
            System.err.println("Could not listen on port " + this.portNum);
            System.err.println("\nERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
