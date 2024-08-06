public class Main {
    public static void main(String[] args){
        Tree<?> t;
        ParseT<?> p;
        switch(args[0]){
            case "i":
                p = new ParseT<Integer>(new ParserI());
                t = new Tree<Integer>((Integer) p.parse(args[1]));
                break;
            case "d":
                p = new ParseT<Double>(new ParserD());
                t = new Tree<Double>((Double) p.parse(args[1]));
                break;
            case "s":
                p = new ParseT<String>(new ParserS());
                t = new Tree<String>((String) p.parse(args[1]));
                break;
        }
        for(int i = 2; i < args.length; i += 2){
            switch(args[0]){
                case "i":
                    t.insert(p.parse(args[i]));
                    break;
                case "d":
                    p = new ParseT<Double>(new ParserD());
                    t = new Tree<Double>((Double) p.parse(args[1]));
                    break;
                case "s":
                    p = new ParseT<String>(new ParserS());
                    t = new Tree<String>((String) p.parse(args[1]));
                    break;
            }
        }
    }
}
