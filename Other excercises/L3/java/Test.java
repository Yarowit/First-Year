import java.util.ArrayList;
import java.util.Arrays;

public class Test{
    public static void main(String[] args){
        int i = 0;
        double nums[] = new double[args.length - 1];
        for (int j = 1; j < args.length; j++){
            try{
                nums[j-1] = Double.parseDouble(args[j]);
            }catch (NumberFormatException ex) {
                System.out.println(args[j] + " nie jest liczba calkowita");
            }
        }

        ArrayList<Figure> Shapes = new ArrayList<Figure>();

        for(int j = 0; j < args[0].length(); j++){
            char shape = args[0].charAt(j);

            if (shape == 'o' ||shape == 'p' ||shape == 's'){
                if(i >= nums.length){
                    System.out.println("kształt " + j + " - brak argumentów");
                    i++;
                    continue;
                }
                if(nums[i] <= 0){
                    System.out.println("kształt " + j + " niepoprawna długość");
                    i++;
                    continue;
                }
            }
            if (shape == 'c'){
                if(i + 4 >= nums.length){
                    System.out.println("kształt " + j + " - brak argumentów");
                    i += 5;
                    continue;
                }
                boolean wrongData = false;
                for(int k = 0; k < 4; k++){
                    if(nums[i+k] <= 0){
                        System.out.println("kształt " + j + " niepoprawna długość");
                        wrongData = true;
                    }
                }
                if (wrongData == true){
                    i += 5;
                    continue;
                }
                if(nums[i+4] <= 0 || nums[i+4] >= 180){
                    System.out.println("kształt " + j + " niepoprawny kąt");
                    i += 5;
                    continue;
                }
            }

            switch(shape){
                case 'o':
                    Shapes.add(new Circle(nums[i]));
                    i++;
                    break;
                case 'p':
                    Shapes.add(new Pentagon(nums[i]));
                    i++;
                    break;
                case 's':
                    Shapes.add(new Hexagon(nums[i]));
                    i++;
                    break;
                case 'c':
                    Shapes.add(parse(nums, i));
                    i = i + 5;
                    break;
                default:
                    System.out.println(args[j] + " nie jest poprawna figura");                    
            }
        }

        for(Figure f : Shapes){
            if(f == null)
                System.out.println("Niepoprawne dane czworokata");
            else
                System.out.println(f.getName() + " " + f.Perimeter() + " " + f.Area());
        }
    }

    private static Figure parse(double arr[], int i){
        Arrays.sort(arr, i, i + 4);
        
        if(arr[i] == arr[i+1] && arr[i+2] == arr[i+3]){
            if(arr[i+1] == arr[i+2]){
                if(arr[i+4] == 90)
                    return new Square(arr[i]);
                return new Kite(arr[i],arr[i+4]);
            }
            if(arr[i+4] == 90)
                return new Rectangle(arr[i],arr[i+2]);
            return new Paralelogram(arr[i], arr[i+2], arr[i+4]);
        }
        return null;
    }
}