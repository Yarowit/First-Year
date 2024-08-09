public class Paralelogram extends Quad{
    private double a,b;
    private double angle;

    Paralelogram(double x, double y, double alpha){
        a = x;
        b = y;
        angle = Math.toRadians(alpha);
        name = "Paralelogram";
    }
    public double Perimeter(){
        return 2 * a + 2 * b;
    }
    public double Area(){
        return a * b * Math.sin(angle);
    }
}