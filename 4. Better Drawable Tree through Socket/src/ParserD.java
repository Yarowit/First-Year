public class ParserD implements Parser{
    public Object parse(String s){
        Double n;
        try{
            n = Double.parseDouble(s);
        }catch(NumberFormatException ex){
            n = 0.0;
        }
        return n;
    }
}