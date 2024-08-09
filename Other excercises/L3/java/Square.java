public class Square extends Quad{
    private double a;
    Square(double side){
        a = side;
        name = "Square";
    }
    public double Perimeter(){
        return 4 * a;
    }
    public double Area(){
        return a * a;
    }
}