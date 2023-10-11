package turingmachine.source.parts;

import java.util.Objects;

public class StartRule {

    public int currentState;
    public char inputSymbol;

    /**
     * Constructor to create objects by incoming rule
     *
     * @param inputRule string rule
     */
    StartRule(String inputRule) {
        this.currentState = Integer.parseInt((inputRule.substring(1, inputRule.length() - 1)));
        this.inputSymbol = inputRule.charAt(inputRule.length() - 1);
    }

    /**
     * @param currentState current state of carriage
     * @param inputSymbol  symbol on which tapes points
     */
    StartRule(int currentState, char inputSymbol) {
        this.currentState = currentState;
        this.inputSymbol = inputSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartRule startRule = (StartRule) o;
        return currentState == startRule.currentState && inputSymbol == startRule.inputSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState, inputSymbol);
    }

    @Override
    public String toString() {
        return "q" + currentState + inputSymbol;
    }
}
