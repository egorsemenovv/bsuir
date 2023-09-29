package mt.source.parts;

import java.util.HashMap;

public class RuleSet {

    private HashMap<StartRule, Transition> setOfRules = new HashMap<>();
    private int finalState;

    public int getFinalState() {
        return finalState;
    }

    public void setFinalState(int finalState) {
        this.finalState = finalState;
    }

    public char getWriteableSymbolOfRule(int currentState, char inputSymbol) {
        return setOfRules.get(new StartRule(currentState, inputSymbol)).getWriteableSymbol();
    }

    public char getShiftOfRule(int currentState, char inputSymbol) {
        return setOfRules.get(new StartRule(currentState, inputSymbol)).getShift();
    }

    public int getTransitionalStateOfRule(int currentState, char inputSymbol) {
        return setOfRules.get(new StartRule(currentState, inputSymbol)).getTransitionalState();
    }

    public String addRule(String inputRule) {
        StartRule firstPartOfRule = new StartRule(inputRule.substring(0, inputRule.indexOf("-")));
        Transition secondPartOfRule = new Transition(inputRule.substring(inputRule.indexOf(">") + 1, 9));
        if (setOfRules.containsKey(firstPartOfRule)) {
            System.out.println("Rule with such running state and input symbol is already exist");
        } else {
            System.out.println("Your rule added to the table of rules");
            setOfRules.put(firstPartOfRule, secondPartOfRule);
        }
        return firstPartOfRule + "->" + secondPartOfRule;
    }

    public String delRule(String inputRule) {
        StartRule firstPartOfRule = new StartRule(inputRule.substring(0, inputRule.indexOf("-")));
        Transition secondPartOfRule = new Transition(inputRule.substring(inputRule.indexOf(">") + 1, 9));
        if (setOfRules.containsKey(firstPartOfRule) && setOfRules.get(firstPartOfRule).equals(secondPartOfRule)) {
            System.out.println("Rule has been deleted");
            setOfRules.remove(firstPartOfRule);
        } else {
            System.out.println("Such rule doesn't exist");
        }
        return firstPartOfRule + "->" + secondPartOfRule;
    }

    public void showRules() {
        for (StartRule name : setOfRules.keySet()) {
            String key = name.toString();
            String value = setOfRules.get(name).toString();
            System.out.println(key + "->" + value);
        }
    }
}
