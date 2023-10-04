package turingmachine.source;

import turingmachine.source.parts.Carriage;
import turingmachine.source.parts.RuleSet;
import turingmachine.source.parts.Tape;

import java.util.Scanner;

public class TuringMachine {
    public final Tape tape = new Tape();
    private final RuleSet ruleSet = new RuleSet();
    private final Carriage carriage = new Carriage();

    public TuringMachine(){
        setTape();
        setFinalStateToCarriage();
    }

    public void setTape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set the values on tape: ");
        this.tape.setTape(scanner.nextLine());
    }
    public void resetTape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new tape:\n" + tape.getTape() + "\r");
        this.tape.setTape(scanner.nextLine());
    }

    public void editTape(){
        System.out.print("Enter the symbol number and new symbol: ");
        Scanner scanner = new Scanner(System.in);
        tape.replaceSymbol(scanner.nextInt()-1, scanner.next().charAt(0));
    }

    public void showTape(){
        System.out.print("Your tape: ");
        for (int i = 0; i< tape.getLength(); i++){
            System.out.print("[ "+tape.getTape().charAt(i)+" ]");
        }
        System.out.println();
    }

    public void addRuleToRuleSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type new rule: ");
        ruleSet.addRule(scanner.nextLine());
    }

    public void delRuleFromRuleSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type rule to delete: ");
        ruleSet.delRule(scanner.next());
    }

    public void showRulesFromRuleSet(){
        System.out.println("List of rules:");
        ruleSet.showRules();
    }

    public void setFinalStateToCarriage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the number of final state: ");
        carriage.setFinalState(scanner.nextInt());
    }

    public void setCurrentIndexForCarriage(){
        Scanner scanner = new Scanner(System.in);
        carriage.setCurrentIndex(scanner.nextInt());
    }

    public void startWork() {
        if (tape.getTape().toString().isEmpty()) {
            System.out.println("Your tape is empty");
            return;
        }
        if (carriage.getFinalState() == 0) {
            System.out.println("Inappropriate terminal state");
            return;
        }
        while (!carriage.isOnFinalState()) {
            carriage.move(tape, ruleSet);
        }
    }
}
