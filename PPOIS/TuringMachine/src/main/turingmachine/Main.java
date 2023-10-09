package turingmachine;

import turingmachine.source.TuringMachine;
import turingmachine.source.parts.Alphabet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        TuringMachine turingMachine;
        StringBuilder fileName = new StringBuilder();
        StringBuilder log = new StringBuilder();
        int x;
        boolean continueWork = true;

        switch (args.length){
            case 0 -> {
                System.out.println("File path is required");
                return;
            }
            case 1 -> fileName.append(args[0]);
            default -> {
                fileName.append(args[0]);
                log.append(args[1]);
            }
        }
        if(!Files.exists(Path.of(fileName.toString()))){
            System.out.println("No such file");
            return;
        }

        Path path = Path.of(fileName.toString());
        List<String> list = Files.readAllLines(path);

        if (log.toString().equals("-log"))
            turingMachine = new TuringMachine(true);
        else
            turingMachine = new TuringMachine(false);


        Alphabet.setAlphabet(list.get(0));
        turingMachine.setTape(list.get(1));

        for (int i = 2; i < list.size(); i++) {
            turingMachine.addRuleToRuleSet(list.get(i));
        }

        Scanner scanner = new Scanner(System.in);

        turingMachine.showTape();


        while (continueWork) {
            System.out.print("1 - add a rule\n");
            System.out.print("2 - delete a rule\n");
            System.out.print("3 - show rules\n");
            System.out.print("4 - show tape\n");
            System.out.print("5 - set tape\n");
            System.out.print("6 - edit tape\n");
            System.out.print("7 - set terminal state to carriage\n");
            System.out.print("8 - set current state to carriage\n");
            System.out.print("9 - set current index to carriage on tape\n");
            System.out.print("10 - step implementation\n");
            System.out.print("11 - start the machine\n");
            System.out.print("12 - exit\n");
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
                    turingMachine.setTape();
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
                    turingMachine.setCurrentStateForCarriage();
                }
                case 9 -> {
                    clear();
                    turingMachine.setCurrentIndexForCarriage();
                }
                case 10 ->{
                    clear();
                    turingMachine.stepImplementation();
                }
                case 11 -> {
                    clear();
                    turingMachine.startWork();
                }
                case 12 -> continueWork = false;
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

