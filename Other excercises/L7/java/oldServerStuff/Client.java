
import java.net.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.lang.System.Logger.Level;
 

public class Client extends Application{
    private static MultiThread2.Mode dataType;
    static Pane pane = new Pane();
    private BorderPane root = new BorderPane();
    private static Node<Integer> intTree = new Node<Integer>(0);
    private static Node<Double> doubTree = new Node<Double>(0.0);
    private static Node<String> strTree = new Node<String>("");
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private TextField console = new TextField();

    private void generateIntTree(){
        try{
            String line = in.readLine();
            intTree = new Node<Integer>(Integer.parseInt(line));
            while(true){
                line = in.readLine();
                System.out.println("T " + line);
                if(line.equals("treeEnd")){
                    break;
                }
                intTree.insert(Integer.parseInt(line));

            }
        }catch(IOException ex){}

    }
    private void generateDoubTree(){
        try{
            String line = in.readLine();
            System.out.println("Lin: " + line);
            doubTree = new Node<Double>(Double.parseDouble(line));
            while(true){
                line = in.readLine();
                if(line.equals("treeEnd")){
                    break;
                }
                doubTree.insert(Double.parseDouble(line));

            }
        }catch(IOException ex){}

    }
    private void generateStrTree(){
        try{
            String line = in.readLine();
            strTree = new Node<String>(line);
            while(true){
                line = in.readLine();
                if(line.equals("treeEnd")){
                    break;
                }
                strTree.insert(line);

            }
        }catch(IOException ex){}

    }

    private static void intDraw(){
        pane.getChildren().clear();
        System.out.println("H "+intTree.getHeight());
        recIntDraw(intTree, intTree.getHeight(), pane.getWidth()/2.0, 1,0);
    }
    private static void doubDraw(){
        pane.getChildren().clear();
        recDoubDraw(doubTree, doubTree.getHeight(), pane.getWidth()/2.0, 1);
    }
    private static void strDraw(){
        pane.getChildren().clear();
        recStrDraw(strTree, strTree.getHeight(), pane.getWidth()/2.0, 1);
    }

