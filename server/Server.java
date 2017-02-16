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

public class Server {
private static Socket socket;

    public static void main(String[] args)
    {
        try
        {
 
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");
 
            //Server is running always. This is done using this while(true) loop
            int counter = 0;
            socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
 
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
                
            while(true)
            {
                //Reading the message from the client
                String client_message = br.readLine();
                System.out.println("Message received from client is "+ client_message);
                String returnMessage = "PONG";
                if (counter == 20)
                    returnMessage = "stop";

                String sendreturnMessage = returnMessage + "\n";
		//Sending the response back to the client.

                bw.write(sendreturnMessage);
                bw.flush();
                System.out.println("Message sent to the client is "+returnMessage);
		System.out.println("\nProcess Complete\n");
                counter++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}
