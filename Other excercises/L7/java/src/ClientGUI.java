import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
 

public class IGUI extends Application{            
    private static PrintWriter out;
    private static BufferedReader in;
    private static Socket socket;
    private static TextField console = new TextField();
    private static Label log = new Label();
    private static Text display = new Text();

    public static void main(String[] args){
        try{
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException ex){}
        launch();
    }

    @Override
    public void start(Stage stage) {        
        stage.setTitle("BST");
        
        Button iBut = new Button("Integer");
        Button dBut = new Button("Double");
        Button sBut = new Button("String");


        iBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                out.println("Integer");
                change(stage,"Integer");
            }
        });

        dBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                out.println("Double");
                change(stage,"Double");
            }
        });

        sBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                out.println("String");
                change(stage,"String");
            }
        });

        HBox buttons = new HBox(iBut, dBut, sBut);

        Scene scene = new Scene(buttons,158,25);                
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Exit");
                out.println("exit");
                try{socket.close();}catch(IOException ex){};
                stage.close();
            }
        });

        console.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {
                try{
                    String line = console.getText();
                    
                    out.println(line);
                    
                    if(line.equals("exit")){
                        out.println("exit");
                        try{socket.close();}catch(IOException ex){};
                        stage.close();
                        return;
                    }
        
                    log.setText(in.readLine());
        
                    //DrawYes // DrawNo
                    
                    if(in.readLine().equals("drawYes")){
                        String tree = "";
                        while(true){
                            line = in.readLine();
                            if(line.equals("drawEnd"))
                                break;
                            tree += line + "\n";
                        }
                        display.setText(tree);
                    }
                    
                }catch (IOException ex){System.out.println("Server disconected"); stage.close();}
            }
        });
    }

    void change(Stage stage, String mode){
        Scene scene = new Scene(new VBox(console,log,display),600,200);
        stage.setScene(scene);
        log.setText(mode);
        display.setText("--");
    }
}