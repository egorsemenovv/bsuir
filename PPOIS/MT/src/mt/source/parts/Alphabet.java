package mt.source.parts;


public class Alphabet {
    private static StringBuilder alphabet = new StringBuilder();
    private static char nullSymbol = ' ';

    public static StringBuilder setAlphabet(StringBuilder alphabet){
        Alphabet.alphabet = alphabet;
        return Alphabet.alphabet;
    }
    public static char getNullSymbol() {
        return nullSymbol;
    }

    public static char setNullSymbol(char nullSymbol){
        Alphabet.nullSymbol = nullSymbol;
        return nullSymbol;
    }
    public boolean isInAlphabet(char symbol){
        return alphabet.indexOf(Character.toString(symbol)) >= 0;
    }
}


