import turingmachine.source.TuringMachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int x;
        boolean continueWork = true;

        Scanner scanner = new Scanner(System.in);

        TuringMachine turingMachine = new TuringMachine();
        turingMachine.showTape();

        while (continueWork) {
            System.out.print("1 - add a rule\n");
            System.out.print("2 - delete a rule\n");
            System.out.print("3 - show rules\n");
            System.out.print("4 - show tape\n");
            System.out.print("5 - set tape\n");
            System.out.print("6 - edit tape\n");
            System.out.print("7 - set terminal state to carriage\n");
            System.out.print("8 - start the machine\n");
            System.out.print("9 - exit\n");
            x = scanner.nextInt();
            switch (x) {
                case 1 -> {
                    clear();
                    turingMachine.addRuleToRuleSet();
                }
                case 2 -> {
                    clear();
                    turingMachine.delRuleFromRuleSet();
                }
                case 3 -> {
                    clear();
                    turingMachine.showRulesFromRuleSet();
                }
                case 4 -> {
                    clear();
                    turingMachine.showTape();
                }
                case 5 -> {
                    clear();
                    turingMachine.resetTape();
                }
                case 6 -> {
                    clear();
                    turingMachine.editTape();
                }
                case 7 -> {
                    clear();
                    turingMachine.setFinalStateToCarriage();
                }
                case 8 -> {
                    clear();
                    turingMachine.startWork();
                }
                case 9 -> {
                    continueWork = false;
                }
                default -> {
                    clear();
                    System.out.println("Try again");
                }
            }
        }

    }

    public static void clear(){
        System.out.print("\033[H\033[2J"); // to clear bash terminal
        System.out.flush();
    }
}



/*turingMachine.addRuleToRuleSet("q01->q01R");
        turingMachine.addRuleToRuleSet("q0x->q1xR");
        turingMachine.addRuleToRuleSet("q0*->q0*R");
        turingMachine.addRuleToRuleSet("q11->q2aR");
        turingMachine.addRuleToRuleSet("q21->q21L");
        turingMachine.addRuleToRuleSet("q2x->q3xL");
        turingMachine.addRuleToRuleSet("q2=->q2=L");
        turingMachine.addRuleToRuleSet("q2a->q2aL");
        turingMachine.addRuleToRuleSet("q31->q4aR");
        turingMachine.addRuleToRuleSet("q3a->q3aL");
        turingMachine.addRuleToRuleSet("q3*->q6*R");
        turingMachine.addRuleToRuleSet("q41->q41R");
        turingMachine.addRuleToRuleSet("q4x->q4xR");
        turingMachine.addRuleToRuleSet("q4=->q4=R");
        turingMachine.addRuleToRuleSet("q4a->q4aR");
        turingMachine.addRuleToRuleSet("q4*->q51R");
        turingMachine.addRuleToRuleSet("q5 ->q2*L");
        turingMachine.addRuleToRuleSet("q6x->q7xR");
        turingMachine.addRuleToRuleSet("q6a->q61R");
        turingMachine.addRuleToRuleSet("q71->q2aR");
        turingMachine.addRuleToRuleSet("q7=->q8=L");
        turingMachine.addRuleToRuleSet("q7a->q7aR");
        turingMachine.addRuleToRuleSet("q8x->q9xN");
        turingMachine.addRuleToRuleSet("q8a->q81L");
*/

/* swap 0 and 1
q00->q01R
q01->q00R
q0 ->q9 N
*/
