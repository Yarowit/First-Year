public class Hexagon extends Figure{
    private double a;
    Hexagon(double r){
        a = r;
        name = "Hexagon";
    }
    public double Perimeter(){
        return 6 * a;
    }
    public double Area(){
        return 3 * Math.sqrt(3) / 2 * a * a;
    }
}