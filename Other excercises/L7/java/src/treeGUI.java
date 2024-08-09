import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
 

public class treeGUI extends Application{    
    private Socket socket;        
    private BufferedReader in;        
    private PrintWriter out;
    private TextField console;
    private Label display;

    treeGUI(Socket socket, String mode){
        this.socket = socket;
        try{
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException ex){}
    
        console = new TextField();
        display = new Label(mode);
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tree");

        Scene scene = new Scene(new VBox(console, display), 600, 300);                
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
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
                    String line;
                    while(true){
                        line = console.getText();
                        // Wysylanie do serwera
                        out.println(line);
                        // Odbieranie z serwera
                        if(line.equals("exit"))
                            break;
            
                        display.setText(in.readLine());
            
                        //DrawYes // DrawNo
                        
                        if(in.readLine().equals("drawYes")){
                            // do{
                            line = in.readLine();
                            display.setText(line);
                            // }while(! line.equals("drawEnd"));
                        }
                    
                    }
                    socket.close();
                    stage.close();
                }catch (IOException ex){}
            }
        });
    }
}