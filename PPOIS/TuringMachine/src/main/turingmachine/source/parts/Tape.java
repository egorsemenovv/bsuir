package turingmachine.source.parts;

public class Tape {

    private StringBuilder tape = new StringBuilder();

    public void setTape(String tape) {;
        this.tape = new StringBuilder(tape);
    }

    public int getLength() {
        return tape.length();
    }

    public StringBuilder getTape() {
        return tape;
    }

    public char addLeft() {
        tape.insert(0, Alphabet.getBlankSymbol());
        return Alphabet.getBlankSymbol();
    }

    public char addRight() {
        tape.append(Alphabet.getBlankSymbol());
        return Alphabet.getBlankSymbol();
    }

    public char getInputSymbol(int currentIndex) {
        return tape.toString().charAt(currentIndex);
    }

    public void replaceSymbol(int currentIndex, char writeableSymbol) {
        tape.replace(currentIndex, currentIndex + 1, Character.toString(writeableSymbol));
    }
}
