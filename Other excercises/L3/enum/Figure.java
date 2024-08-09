public abstract class Figure {
    // public String name;
    // public String getName(){
    //     return name;
    // }
    
    public enum Singles implements singleArg {
        Circle("Circle"){
            public double Perimeter(double a){
                return 2 * Math.PI * a;
            }
            public double Area(double a){
                return Math.PI * a * a;
            }
        }, 
        Square("Square"){
            public double Perimeter(double a){
                return 4 * a;
            }
            public double Area(double a){
                return a * a;
            }
        },
        Pentagon("Pentagon"){
            public double Perimeter(double a){
                return 5 * a;
            }
            public double Area(double a){
                return Math.sqrt(25 + 10 * Math.sqrt(5)) / 4 * a * a;
            }
        }, 
        Hexagon("Hexagon"){
            public double Perimeter(double a){
                return 6 * a;
            }
            public double Area(double a){
                return 3 * Math.sqrt(3) / 2 * a * a;
            }
        };
        
        private String name;
        
        Singles(String n){
            name = n;
        }
        public String getName(){
            return name;
        }
    }
    public enum Doubles implements doubleArg {
        Rectangle("Rectangle"){
            public double Perimeter(double a, double b){
                return 2 * a + 2 * b;
            }
            public double Area(double a, double b){
                return a * b;
            }
        }, 
        Kite("Kite"){
            public double Perimeter(double a, double b){
                return 4 * a;
            }
            public double Area(double a, double angle){
                return a * a * Math.sin(Math.toRadians(angle));
            }
        };

        private String name;
        
        Doubles(String n){
            this.name = n;
        }
        public String getName(){
            return name;
        }
    }
}