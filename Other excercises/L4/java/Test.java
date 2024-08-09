import javax.swing.*;

public class Test{
    final static int maxRow = 33;
    
    public static void main(String[] args){
        if (args.length == 0)
            return;

        int n;

        try{
            n = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex){
            System.out.println(args[0] + " to nie liczba calkowita");
            return;
        }

        if (n < 0 || n > maxRow){
            System.out.println(args[0] + " jest poza zakresem");
            return;
        }
        
        Pascal triangle = new Pascal(n);

        JFrame window = new JFrame("Pascal");

        String text = "<html>";

        for(int[] row : triangle.present()){
            for(int val : row)
                text = text + val + " ";
            text = text + "<br/>";
        }
        
        text = text+"</html>";

        JLabel t = new JLabel(text);
        
        window.add(t);
        
        window.pack();

        window.setVisible(true);
    }
}