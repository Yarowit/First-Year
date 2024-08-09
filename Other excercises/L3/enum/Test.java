public class Test{
    public static void main(String[] args){
        int i = 0;
        double nums[] = new double[args.length-1];
        for (int j = 1; j < args.length; j++){
            try{
                nums[j-1] = Double.parseDouble(args[j]);
            }catch (NumberFormatException ex) {
                System.out.println(args[j] + " nie jest liczba calkowita");
            }
        }

        for(int j = 0; j < args[0].length(); j++){
            char shape = args[0].charAt(j);

            if (shape == 'c' ||shape == 'p' ||shape == 'h' || shape == 's'){
                if(i >= nums.length){
                    System.out.println("kształt " + j + " - brak argumentów");
                    i++;
                    continue;
                }
                if(nums[i] <= 0){
                    System.out.println("kształt " + j + " : " + args[i+1] + " niepoprawny argument");
                    i++;
                    continue;
                }
            }
            if (shape == 'r' || shape == 'k'){
                if(i + 1 >= nums.length){
                    System.out.println("kształt " + j + " - brak argumentów");
                    i += 2;
                    continue;
                }
                boolean wrongData = false;
                for(int k = 0; k < 2; k++){
                    if(nums[i+k] <= 0){
                        System.out.println("kształt " + j + " : " + args[i+1+k] + " niepoprawny argument");
                        wrongData = true;
                    }
                }
                if (wrongData == true){
                    i += 2;
                    continue;
                }
            }

            if (shape == 'c' || shape == 'p' || shape == 'h' || shape == 's'){
            	Figure.Singles s;
            	switch(shape){
					case 'c':
					    s = Figure.Singles.Circle;
					    break;
					case 'p':
					    s = Figure.Singles.Pentagon;
					    break;
					case 'h':
					    s = Figure.Singles.Hexagon;
					    break;
					default:
					    s = Figure.Singles.Square;
            	}
            	
            	System.out.println(s.getName() + " " + s.Perimeter(nums[i]) + " " + s.Area(nums[i]));
                i++;
            }else if (shape == 'r' || shape == 'k'){
            	Figure.Doubles d;
            	switch(shape){
            		case 'r':
                    	d = Figure.Doubles.Rectangle;
                    	break;
                	default:
                    	d = Figure.Doubles.Kite;
            	}
            	System.out.println(d.getName() + " " + d.Perimeter(nums[i],nums[i+1]) + " " + d.Area(nums[i],nums[i+1]));
                i += 2;
            }else
            	System.out.println(args[0].charAt(j) + " nie jest poprawna figura"); 
        }
    }    
}
