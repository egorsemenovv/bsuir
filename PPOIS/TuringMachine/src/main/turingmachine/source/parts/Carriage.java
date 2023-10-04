package turingmachine.source.parts;

public class Carriage {

    private int currentIndex = 0;
    private int currentState = 0;
    private int finalState;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getFinalState() {
        return finalState;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setFinalState(int finalState) {
        this.finalState = finalState;
    }

    public void move(Tape tape, RuleSet ruleSet) {
        int currentStateTemp = currentState;
        char inputSymbol = tape.getInputSymbol(currentIndex);
        if (ruleSet.isRuleExist(currentState, inputSymbol)) {
            currentState = ruleSet.getTransitionalStateOfRule(currentState, inputSymbol);
            tape.replaceSymbol(currentIndex, ruleSet.getWriteableSymbolOfRule(currentStateTemp, inputSymbol));
            if (ruleSet.getShiftOfRule(currentStateTemp, inputSymbol) == 'L') {
                currentIndex--;
            } else if (ruleSet.getShiftOfRule(currentStateTemp, inputSymbol) == 'R') {
                currentIndex++;
            }
            if (currentIndex == tape.getLength()) {
                tape.addRight();
            } else if (currentIndex < 0) {
                tape.addLeft();
                currentIndex++;
            }
            System.out.println(tape.getTape());
        }else {
            System.out.println("Here is no rule for q"+currentStateTemp+" and '"+inputSymbol+"'");
            currentState=finalState;
        }
    }

    public boolean isOnFinalState() {
        return currentState == finalState;
    }

}

