import javafx.scene.shape.Polygon;

/** Klasa trójkąt
 */

public class Triangle extends Polygon{
    /**Tworzy trójkąt
     */
    public Triangle(){
        super();
        super.getPoints().addAll(
            -1.0, 0.0,
            1.0,  0.0,
            0.0,  4.0
        );
    }
}