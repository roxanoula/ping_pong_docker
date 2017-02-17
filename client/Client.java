import java.lang.Object;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Client {

private static Socket socket;

public static void main(String args[])
{
    try
    {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("Connecting to server on host: " + host + " and port: " + port);
        InetAddress address = InetAddress.getByName(host);
        socket = new Socket(address, port);

        //Send the message to the server
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
       
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String clientMessage = "PING";
        String serverMessage = "PONG";

        while (serverMessage.equals("PONG"))
        {
           bw.write(clientMessage + "\n");
           bw.flush();
           System.out.println("Message sent to the server : "+ clientMessage);

           //Get the return message from the server

           serverMessage = br.readLine();
           System.out.println("Message received from the server : " + serverMessage);
        }
    }
    catch (Exception exception) { exception.printStackTrace();}
    finally
    {
        //Closing the socket
        try
        {
            socket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
}
