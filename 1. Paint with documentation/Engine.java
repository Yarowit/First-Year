import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/** Silnik do rysowania
 * 
 */
public class Engine{
    private static Mode mode;
    private static State state;
    private static Pane Palette;
    private static Shape tShape, shape;
    private static double oldPaletteX, oldPaletteY;
   
    private static ColorPicker Picker = new ColorPicker();
    
    /** Dostosowuje rozmiary i pozycje wszystkich figur do rozmiaru okna
     * @param PaletteX nowa szerokość okna 
     * @param PaletteY nowa wysokość okna 
     */

    private static void WindowResize(double PaletteX, double PaletteY){
        hidePicker();
        double scaleX = PaletteX/oldPaletteX, scaleY = PaletteY/oldPaletteY;
        
        for(Object child : Palette.getChildren()){
            Shape shape = (Shape) child;
            shape.setScaleX(shape.getScaleX() * scaleX);
            shape.setScaleY(shape.getScaleY() * scaleY);

            shape.setLayoutX(shape.getLayoutX() * scaleX);
            shape.setLayoutY(shape.getLayoutY() * scaleY);
            shape.setTranslateX(shape.getTranslateX() * scaleX );
            shape.setTranslateY(shape.getTranslateY() * scaleY );
         }
        oldPaletteX = PaletteX;
        oldPaletteY = PaletteY;
    }

