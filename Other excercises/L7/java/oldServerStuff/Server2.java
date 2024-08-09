import java.io.*;
import java.net.*;
import java.util.ArrayList;
 

public class Server2 {
    private static ArrayList<Node<Integer>> IntTree = new ArrayList<Node<Integer>>();
    private static ArrayList<Node<Double>> DoubTree = new ArrayList<Node<Double>>();
    private static ArrayList<Node<String>> StrTree = new ArrayList<Node<String>>();

    // rozdawanie indeksów
    public static int getNewIndex(MultiThread2.Mode dataType){
        switch(dataType){
            case Int:
                return IntTree.size() - 1;
            case Doub:
                return DoubTree.size() - 1;
            case Str:
                return StrTree.size() - 1;
        }
        return 0;
    }

    // Zwracanie
    public static Node<Integer> getIntTree(int index){
        return IntTree.get(index);
    }
    public static Node<Double> getDoubTree(int index){
        return DoubTree.get(index);
    }
    public static Node<String> getStrTree(int index){
        return StrTree.get(index);
    }

    // Dodawanie do ArrayListy
    public static void addIntTree(Integer value){
        IntTree.add(new Node<Integer>(value));
    }
    public static void addDoubTree(Double value){
        DoubTree.add(new Node<Double>(value));
    }
    public static void addStrTree(String value){
        StrTree.add(new Node<String>(value));
    }

    // insert
    public static void intInsert(int value, int index){
        IntTree.get(index).insert(value);
    }
    public static void doubInsert(double value, int index){
        DoubTree.get(index).insert(value);
    }
    public static void strInsert(String value, int index){
        StrTree.get(index).insert(value);
    }

    // delete
    public static void intDelete(int value, int index){
        IntTree.get(index).delete(value);
    }
    public static void doubDelete(double value, int index){
        DoubTree.get(index).delete(value);
    }
    public static void strDelete(String value, int index){
        StrTree.get(index).delete(value);
    }
    
    // search
    public static Boolean intSearch(int value, int index){
        return IntTree.get(index).search(value);
    }
    public static Boolean doubSearch(double value, int index){
        return DoubTree.get(index).search(value);
    }
    public static Boolean strSearch(String value, int index){
        return StrTree.get(index).search(value);
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444)) {
 
            System.out.println("Server is listening on port 4444");
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
 
                new MultiThread2(socket).start();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}