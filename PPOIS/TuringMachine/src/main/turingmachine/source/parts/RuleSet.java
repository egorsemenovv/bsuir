package turingmachine.source.parts;


import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleSet {

    /**
     * hash map to store rules for carriage
     */
    private final LinkedHashMap<StartRule, Transition> setOfRules = new LinkedHashMap<>();

    /**
     *
     * @param currentState current state of carriage
     * @param inputSymbol symbol on which carriage points
     * @return checking if rule with such parameters exists
     */
    public boolean isRuleExist(int currentState , char inputSymbol){
        return setOfRules.containsKey(new StartRule(currentState, inputSymbol));
    }

    /**
     *
     * @param currentState current state of carriage
     * @param inputSymbol symbol on which carriage points
     * @return symbol from transition rule to rewrite on tape
     */
    public char getWriteableSymbolOfRule(int currentState, char inputSymbol) {
        return setOfRules.get(new StartRule(currentState, inputSymbol)).getWriteableSymbol();
    }

    /**
     *
     * @param currentState current state of carriage
     * @param inputSymbol symbol on which carriage points
     * @return shift-symbol from transition rule to rewrite on tape
     */
    public char getShiftOfRule(int currentState, char inputSymbol) {
        return setOfRules.get(new StartRule(currentState, inputSymbol)).getShift();
    }

    /**
     *
     * @param currentState current state of carriage
     * @param inputSymbol symbol on which carriage points
     * @return transitional state of rule to set for carriage
     */
    public int getTransitionalStateOfRule(int currentState, char inputSymbol) {
        return setOfRules.get(new StartRule(currentState, inputSymbol)).getTransitionalState();
    }

    /**
     * adding rules to set of rules
     * @param inputRule rule to add for set of rules
     * @return 1 if rule has been added and 0 if not
     */
    public int addRule(String inputRule) {
        Pattern pattern = Pattern.compile("^q\\d+.->q\\d+.[RLN]$");
        Matcher matcher = pattern.matcher(inputRule);
        if (!matcher.matches()){
            System.out.println("Incorrect rule, example: q'number''symbol'->q'number''symbol''shift'");
            return 0;
        }
        StartRule firstPartOfRule = new StartRule(inputRule.substring(0, inputRule.indexOf("-")));
        Transition secondPartOfRule = new Transition(inputRule.substring(inputRule.indexOf(">") + 1));
        if (setOfRules.containsKey(firstPartOfRule)) {
            System.out.println("Rule with such running state and input symbol is already exist");
            return 0;
        } else {
            System.out.println("Your rule added to the table of rules");
            setOfRules.put(firstPartOfRule, secondPartOfRule);
        }
        return 1;
    }

    /**
     * deleting rule from rule set
     * @param inputRule rule to delete from set of rules
     * @return 1 if rule has been deleted and 0 if not
     */
    public int delRule(String inputRule) {
        Pattern pattern = Pattern.compile("^q\\d+.->q\\d+.[RLN]$");
        Matcher matcher = pattern.matcher(inputRule);
        if (!matcher.matches()){
            System.out.println("Incorrect rule, example: q'number''symbol'->q'number''symbol''shift'");
            return 0;
        }
        StartRule firstPartOfRule = new StartRule(inputRule.substring(0, inputRule.indexOf("-")));
        Transition secondPartOfRule = new Transition(inputRule.substring(inputRule.indexOf(">") + 1));
        if (setOfRules.containsKey(firstPartOfRule) && setOfRules.get(firstPartOfRule).equals(secondPartOfRule)) {
            System.out.println("Rule has been deleted");
            setOfRules.remove(firstPartOfRule);
        } else {
            System.out.println("Such rule doesn't exist");
            return 0;
        }
        return 1;
    }

    public void showRules() {
        for (StartRule name : setOfRules.keySet()) {
            String key = name.toString();
            String value = setOfRules.get(name).toString();
            System.out.println(key + "->" + value);
        }
    }
}
