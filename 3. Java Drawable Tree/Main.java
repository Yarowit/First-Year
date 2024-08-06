import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public Pane Paint = new Pane();
    public int heightIncr = 100, widthIncr = 100;

    public void draw(Node<Integer> node, double level, double horizontal){
        if(node == null)
            return;

        Text child =new Text( horizontal,heightIncr*level,node.value.toString());
        Paint.getChildren().add(child);

        System.out.println(child.getX() + " "+ child.getY() + " " +child.getText());

        draw(node.left,level+1,horizontal - widthIncr/level);
        draw(node.right,level+1,horizontal + widthIncr/level);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {        

        stage.setTitle("Tree");

        
        BorderPane root = new BorderPane(Paint, null, null,null,null);
            
            
            
            
        Scene scene = new Scene(root,600,500);                
        stage.setScene(scene);
        stage.show();
        
        //******************************************************************* */
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
        tree.draw(tree);
        // System.out.println("m: " + tree.measure(tree,0));
        draw(tree, 1, Paint.getWidth()/2);
        // try{wait(2000);}catch(InterruptedException ex){};
        
        System.out.println("Koniec");
        // try{Thread.sleep(2000);}catch(InterruptedException ex){};
        

        Paint.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                Paint.getChildren().clear();
                tree.delete(17);
                draw(tree, 1, Paint.getWidth()/2);
                tree.draw(tree);
            }
        });
        
    }
}