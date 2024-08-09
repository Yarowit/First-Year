import java.io.*;
import java.net.*;
 

public class MultiThread2 extends Thread {
    public enum Mode{Int,Doub,Str};
    private Mode dataType;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private int index;
 
    public MultiThread2(Socket socket) {
        this.socket = socket;
    }

    private void intTreeCopy(Node<Integer> tree){
        if(tree == null)
            return;
        out.println(tree.getValue());
        System.out.println("Y " + tree.getValue());
        intTreeCopy(tree.getLeft());
        intTreeCopy(tree.getRight());
    }
    private void doubTreeCopy(Node<Double> tree){
        if(tree == null)
            return;
        System.out.println("D " + tree.getValue());
        out.println(tree.getValue());
        doubTreeCopy(tree.getLeft());
        doubTreeCopy(tree.getRight());
    }
    private void strTreeCopy(Node<String> tree){
        if(tree == null)
            return;
        out.println(tree.getValue());
        strTreeCopy(tree.getLeft());
        strTreeCopy(tree.getRight());
    }

    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
            out = new PrintWriter(socket.getOutputStream(), true);
    
            String[] firstCommand = in.readLine().split(" ");
            
            System.out.println(firstCommand[0]);

            switch(firstCommand[0]){
                case "Integer":
                    dataType = Mode.Int;
                    Server2.addIntTree(Integer.parseInt(firstCommand[1]));
                    index = Server2.getNewIndex(dataType);
                    

                    intTreeCopy(Server2.getIntTree(index));

                    out.println("treeEnd");

                    break;
                case "Double":
                    
                    dataType = Mode.Doub;
                    Server2.addDoubTree(Double.parseDouble(firstCommand[1]));
                    index = Server2.getNewIndex(dataType);
                    
                    
                    doubTreeCopy(Server2.getDoubTree(index));
                    // System.out.println(Server2.getDoubTree(index).getValue());
                    
                    out.println("treeEnd");
                    
                    break;
                default:
                    
                    dataType = Mode.Str;
                    Server2.addStrTree(firstCommand[1]);
                    index = Server2.getNewIndex(dataType);
                                       
                    
                    strTreeCopy(Server2.getStrTree(index));
                    
                    out.println("treeEnd");
            }
            

            String line = "";
            
            while (!line.equals("end")){
                line = in.readLine();

                System.out.println(line);

                String[] data = line.split(" ");

                if(data.length != 2){
                    out.println("Wrong structure");
                    continue;
                }
                    

                Object value = data[1];                
                try{
                switch(dataType){
                    case Int:
                        value = Integer.parseInt(data[1]);
                        switch(data[0]){
                            case "insert":
                                Server2.intInsert((Integer) value, index);
                                out.println("inserted " + value);
                                intTreeCopy(Server2.getIntTree(index));
                                out.println("treeEnd");
                                break;
                            case "delete":
                                Server2.intDelete((Integer) value, index);
                                out.println("deleted " + value);
                                intTreeCopy(Server2.getIntTree(index));
                                out.println("treeEnd");
                                break;
                            case "search":
                                out.println(Server2.intSearch((Integer) value, index));
                                break;
                            default:
                                out.println("Wrong command");
                        }
                        break;
                    case Doub:
                        value = Double.parseDouble(data[1]);
                        switch(data[0]){
                            case "insert":
                                Server2.doubInsert((Double) value, index);
                                out.println("inserted " + value);
                                doubTreeCopy(Server2.getDoubTree(index));
                                out.println("treeEnd");
                                break;
                            case "delete":
                                Server2.doubDelete((Double) value, index);
                                out.println("deleted " + value);
                                doubTreeCopy(Server2.getDoubTree(index));
                                out.println("treeEnd");
                                break;
                            case "search":
                                out.println(Server2.doubSearch((Double) value, index));
                                break;
                            default:
                                out.println("Wrong command");
                        }
                        break;
                    case Str:
                        value = data[1];
                        switch(data[0]){
                            case "insert":
                                Server2.strInsert((String) value, index);
                                out.println("inserted " + value);
                                strTreeCopy(Server2.getStrTree(index));
                                out.println("treeEnd");
                                break;
                            case "delete":
                                Server2.strDelete((String) value, index);
                                out.println("deleted " + value);
                                strTreeCopy(Server2.getStrTree(index));
                                out.println("treeEnd");
                                break;
                            case "search":
                                out.println(Server2.strSearch((String) value, index));
                                break;
                            default:
                                out.println("Wrong command");
                        }
                        break;
                } }catch (NumberFormatException ex){out.println("Wrong data");}               
            }
    
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } 
    }
}