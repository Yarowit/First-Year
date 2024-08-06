public class ParseT <T extends Comparable<T>>{
    Parser p;
    ParseT(Parser p){
        this.p = p;
    }
    public T parse(String s){
        @SuppressWarnings("unchecked") T value = (T) p.parse(s);
         return value;
    }
}
