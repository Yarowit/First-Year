public class Divisor {
    public static void main (String[] args) {
        for(int i = 0; i < args.length; i++ ){
            int n=0;
            try {
                n = Integer.parseInt(args[i]); 

                System.out.println( div(n) );
            }
            catch (NumberFormatException ex) {
                System.out.println(args[i] + " nie jest liczba calkowita");
            }
        }
    }
    
    public static int div (int n) {
        if( n < 2 )
            return 0;

        int i = 2;
        double root = Math.sqrt(n);
        
        while( n % i != 0  &&  i <= root)
            i++;

        if (i > root)
            return 1;
        
        return n/i;
    }
}
