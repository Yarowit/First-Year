import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle implements Runnable{
    public int Index,Indey;
    public Boolean Suspended = false;
    
    public void run(){
        while(true) {
            try{
                synchronized(this){
                    while(Suspended)
                        wait();
                }
            }
            catch(InterruptedException e){}

            try {
                Platform.runLater(() -> {
                    if(Main.seed.nextDouble() <= Main.probability){
                        this.setFill(Color.color(Main.seed.nextDouble(),Main.seed.nextDouble(),Main.seed.nextDouble()));
                    }else{
                        Color color = Main.getColorAverage(Index,Indey);
                        if(color != null)
                            this.setFill(color);
                    }
                });

                Thread.sleep( (long) (Main.waitTime * (Main.seed.nextDouble()+0.5)) );
            }
            catch(Exception e){}
        }
    }

    public synchronized void toggle(){
        Suspended = !Suspended;
        if(Suspended == false)
            notify();
    }

    public Boolean isSuspended(){
        return Suspended;
    }

    Tile(double x, double y, Color color,int index, int indey){
        super(x, y,color);
        Index = index;
        Indey = indey;

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                toggle();
            }
        });
    }
}