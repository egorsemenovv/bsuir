package mt.source.parts;

public class Carriage {

    int currentIndex = 0;
    int currentState = 0;
//    currentState != ruleSet.getFinalState()
    public void startWork(Tape tape, RuleSet ruleSet) {
        int currentStateTemp;
        char inputSymbol;
        while (currentState != ruleSet.getFinalState()) {
            currentStateTemp = currentState;
            inputSymbol = tape.getInputSymbol(currentIndex);
            currentState = ruleSet.getTransitionalStateOfRule(currentState, inputSymbol);
            tape.replaceSymbol(currentIndex, ruleSet.getWriteableSymbolOfRule(currentStateTemp, inputSymbol));
            if (ruleSet.getShiftOfRule(currentStateTemp, inputSymbol) == 'L') {
                currentIndex--;
            }
            else if (ruleSet.getShiftOfRule(currentStateTemp, inputSymbol) == 'R') {
                currentIndex++;
            }
            if (currentIndex == tape.getLength()) {
                tape.addRight();
            } else if (currentIndex < 0) {
                tape.addLeft();
                currentIndex++;
            }
            System.out.println(tape.getTape());
        }
    }
}

