import java.io.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String command = "./Pascal.exe";

        for (String el : args){
            command = command +" " + el;
        }

        String text = "<html>";

        try{
            Process proc = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                text = text + line + "<br/>";
            }
            text = text+"</html>";
      
            reader.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        
        JFrame window = new JFrame("Pascal");

        JLabel t = new JLabel(text);
        
        window.add(t);
        
        window.pack();

        window.setVisible(true);
    }
}