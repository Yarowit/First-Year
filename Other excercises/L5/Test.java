import javafx.application.Application;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
 

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {        

        stage.setTitle("Palette");
        
        Button CircleMaker = new Button("Circle");
        Button RectangleMaker = new Button("Rectangle");
        Button TriangleMaker = new Button("Triangle");
        Button ReshapeMaker = new Button("Reshape");
        Button Manual = new Button("Manual");
        Button Info = new Button("Info");

        HBox ButtonBox = new HBox(CircleMaker,RectangleMaker,TriangleMaker,ReshapeMaker,Manual,Info);


        Pane Palette = new Pane();
        Palette.setStyle("-fx-background-color: #FFF8EE;");
           
        BorderPane root = new BorderPane(Palette, ButtonBox, null,null,null);
        
        for(Node child : ButtonBox.getChildren()){
            Button button = (Button)child;
            button.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(button, Priority.ALWAYS);
        }
            
            
            
        Scene scene = new Scene(root,600,500);                
        stage.setScene(scene);
        stage.show();


        Engine engine = new Engine(Palette);


        CircleMaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.changeMode(Engine.Mode.CircleMode);
            }
        });
        RectangleMaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.changeMode(Engine.Mode.RectangleMode);
            }
        });
        TriangleMaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.changeMode(Engine.Mode.TriangleMode);
            }
        });
        ReshapeMaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.changeMode(Engine.Mode.ReshapeMode);
            }
        });
        ReshapeMaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.changeMode(Engine.Mode.ReshapeMode);
            }
        });
        Manual.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.changeMode(null);
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Manual");
                alert.setContentText("To create a shape choose a type and click on the palette, to confirm the shape click second time. To relocate a shape click on it while mode 'Reshape' is chosen, by scrolling on a shape you can resize it and by scrolling sideways, rotate it. Right click a shape to change it's color.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        Info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.changeMode(null);
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setContentText("Name: Palette \nPurpose: Drawing simple shapes with ability to edit them \nAuthor: Jarosław Socha ");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });        
    }
}