public class Pentagon extends Figure{
    private double a;
    Pentagon(double r){
        a = r;
        name = "Pentagon";
    }
    public double Perimeter(){
        return 5 * a;
    }
    public double Area(){
        return Math.sqrt(25 + 10 * Math.sqrt(5)) / 4 * a * a;
    }
}