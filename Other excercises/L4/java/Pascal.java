public class Pascal {
    private int[][] triangle;

    Pascal (int n) {
        triangle = new int[n+1][];

        for(int row = 0; row <= n; row++) {
            triangle[row] = new int[row + 1];
            triangle[row][0] = 1;
            triangle[row][row] = 1;
            
            for(int col = 1; col < row; col++){
                triangle[row][col] = triangle[row - 1][col] + triangle[row - 1][col - 1];
            }
        }
    }

    public int[][] present(){
        return triangle;
    }
}