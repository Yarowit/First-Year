public class Kite extends Quad{
    private double a;
    private double angle;

    Kite(double x, double alpha){
        a = x;
        angle = Math.toRadians(alpha);
        name = "Kite";
    }
    public double Perimeter(){
        return 4 * a;
    }
    public double Area(){
        return a * a * Math.sin(angle);
    }
}