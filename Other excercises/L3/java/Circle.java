public class Circle extends Figure{
    private double radius;
    Circle(double r){
        radius = r;
        name = "Circle";
    }
    public double Perimeter(){
        return 2 * Math.PI * radius;
    }
    public double Area(){
        return Math.PI * radius * radius;
    }
}