import java.util.Random;

import javax.naming.TimeLimitExceededException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;



public class Main extends Application  {
    public static int width, height, waitTime;
    public static double probability;
    private static Tile TileArray[][];

    public static Random seed = new Random();

    public static synchronized Color getColorAverage(int i, int j){
        double counter = 0, red = 0, green = 0, blue = 0;
        int[][] TileCoordinates =   {{(i + 1) % width, j},
                                     {(i - 1 + width) % width, j},
                                     {i, (j + 1) % height},
                                     {i, (j - 1 + height) % height}};
        
        for(int[] Coordinates : TileCoordinates ){
            if(TileArray[Coordinates[0]][Coordinates[1]].isSuspended() == false){
                Color color = (Color) TileArray[Coordinates[0]][Coordinates[1]].getFill();
                red += color.getRed();
                green += color.getGreen();
                blue += color.getBlue();
                counter++;
            }
        }

        if(counter == 0)
            return null;
        
        return Color.color(red/counter,green/counter,blue/counter);
    }

    public static void main(String[] args) {
        if(args.length < 4){
            System.out.println("Niepoprawna liczba danych");
            
            Platform.exit();
            return;
        }

        try{
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            probability = Double.parseDouble(args[2]);
            waitTime = Integer.parseInt(args[3]);
            if(probability > 1 || probability < 0  || width <= 0 || height <= 0 || waitTime <= 0)
                throw new NumberFormatException();
        }
        catch (NumberFormatException ex){
            System.out.println("Podano nieprawidłowe dane");
            
            Platform.exit();
            return;
        }

        TileArray = new Tile[width][height];

        launch();        
    }

    @Override
    public void start(Stage stage) {     

        stage.setTitle("Threads");
        
        GridPane grid = new GridPane();
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){                
                if((i + j) % 2 == 0)
                    TileArray[i][j] = new Tile(10,10, Color.RED, i, j);
                else
                    TileArray[i][j] = new Tile(10,10, Color.BLUE, i, j);

                grid.add(TileArray[i][j], i, j);
            }
        }

        for(Tile[] row : TileArray) for(Tile tile : row){
            Thread thread = new Thread(tile);
            thread.start();
        }
        
        Scene scene = new Scene(grid);
        
        ChangeListener<Number> listener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                for (Node el : grid.getChildren()){
                    Rectangle rect = (Rectangle)el;
                    rect.setWidth(scene.getWidth()/width);
                    rect.setHeight(scene.getHeight()/height);
                }
            }
        };

        scene.heightProperty().addListener(listener);
        scene.widthProperty().addListener(listener);
                          
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}