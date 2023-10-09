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

    public TuringMachine(boolean logFlag){
        this.logFlag = logFlag;
    }

    public void setTape(String tape) {
        this.tape.setTape(tape);
    }
    public void setTape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new tape: ");
        String string = scanner.nextLine();
        while(!Alphabet.isNormalTape(string)) {
            System.out.print("Tape must consist only of symbols in alphabet. Enter new tape: " );
            string = scanner.nextLine();
        }
        tape.setTape(string);
        carriage.setCurrentIndex(0);
    }

    public void editTape(){
        System.out.print("Enter the symbol number and new symbol: ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt()-1;
        if(x<0 || x>=tape.getLength()){
            System.out.println("Invalid index");
            return;
        }
        tape.replaceSymbol(x, scanner.next().charAt(0));
    }

    public void showTape(){
        System.out.print("Your tape: ");
        for (int i = 0; i< tape.getLength(); i++){
            System.out.print("[ "+tape.getTape().charAt(i)+" ]");
        }
        System.out.println();
        for(int i = 0; i <5*carriage.getCurrentIndex() + 13; i++){
            System.out.print(' ');
        }
        System.out.println('^');
        System.out.println();
    }

    public void addRuleToRuleSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type new rule: ");
        ruleSet.addRule(scanner.nextLine());
    }

    public void addRuleToRuleSet(String rule) {
        ruleSet.addRule(rule);
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
        System.out.print("Enter index of carriage on tape: ");
        int x = scanner.nextInt();
        if(x<=0 || x>tape.getLength()){
            System.out.println("Invalid index");
            return;
        }
        carriage.setCurrentIndex(x);
    }

    public void setCurrentStateForCarriage(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of state to carriage: ");
        carriage.setCurrentState(scanner.nextInt());
    }

    public void stepImplementation(){
        if (tape.getTape().toString().isEmpty()) {
            System.out.println("Your tape is empty");
            return;
        }
        if (carriage.getFinalState() == 0) {
            System.out.println("Inappropriate terminal state");
            return;
        }
        if(!carriage.isOnFinalState()){
            carriage.move(tape, ruleSet);
        }
        else {
            System.out.println("Machine has finished work");
        }
        if(logFlag) {
            System.out.println("Tape: " + tape.getTape() + " Current index: " + carriage.getCurrentIndex() + " Current state: q" + carriage.getCurrentState() + " Final state: q" + carriage.getFinalState());
        }
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
            if(logFlag)
                System.out.println("Tape: " + tape.getTape() + " Current index: " + carriage.getCurrentIndex() + " Current state: q" + carriage.getCurrentState() + " Final state: q" + carriage.getFinalState());
            carriage.move(tape, ruleSet);
        }
        if(logFlag)
            System.out.println("Tape: " + tape.getTape() + " Current index: " + carriage.getCurrentIndex() + " Current state: q" + carriage.getCurrentState() + " Final state: q" + carriage.getFinalState());
        System.out.println("Machine has finished work");
    }
}
