package mt.source.parts;

import java.util.Scanner;
public class Tape {

    private StringBuilder tape;

    public Tape(String tape){
        this.tape = new StringBuilder(tape);
    }

    public int getLength(){
        return tape.length();
    }

    public StringBuilder getTape(){
        return tape;
    }
    public char addLeft(){
        tape.insert(0,Alphabet.getNullSymbol());
        return Alphabet.getNullSymbol();
    }
    public char addRight(){
        tape.append(Alphabet.getNullSymbol());
        return Alphabet.getNullSymbol();
    }

    public char getInputSymbol(int currentIndex){
        return tape.toString().charAt(currentIndex);
    }

    public void replaceSymbol(int currentIndex, char writeableSymbol){
        this.tape.replace(currentIndex, currentIndex+1, Character.toString(writeableSymbol));
    }
}
