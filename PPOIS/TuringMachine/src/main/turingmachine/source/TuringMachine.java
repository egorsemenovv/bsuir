package turingmachine.source;

import turingmachine.source.parts.Alphabet;
import turingmachine.source.parts.Carriage;
import turingmachine.source.parts.RuleSet;
import turingmachine.source.parts.Tape;

import java.util.Scanner;

public class TuringMachine {
    public final Tape tape = new Tape();
    private final RuleSet ruleSet = new RuleSet();
    private final Carriage carriage = new Carriage();
    private final boolean logFlag;

    public TuringMachine(boolean logFlag) {
        this.logFlag = logFlag;
    }

    public void setTape(String tape) {
        this.tape.setTape(tape);
    }

    /**
     * sets tape to TuringMachine
     */
    public void setTape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new tape: ");
        String string = scanner.nextLine();
        while (!Alphabet.isNormalTape(string)) {
            System.out.print("Tape must consist only of symbols in alphabet. Enter new tape: ");
            string = scanner.nextLine();
        }
        tape.setTape(string);
        carriage.setCurrentIndex(0);
    }

    /**
     * to edit tape by index and symbol
     */
    public void editTape() {
        System.out.print("Enter the symbol number and new symbol: ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt() - 1;
        if (x < 0 || x >= tape.getLength()) {
            System.out.println("Invalid index");
            return;
        }
        tape.replaceSymbol(x, scanner.next().charAt(0));
    }

    /**
     * shows tape and states in console
     */
    public void showTape() {
        System.out.print("Your tape: ");
        for (int i = 0; i < tape.getLength(); i++) {
            System.out.print("[ " + tape.getTape().charAt(i) + " ]");
        }
        System.out.println();
        for (int i = 0; i < 5 * carriage.getCurrentIndex() + 13; i++) {
            System.out.print(' ');
        }
        System.out.println('^');
        System.out.println("Current state: q"+carriage.getCurrentState()+" Final state: q"+carriage.getFinalState());
    }

    /**
     * to add rules in console
     */
    public void addRuleToRuleSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type new rule: ");
        ruleSet.addRule(scanner.nextLine());
    }

    /**
     * to add rules from File
     * @param rule string from File
     */
    public void addRuleToRuleSet(String rule) {
        ruleSet.addRule(rule);
    }

    /**
     * to delete rule from set of rules
     */
    public void delRuleFromRuleSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type rule to delete: ");
        ruleSet.delRule(scanner.next());
    }

    /**
     * shows rules in console
     */
    public void showRulesFromRuleSet() {
        System.out.println("List of rules:");
        ruleSet.showRules();
    }

    /**
     * to set final set to carriage
     */
    public void setFinalStateToCarriage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the number of final state: ");
        carriage.setFinalState(scanner.nextInt());
    }

    /**
     * to set current index to carriage
     */
    public void setCurrentIndexForCarriage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter index of carriage on tape: ");
        int x = scanner.nextInt();
        if (x <= 0 || x > tape.getLength()) {
            System.out.println("Invalid index");
            return;
        }
        carriage.setCurrentIndex(x - 1);
    }

    /**
     * to set current state to carriage
     */
    public void setCurrentStateForCarriage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of state to carriage: ");
        carriage.setCurrentState(scanner.nextInt());
    }

    /**
     * makes one step
     */
    public void stepImplementation() {
        if (tape.getTape().toString().isEmpty()) {
            System.out.println("Your tape is empty");
            return;
        }
        if (carriage.getFinalState() == 0) {
            System.out.println("Inappropriate terminal state");
            return;
        }
        if (!carriage.isOnFinalState()) {
            carriage.move(tape, ruleSet);
        } else {
            System.out.println("Machine has finished work");
        }
        if (logFlag) {
            System.out.println("Tape: " + tape.getTape() + " Current index: " + carriage.getCurrentIndex() + " Current state: q" + carriage.getCurrentState() + " Final state: q" + carriage.getFinalState());
        }
    }

    /**
     * makes steps until current state not equal to final step
     */
    public void startWork() {
        if (carriage.getFinalState() == 0) {
            System.out.println("Inappropriate terminal state");
            return;
        }
        while (!carriage.isOnFinalState()) {
            if (logFlag)
                System.out.println("Tape: " + tape.getTape() + " Current index: " + carriage.getCurrentIndex() + " Current state: q" + carriage.getCurrentState() + " Final state: q" + carriage.getFinalState());
            carriage.move(tape, ruleSet);
        }
        if (logFlag)
            System.out.println("Tape: " + tape.getTape() + " Current index: " + carriage.getCurrentIndex() + " Current state: q" + carriage.getCurrentState() + " Final state: q" + carriage.getFinalState());
        System.out.println("Machine has finished work");
    }
}
