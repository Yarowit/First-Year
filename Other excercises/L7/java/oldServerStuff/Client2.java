
import java.net.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
 

public class Client2 extends Application{
    private static int index;
    private static MultiThread2.Mode dataType;
    static Pane pane = new Pane();
    // private enum Mode{i,d,s};
    // private Mode mode = Mode.i;


    private static void intDraw(Node<Integer> tree){
        pane.getChildren().clear();
        recIntDraw(tree, tree.getHeight(), pane.getWidth()/2.0, 0);
    }
    private static void doubDraw(Node<Double> tree){
        pane.getChildren().clear();
        recDoubDraw(tree, tree.getHeight(), pane.getWidth()/2.0, 0);
    }
    private static void strDraw(Node<String> tree){
        pane.getChildren().clear();
        recStrDraw(tree, tree.getHeight(), pane.getWidth()/2.0, 0);
    }

    private static void recIntDraw(Node<Integer> node, double treeHeight, double currWidth, double level){
        if(node == null)
            return;

        Text child = new Text( currWidth, pane.getHeight()/treeHeight * level , node.getValue().toString());
        pane.getChildren().add(child);

        recIntDraw(node.getLeft(),  treeHeight, currWidth - pane.getWidth()/2.0/level, level + 1);
        recIntDraw(node.getRight(), treeHeight, currWidth + pane.getWidth()/2.0/level, level + 1);
    }
    private static void recDoubDraw(Node<Double> node, double treeHeight, double currWidth, double level){
        if(node == null)
            return;

        Text child = new Text( currWidth, pane.getHeight()/treeHeight * level , node.getValue().toString());
        pane.getChildren().add(child);

        recDoubDraw(node.getLeft(),  treeHeight, currWidth - pane.getWidth()/2.0/level, level + 1);
        recDoubDraw(node.getRight(), treeHeight, currWidth + pane.getWidth()/2.0/level, level + 1);
    }
    private static void recStrDraw(Node<String> node, double treeHeight, double currWidth, double level){
        if(node == null)
            return;

        Text child = new Text( currWidth, pane.getHeight()/treeHeight * level , node.getValue());
        pane.getChildren().add(child);

        recStrDraw(node.getLeft(),  treeHeight, currWidth - pane.getWidth()/2.0/level, level + 1);
        recStrDraw(node.getRight(), treeHeight, currWidth + pane.getWidth()/2.0/level, level + 1);
    }

    public static void main(String[] args) {
        // launch(args);
    try  {
 
        Socket socket = new Socket("localhost", 4444); 
            // Wysylanie do serwera
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Odbieranie z serwera
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Console console = System.console();
        String text = console.readLine("Enter data type: ");
        out.println(text);
        switch(text.split(" ")[0]){
            case "Integer":
                dataType = MultiThread2.Mode.Int;
                break;
            case "Double":
                dataType = MultiThread2.Mode.Doub;
                break;
            default:
                dataType = MultiThread2.Mode.Str;
        }
        text = in.readLine();

        System.out.println(text);
        // index = Integer.parseInt(text);
        index = 0;
        do {
            text = console.readLine("Enter text: ");

            // Wysylanie do serwera
            out.println(text);
            // Odbieranie z serwera
            System.out.println(in.readLine());
            String command = text.split(" ")[0];
            // if(command == "delete" || command == "insert"){
            //     switch(dataType){
            //         case Int:
            //             intDraw(Server2.getIntTree(index));
            //         case Doub:
            //             doubDraw(Server2.getDoubTree(index));
            //         case Str:
            //             strDraw(Server2.getStrTree(index));

            //     }
            // }
            
        } while (!text.equals("end"));
        socket.close();
 
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {        

        stage.setTitle("Tree");
        
        TextField textField = new TextField();
        
        
        // when enter is pressed
       
        ToggleButton IntegerMode = new ToggleButton("Integer");
        ToggleButton DoubleMode = new ToggleButton("Double");
        ToggleButton StringMode = new ToggleButton("String");
        ToggleGroup group = new ToggleGroup();
        IntegerMode.setToggleGroup(group);
        DoubleMode.setToggleGroup(group);
        StringMode.setToggleGroup(group);
        
        

        // IntegerMode.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent actionEvent) {
        //         mode = Mode.i;
        //     }
        // });
        // DoubleMode.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent actionEvent) {
        //         mode = Mode.d;
        //     }
        // });
        // StringMode.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent actionEvent) {
        //         mode = Mode.s;
        //     }
        // });

        HBox buttons = new HBox(textField, IntegerMode, DoubleMode, StringMode);

        // BorderPane root = new BorderPane(buttons, textField, null, null, pane);
        BorderPane root = new BorderPane();
        // root.setTop(textField);
        root.setCenter(pane);
        root.setTop(buttons);
            
            
            
            
        Scene scene = new Scene(root,600,500);                
        stage.setScene(scene);
        stage.show();
        
        /*
        //******************************************************************* /
        Node<Integer> tree = new Node<Integer>(10);

        // tree.insert(10);
        // System.out.println(tree.left.value);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(7);
        tree.insert(12);
        tree.insert(17);
        tree.insert(3);
        tree.insert(6);
        // tree.print("");
        tree.draw();
        
        System.out.println("Koniec");
        
        
        textField.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println(textField.getText());
                Action(textField.getText(),tree);
                pane.getChildren().clear();
                draw(pane, tree, 1, pane.getWidth()/2);
            }
        });
        */
        
    }
}