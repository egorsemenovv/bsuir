package turingmachine.source.parts;

public class Carriage {

    private int currentIndex = 0;
    private int currentState = 0;
    private int finalState;

    /**
     * @return current position of carriage on tape
     */

    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * @return terminal state of carriage
     */
    public int getFinalState() {
        return finalState;
    }

    /**
     * @param currentIndex current index of carriage on tape
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * @param finalState sets terminal state to carriage
     */
    public void setFinalState(int finalState) {
        this.finalState = finalState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    /**
     * @return current state of carriage
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * @param tape    tape from Turing Machine
     * @param ruleSet set of rules for carriage operation
     */
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
        } else {
            System.out.println("Here is no rule for q" + currentStateTemp + " and '" + inputSymbol + "'");
            currentState = finalState;
        }
    }

    /**
     * @return checking if carriage state is final
     */
    public boolean isOnFinalState() {
        return currentState == finalState;
    }

}

