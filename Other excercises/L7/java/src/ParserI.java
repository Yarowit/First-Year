public class ParserI implements Parser{
    public Object parse(String s){
        Integer n;
        try{
            n = Integer.parseInt(s);
        }catch(NumberFormatException ex){
            n = 0;
        }
        return n;
    }
}