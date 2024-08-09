import java.net.*;
import java.io.*;
 

public class MultiClient {
 
    public static void main(String[] args) {

    try  {
 
        Socket socket = new Socket("localhost", 4444); 
            // Wysylanie do serwera
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Odbieranie z serwera
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Console console = System.console();
        String text;

        do {
            text = console.readLine("Enter text: ");

            // Wysylanie do serwera
            out.println(text);
            // Odbieranie z serwera
            System.out.println(in.readLine());
        
        } while (!text.equals("bye"));
        socket.close();
 
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}