    /** Tryb rysowania
    */
    public enum Mode{
        /**@brief Tryb rysowania koła */
        CircleMode{
            @Override 
            protected void begAct(Point2D mouse){
                tShape = new Circle(0,0,1);
                super.begAct(mouse);
            }
            @Override 
            protected void cling(Point2D mouse){
                Point2D center = new Point2D(tShape.getLayoutX(), tShape.getLayoutY());
                tShape.setScaleX(center.distance(mouse));
                tShape.setScaleY(center.distance(mouse));
            }
            
        },
        /**@brief Tryb rysowania prostokąta */
        RectangleMode{
            @Override 
            protected void begAct(Point2D mouse){
                tShape = new Rectangle(0,0,1,1);
                super.begAct(mouse);                
            }
            @Override 
            protected void cling(Point2D mouse){
                tShape.setTranslateY((mouse.getY() - tShape.getLayoutY())/2);
                tShape.setTranslateX((mouse.getX() - tShape.getLayoutX())/2);
                
                tShape.setScaleY(tShape.getLayoutY() - mouse.getY());
                tShape.setScaleX(tShape.getLayoutX() - mouse.getX());
            }
        },
        /**@brief Tryb rysowania trójkąta */
        TriangleMode{
            @Override 
            protected void begAct(Point2D mouse){
                tShape = new Triangle();
                super.begAct(mouse);
            }
            @Override 
            protected void cling(Point2D mouse){
                double scale = new Point2D(tShape.getLayoutX(),tShape.getLayoutY()).distance(mouse)/2;
                tShape.setScaleY(scale);
                tShape.setScaleX(scale);
                Point2D zeroVector = new Point2D(0, 1);
        
                double angle = zeroVector.angle(mouse.subtract(new Point2D(tShape.getLayoutX(), tShape.getLayoutY())));
                
                if(mouse.getX() > tShape.getLayoutX())
                    angle = 360 - angle;
                
                tShape.setRotate(angle); 
            }
        },
        /**@brief Tryb przesuwania i zmiany kształtu */
        ReshapeMode{
            @Override 
            protected void begAct(Point2D mouse){
                if(shape.getFill() != Color.RED)
                    shape.setStroke(Color.RED);
                else
                    shape.setStroke(Color.VIOLET);

                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStrokeWidth(0.1);
            }
            
            @Override 
            protected void cling(Point2D mouse){
                shape.setTranslateX(mouse.getX() - shape.getLayoutX());
                shape.setTranslateY(mouse.getY() - shape.getLayoutY());
            }
            @Override 
            protected void endAct(Point2D mouse){
                shape.setStroke(shape.getFill());
                shape.setStrokeWidth(0);
                super.endAct(mouse);
            }
        };
        /** Akcja po pierwszym kliknięciu w zależności od trybu
         * 
         * @param mouse współrzędne myszki
         */
        protected void begAct(Point2D mouse){
            tShape.setLayoutX(mouse.getX());
            tShape.setLayoutY(mouse.getY());
            
            Palette.getChildren().add(tShape);
        };
        /** Akcja po drugim kliknięciu w zależności od trybu
         * 
         * @param mouse współrzędne myszki
         */
        protected void endAct(Point2D mouse){
            mode.cling(mouse);
            if(tShape != null){
                tShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override 
                    public void handle(MouseEvent event) {
                        shape = (Shape)event.getSource();
                        if(event.getButton() == MouseButton.SECONDARY){
                            
                            Picker.setValue((Color)shape.getFill());
                            
                            if(Palette.getChildren().contains(Picker) == false)
                                Palette.getChildren().add(Picker);
                                Picker.setLayoutX(shape.getLayoutX() + shape.getTranslateX());
                                Picker.setLayoutY(shape.getLayoutY() + shape.getTranslateY());
                            
                                event.consume();

                        }else if (mode == Mode.ReshapeMode && state == State.Waiting){
                            state = state.next(null);
                            event.consume();
                        }
                    }
                });
                tShape = null;
            }
            if(shape!= null)
                shape = null;
            
        }; 
        /** Akcja pomiędzy kliknięciami w zależności od trybu
         * 
         * @param mouse współrzędne myszki
         */
        abstract protected void cling(Point2D mouse);
    }
    
    /** Stan maszyny
    */
    private enum State{
        Waiting{
            @Override 
            State next(Point2D mouse){
                mode.begAct(mouse);
                return State.InProgress;
            }

        }, InProgress{
            @Override 
            State next(Point2D mouse){
                mode.endAct(mouse);
                return State.Waiting;
            }

        };
        /** Zmienia stan maszyny stanów
         * @param mouse współrzędne myszki
         * @return następny stan
         */
        abstract State next(Point2D mouse);
    }

    
    /** chowa paletę kolorów
     */
    private static void hidePicker(){
        if(Palette.getChildren().contains(Picker) == true)
            Palette.getChildren().remove(Picker);
    }
    /** obraca bieżący kształt o kąt
     * @param deltaX kąt obrotu
     */
    private void rotate(double deltaX){
        shape.setRotate(shape.getRotate() - deltaX);
    }
    /** powiększa kształt o daną wartość
     * @param deltaX inkrementacja skali
     */
    private void resize(double deltaY){
        shape.setScaleX(shape.getScaleX() + deltaY);
        shape.setScaleY(shape.getScaleY() + deltaY);
    }
    
    /** Tworzy silnik do rysowania
     * @param palette Paleta do rysowania
     */

    public Engine(Pane palette){
        Palette = palette;

        Palette.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    Point2D point = new Point2D(event.getX(),event.getY());
                    if((state == Engine.State.Waiting && mode == Engine.Mode.ReshapeMode) == false)
                        state = state.next(point);
                }
                hidePicker();
            }
        });

        Palette.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if (state == Engine.State.InProgress){
                    Point2D point = new Point2D(event.getX(),event.getY());
                    mode.cling(point);
                }
            }
        });

        Palette.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override 
            public void handle(ScrollEvent event) {
                if (state == Engine.State.InProgress){
                    resize(event.getDeltaY());
                    rotate(event.getDeltaX());
                }
            }
        });

        Picker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shape.setFill(Picker.getValue());
            }
        }); 

        Picker.getStyleClass().add("button");
        oldPaletteX = Palette.getWidth();
        oldPaletteY = Palette.getHeight();

        ChangeListener<Number> listener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                WindowResize(Palette.getWidth(), Palette.getHeight());
            }
        };

        Palette.heightProperty().addListener(listener);
        Palette.widthProperty().addListener(listener);
        
        changeMode(Engine.Mode.CircleMode);
    }


    /** Zmienia obecny tryb na inny
     * @param newMode Nowy tryb
     */
    public void changeMode(Mode newMode){
        if(Palette.getChildren().contains(Picker)){
            Palette.getChildren().remove(Picker);
        }
        if(newMode != null)
            mode = newMode;
        Palette.getChildren().remove(tShape);
        
        tShape = null;
        state = State.Waiting;
    }
}