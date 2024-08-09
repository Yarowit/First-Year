public class Test{
    public static void main(String[] args) throws WrongRow{
        int n;
        try{
            n = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex){
            System.out.println(args[0] + " nie jest liczba calkowita");
            return;
        }
        
        WierszTrojkataPascala row = new WierszTrojkataPascala(n);

        for (int i = 1; i < args.length; i++ ) {
            try{
                int k = Integer.parseInt(args[i]);

                if (k >= 0 && k <= n )
                    System.out.println(k + " - " + row.wspolczynnik(k));
                else
                    System.out.println(k + " - Liczba spoza zakresu");
            }
            catch (NumberFormatException ex) {
                System.out.println(args[i] + " nie jest liczba calkowita");
            }
        }
    }
}