import java.io.*;
import java.net.*;
import java.lang.*;

public class ServerThread extends Thread
{
    private Socket sock;
    private String sendMessage;

    public ServerThread(Socket clientSocket, String sendMessage)
    {
        super("ServerThread");
        this.sock = clientSocket;
        this.sendMessage = sendMessage;
    }

    public void run()
    {
        String message;

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));
                PrintWriter out = new PrintWriter(this.sock.getOutputStream(), true)
        )
        {
            while((message = reader.readLine()) != null)
            {
                // If client tries to close connection
                if(message.equalsIgnoreCase("bye"))
                    break;

                // read client message and send server message
                // System.out.println("Server Read: " + message);
                System.out.println("IP: " + this.sock.getInetAddress() + "\tPort: " +
                        this.sock.getPort() + "\tMessage: " + message);

                // MUST SEND WITH AN ENDLINE '\n' CHARACTER OR ELSE THE WRITER NEVER CLOSES AND THE
                // CLIENT HANGS AND DEADLOCKS
                writer.write(this.sendMessage + "\n");
                writer.flush();
                System.out.println("Sent Message: " + this.sendMessage + "\n");
            }
            this.sock.close();
        }
        catch (IOException e)
        {
            System.err.println("\nERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}