    private static void recIntDraw(Node<Integer> node, double treeHeight, double currWidth, double level,double LineX){
        if(node == null)
            return;
        System.out.println("W "+currWidth+ " H "+pane.getHeight()/treeHeight * (level-0.5) + " v "+ node.getValue().toString());
        Text child = new Text( currWidth, pane.getHeight()/treeHeight * (level - 0.5) , node.getValue().toString());

        if(level != 1){
            Line line = new Line(LineX,pane.getHeight()/treeHeight * (level - 1.5),currWidth,pane.getHeight()/treeHeight * (level - 0.5) - 10);
            pane.getChildren().add(line);
        }

        pane.getChildren().add(child);

        recIntDraw(node.getLeft(),  treeHeight, currWidth - (pane.getWidth()/2.0)/(Math.pow(2,level)), level + 1,currWidth);
        recIntDraw(node.getRight(), treeHeight, currWidth + (pane.getWidth()/2.0)/(Math.pow(2,level)), level + 1,currWidth);
    }
    private static void recDoubDraw(Node<Double> node, double treeHeight, double currWidth, double level){
        if(node == null)
            return;

        Text child = new Text( currWidth, pane.getHeight()/treeHeight * (level - 0.5) , node.getValue().toString());
        pane.getChildren().add(child);

        recDoubDraw(node.getLeft(),  treeHeight, currWidth - (pane.getWidth()/2.0)/(Math.pow(2,level)), level + 1);
        recDoubDraw(node.getRight(), treeHeight, currWidth + (pane.getWidth()/2.0)/(Math.pow(2,level)), level + 1);
    }
    private static void recStrDraw(Node<String> node, double treeHeight, double currWidth, double level){
        if(node == null)
            return;

        Text child = new Text( currWidth, pane.getHeight()/treeHeight * (level - 0.5) , node.getValue());
        pane.getChildren().add(child);

        recStrDraw(node.getLeft(),  treeHeight, currWidth - (pane.getWidth()/2.0)/(Math.pow(2,level)), level + 1);
        recStrDraw(node.getRight(), treeHeight, currWidth + (pane.getWidth()/2.0)/(Math.pow(2,level)), level + 1);
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {        
        stage.setTitle("Tree");

        console.setEditable(false);
        
        TextField textField = new TextField();
        
        ToggleButton IntegerMode = new ToggleButton("Integer");
        ToggleButton DoubleMode = new ToggleButton("Double");
        ToggleButton StringMode = new ToggleButton("String");

        HBox buttons = new HBox(textField, IntegerMode, DoubleMode, StringMode);

        root.setCenter(pane);
        root.setTop(buttons);
            
        Scene scene = new Scene(root,600,500);                
        stage.setScene(scene);
        stage.show();
        // System.out.println("X " + pane.getWidth()+" H "+ pane.getHeight());

        try {
            socket = new Socket("localhost", 4444); 
            
            out = new PrintWriter(socket.getOutputStream(), true);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
    
        } catch (IOException ex) {
            System.out.println("In not found: " + ex.getMessage());
    
        }

        IntegerMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                
                if(textField.getText() == "")
                    out.println("Integer 0");
                else
                    out.println("Integer " + textField.getText());

                dataType = MultiThread2.Mode.Int;
                textField.setText("");

                generateIntTree();
                intDraw();
                setEventListener(textField);
            }
        });
        DoubleMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                
                if(textField.getText() == "")
                    out.println("Double 0.0");
                else
                    out.println("Double " + textField.getText());

                dataType = MultiThread2.Mode.Doub;
                textField.setText("");
                
                generateDoubTree();
                doubDraw();
                setEventListener(textField);
                
            }
        });
        StringMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                
                if(textField.getText() == "")
                    out.println("String 0");
                else
                    out.println("String " + textField.getText());

                dataType = MultiThread2.Mode.Str;
                textField.setText("");
                
                generateStrTree();
                strDraw();
                setEventListener(textField);
                
            }
        });
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                out.println("end");
                try{
                    socket.close();
                }catch(IOException ex){};
                Platform.exit();
                System.exit(0);
            }
        });

        ChangeListener<Number> listener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                switch(dataType){
                    case Int:
                        intDraw();
                        break;
                    case Doub:
                        doubDraw();
                        break;
                    case Str:
                        strDraw();
                        break;
                }
            }
        };

        scene.heightProperty().addListener(listener);
        scene.widthProperty().addListener(listener);
        
    }

    private void setEventListener(TextField textField){
        root.setTop(new HBox(textField, console));

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {
                try{
                    String text = textField.getText();
                    if(text == "end"){
                        socket.close();
                        textField.setOnAction(null);
                        return;
                    }
                    
                    out.println(text);
                    
                    
                    String command = text.split(" ")[0];

                    String result = in.readLine();
                    System.out.println("Preresult: " + result);
                    if( result.equals("Wrong command") || 
                        result.equals("Wrong structure") || 
                        result.equals("Wrong data") ){

                        textField.setText("");
                        console.setText(result);
                    }else{
                        if(command.equals("delete") || command.equals("insert")){
                            switch(dataType){
                                case Int:
                                    generateIntTree();
                                    intDraw();
                                    break;
                                case Doub:
                                    generateDoubTree();
                                    doubDraw();
                                    break;
                                case Str:
                                    generateStrTree();
                                    strDraw();
                                    break;
                            }
                        }
                        textField.setText("");
                        console.setText(result);
                    }
                } catch (UnknownHostException ex) {
                    System.out.println("Server not found: " + ex.getMessage());
            
                } catch (IOException ex) {
                    System.out.println("I/O error: " + ex.getMessage());
                }
            }
        });
        
    }
}





        /*
        try  {

            Socket socket = new Socket("localhost", 4444); 
                // Wysylanie do serwera
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Console console = System.console();
            // String text = "Integer 5";
            // out.println(text);
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

            textField.setText(text);
            // index = Integer.parseInt(text);
            index = 0;
            do {
                // text = console.readLine("Enter text: ");
                text = textField.getText();

                // Wysylanie do serwera
                out.println(text);
                // Odbieranie z serwera
                System.out.println(in.readLine());
                String command = text.split(" ")[0];
                if(command == "delete" || command == "insert"){
                    switch(dataType){
                        case Int:
                            intDraw(Server2.getIntTree(index));
                        case Doub:
                            doubDraw(Server2.getDoubTree(index));
                        case Str:
                            strDraw(Server2.getStrTree(index));

                    }
                }
                
            } while (!text.equals("end"));
            textField.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    try{
                        String text = textField.getText();

                        // Wysylanie do serwera
                        out.println(text);
                        // Odbieranie z serwera
                        System.out.println(in.readLine());
                        String command = text.split(" ")[0];
                        if(command == "delete" || command == "insert"){
                            switch(dataType){
                                case Int:
                                    intDraw(Server2.getIntTree(index));
                                case Doub:
                                    doubDraw(Server2.getDoubTree(index));
                                case Str:
                                    strDraw(Server2.getStrTree(index));

                            }
                        }
                    }} catch (UnknownHostException ex) {
                System.out.println("Server not found: " + ex.getMessage());
        
            } catch (IOException ex) {
                System.out.println("I/O error: " + ex.getMessage());
            }
                
            });

            socket.close();
        
            } catch (UnknownHostException ex) {
                System.out.println("Server not found: " + ex.getMessage());
        
            } catch (IOException ex) {
                System.out.println("I/O error: " + ex.getMessage());
            }
            */