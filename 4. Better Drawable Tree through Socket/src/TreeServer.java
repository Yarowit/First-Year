import java.io.*;

public class TreeServer<T extends Comparable<T>> {
    Tree<T> t;
    ParseT<T> p;
    BufferedReader in;
    PrintWriter out;

    TreeServer(Tree<T> t, ParseT<T> p, BufferedReader in, PrintWriter out){
        this.t = t;
        this.p = p;
        this.in = in;
        this.out = out;
    }

    public void run(){
        while(true){
            try{
                String line = in.readLine(); 
                if(line.equals("exit"))
                    return;
                String[] commands = line.split(" ");

                if(commands.length < 2){
                    out.println("wrong command");
                    out.println("drawNo");
                    continue;
                }

                T value = p.parse(commands[1]);

                switch(commands[0]){
                    case "insert":
                        t.insert(value);
                        out.println("inserted " + value);
                        out.println("drawYes");
                        out.println(t.draw());
                        // System.out.println(t.draw());
                        out.println("drawEnd");
                        break;
                    case "delete":
                        t.delete(value);
                        out.println("deleted " + value);
                        out.println("drawYes");
                        out.println(t.draw());
                        // System.out.println(t.draw());
                        out.println("drawEnd");
                        break;
                    case "search":
                        out.println("found? " + t.search(value));
                        out.println("drawNo");
                        break;
                    default:
                        out.println("wrong command");
                        out.println("drawNo");
                }
            }catch(IOException ex){}
        }
    }
}
