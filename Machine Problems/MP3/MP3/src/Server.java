import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Server
{
    private ServerSocket servSock;
    private PrintWriter writer;
    private BufferedReader reader;

    private ArrayList<PrintWriter> clientOutputStreams;

    private int maxNumClients;
    private int numClients;
    private int portNum;
    private String message;

    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("Usage: java Server <port number> <max # clients> <message>");
            System.exit(11);
        }

        int portNum = Integer.parseInt(args[0]);
        int maxNumClients = Integer.parseInt(args[1]);
        String message = args[2];

    }

    public void Server(int portNum, int maxNumClients, String message)
    {
        this.portNum = portNum;
        this.maxNumClients = maxNumClients;
        this.message = message;
        this.numClients = 0;
    }

    public void startServer()
    {
        try
        {
            this.servSock = new ServerSocket(this.portNum);
            while (this.numClients <= this.maxNumClients)
            {
                Socket clientSocket = this.servSock.accept();
                this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
                this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                this.clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();

                System.out.println("Got a connection");
                this.numClients += 1;
            }

            System.out.println("\nExceeded Max Number of Connections!!!");
            System.out.println("Max Number of Connections Allowed: " + this.maxNumClients);
            System.out.println("\nExiting!");
        }
        catch (IOException e)
        {
            System.err.println("\nERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public class ClientHandler implements Runnable
    {
        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSocket)
        {
            try
            {
                this.sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                this.reader = new BufferedReader(isReader);
            }
            catch(Exception e)
            {
                System.err.println("\nERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }

        public void run()
        {
            String message;

            try
            {
                while((message = this.reader.readLine()) != null)
                {
                    System.out.println("Server Read: " + message);
                }
            }
            catch (IOException e)
            {
                System.err.println("\nERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
