import java.io.*;
import java.net.*;

class SocketServer {
  
  public static void main(String[] args) {
    
    try (ServerSocket serverSocket = new ServerSocket(4444)) {
 
      System.out.println("Server is listening on port 4444");

      Socket socket = serverSocket.accept();
  
      //Odbieranie od socketa
      InputStream input = socket.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(input));
  
      //Wysylanie do socketa
      OutputStream output = socket.getOutputStream();
      PrintWriter out = new PrintWriter(output, true);
  
      String line;
      do {
          // Odbieranie od socketa
          line = in.readLine();
          // Wypisywanie na serwerze
          System.out.println(line);       
          // Wysylanie do socketa
          out.println("-> ("+line+")");
  
          } while (!line.equals("bye"));
  
      socket.close();
           
  } catch (IOException ex) {
      System.out.println("Server exception: " + ex.getMessage());
      ex.printStackTrace();
  }
}
}
