public class Rectangle extends Quad{
    private double a;
    private double b;

    Rectangle(double x, double y){
        a = x;
        b = y;
        name = "Rectangle";
    }
    public double Perimeter(){
        return 2 * a + 2 * b;
    }
    public double Area(){
        return a * b;
    }
}