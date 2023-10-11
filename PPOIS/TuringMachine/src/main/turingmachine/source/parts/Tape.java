package turingmachine.source.parts;

public class Tape {

    private StringBuilder tape = new StringBuilder();

    /**
     * @param tape string to set tape
     */
    public void setTape(String tape) {
        this.tape = new StringBuilder(tape);
    }


    /**
     * @return length of tape
     */
    public int getLength() {
        return tape.length();
    }

    /**
     * @return tape
     */
    public StringBuilder getTape() {
        return tape;
    }

    /**
     * add a null symbol to the left side of tape
     *
     * @return null symbol from alphabet
     */
    public char addLeft() {
        tape.insert(0, Alphabet.getBlankSymbol());
        return Alphabet.getBlankSymbol();
    }

    /**
     * add a null symbol to the right side of tape
     *
     * @return null symbol from alphabet
     */
    public char addRight() {
        tape.append(Alphabet.getBlankSymbol());
        return Alphabet.getBlankSymbol();
    }

    /**
     * @param currentIndex current index of carriage on tape
     * @return symbol on which carriage points
     */
    public char getInputSymbol(int currentIndex) {
        return tape.toString().charAt(currentIndex);
    }

    /**
     * rewrites symbols on tape
     *
     * @param currentIndex    current index of carriage on tape
     * @param writeableSymbol symbol from transition rule to rewrite on tape
     */
    public void replaceSymbol(int currentIndex, char writeableSymbol) {
        tape.replace(currentIndex, currentIndex + 1, Character.toString(writeableSymbol));
    }
}
