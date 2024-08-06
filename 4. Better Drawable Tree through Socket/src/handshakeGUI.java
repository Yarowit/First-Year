import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
 

public class handshakeGUI extends Application{            
    private static PrintWriter out;
    private static Socket socket;

    // handshakeGUI(Socket socket){
    //     super();
    //     System.out.println("1");
    //     this.socket = socket;
    //     try{
    //         this.out = new PrintWriter(socket.getOutputStream(), true);
    //     }catch(IOException ex){}
        
    //     // launch();
    // }
    // @Override
    // public void init() throws Exception {
    //     System.out.println("second");
    //     super.init();
    // }
    public static void main(String[] args){
        try{
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException ex){}
    }
    @Override
    public void start(Stage stage) {        
        stage.setTitle("Choose Type");
        
        Button iBut = new Button("Integer");
        Button dBut = new Button("Double");
        Button sBut = new Button("String");

        iBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                

            }
        });
        dBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                out.println("Double");
                stage.close();
            }
        });
        sBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                out.println("String");
                stage.close();
            }
        });

        HBox buttons = new HBox(iBut, dBut, sBut);

        Scene scene = new Scene(buttons,600,300);                
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                out.println("Integer");
                out.println("exit");
                try{socket.close();}catch(IOException ex){};
                stage.close();
            }
        });    
    }
}