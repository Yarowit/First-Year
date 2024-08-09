public class WierszTrojkataPascala {
    int[] row;
    static final int maxRow = 33;

    WierszTrojkataPascala (int n) throws WrongRow {
        if (n < 0 || n > maxRow)
            throw new WrongRow(n + " - Niepoprawny numer wiersza");

        row = new int[n + 1];

        row[0] = 1;

        if (n == 0)
            return;

        row[n] = 1;

        if (n == 1)
            return;
            
        row[1] = n;
        row[n-1] = n;
        
        if (n < 4)
            return;   
//to można skrocic
        long val = n;

        for (int i = 2; i < (n + 3) / 2; i++) {
            val = (n - i + 1) * val / i;
            
            row[i] = (int) val;
            row[n-i] = (int) val;
        }
    }

    public int wspolczynnik (int n) {
        return row[n];
    }
}
