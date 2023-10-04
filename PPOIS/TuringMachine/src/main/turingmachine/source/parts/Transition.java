package turingmachine.source.parts;

public class Transition {

    private int transitionalState;
    private char writeableSymbol;
    private char shift;

    public Transition(String inputRule){
        writeableSymbol = inputRule.charAt(2);
        shift = inputRule.charAt(3);
        transitionalState = Integer.parseInt(Character.toString(inputRule.charAt(1)));
    }

    public int getTransitionalState() {
        return transitionalState;
    }

    public char getWriteableSymbol() {
        return writeableSymbol;
    }

    public char getShift() {
        return shift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition transition = (Transition) o;
        return transitionalState == transition.transitionalState && writeableSymbol == transition.writeableSymbol && shift == transition.shift;
    }

    @Override
    public String toString() {
        return "q" + transitionalState+writeableSymbol+shift;
    }
}
