package mt.source.parts;

import java.util.Objects;

public class StartRule {
    int currentState;
    char inputSymbol;

    StartRule(String inputRule) {
        this.currentState = Integer.parseInt(Character.toString(inputRule.charAt(1)));
        this.inputSymbol = inputRule.charAt(2);
    }

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
    //Rule: q0'symbol'->q1'symbol''shift'

    @Override
    public String toString() {
        return "q" + currentState + inputSymbol;
    }
}
