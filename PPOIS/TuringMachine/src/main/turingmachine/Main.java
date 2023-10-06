package turingmachine;

import turingmachine.source.TuringMachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // java /.../ turingmachine.Main filePath/... -log
        StringBuilder path = new StringBuilder();
        StringBuilder log = new StringBuilder();
        switch (args.length){
            case 0 -> System.out.println("...");
            case 1 -> {path.append(args[0]);}
            default -> {
                path.append(args[0]);
                log.append(args[1]);
            }
        }

        System.out.println(path);
        System.out.println(log);

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
                case 9 -> continueWork = false;
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

/* swap 0 and 1
q00->q01R
q01->q00R
q0 ->q9 N
*/
