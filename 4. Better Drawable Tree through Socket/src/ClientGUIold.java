import java.io.*;
import java.net.*;

class ClientGUI  {


  public static void main(String[] args){

    try {
      Socket socket = new Socket("localhost", 4444);
  
      InputStream input = socket.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(input));

      handshakeGUI first = new handshakeGUI(socket);
      
      System.out.println("2");
      // try{first.init();}catch(Exception e){}
      String mode = in.readLine();
      treeGUI second = new treeGUI(socket, mode);
    }
    catch (UnknownHostException e) {
       System.out.println("Unknown host: localhost"); System.exit(1);
     }
     catch  (IOException e) {
       System.out.println("No I/O"); System.exit(1);
     } 
  }
}
