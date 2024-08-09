import java.io.*;
import java.net.*;

class HandshakeServer {
	private static TreeServer<?> ts;
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(4444)) {

			System.out.println("Server is listening on port 4444");

			Socket socket = serverSocket.accept();

			InputStream input = socket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			PrintWriter out = new PrintWriter(output, true);

			String line = in.readLine();
			switch(line){
				case "Integer":
					ts = new TreeServer<Integer>(new Tree<Integer>(), new ParseT<Integer>(new ParserI()), in, out);
					break;
				case "Double":
					ts = new TreeServer<Double>(new Tree<Double>(), new ParseT<Double>(new ParserD()), in, out);
					break;
				case "String":
					ts = new TreeServer<String>(new Tree<String>(), new ParseT<String>(new ParserS()), in, out);
					break;
				default:
					socket.close();
					return;
			}

			ts.run();
			socket.close();
				
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
