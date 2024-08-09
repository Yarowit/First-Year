import java.io.*;
import java.net.*;

class Client  {


  public static void main(String[] args){

    try {
      Socket socket = new Socket("localhost", 4444);
  
      OutputStream output = socket.getOutputStream();
      PrintWriter out = new PrintWriter(output, true);

      InputStream input = socket.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(input));

      String line;
	  //Handshake
	  line = System.console().readLine("Enter Type: ");
	  // Wysylanie do serwera
	  out.println(line);
      while(true){
          line = System.console().readLine("Enter command: ");
          // Wysylanie do serwera
          out.println(line);
           // Odbieranie z serwera
			if(line.equals("exit"))
				break;

          System.out.println(in.readLine());

		  //DrawYes // DrawNo
		  
		  if(in.readLine().equals("drawYes")){
			  do{
				line = in.readLine();
				System.out.println(line);
			  }while(! line.equals("drawEnd"));
		  }
        
      }
      socket.close();

    }
    catch (UnknownHostException e) {
       System.out.println("Unknown host: localhost"); System.exit(1);
     }
     catch  (IOException e) {
       System.out.println("No I/O"); System.exit(1);
     } 
  }
}
