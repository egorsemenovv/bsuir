package mt.source;

import mt.source.parts.*;
public class Main {
    public static void main(String[] args) {
        Tape tape = new Tape("*111×11=*");
        RuleSet ruleSet = new RuleSet();
        ruleSet.addRule("q01->q01R");
        ruleSet.addRule("q0×->q1×R");
        ruleSet.addRule("q0*->q0*R");
        ruleSet.addRule("q11->q2aR");
        ruleSet.addRule("q21->q21L");
        ruleSet.addRule("q2×->q3×L");
        ruleSet.addRule("q2=->q2=L");
        ruleSet.addRule("q2a->q2aL");
        ruleSet.addRule("q31->q4aR");
        ruleSet.addRule("q3a->q3aL");
        ruleSet.addRule("q3*->q6*R");
        ruleSet.addRule("q41->q41R");
        ruleSet.addRule("q4×->q4×R");
        ruleSet.addRule("q4=->q4=R");
        ruleSet.addRule("q4a->q4aR");
        ruleSet.addRule("q4*->q51R");
        ruleSet.addRule("q5 ->q2*L");
        ruleSet.addRule("q6×->q7×R");
        ruleSet.addRule("q6a->q61R");
        ruleSet.addRule("q71->q2aR");
        ruleSet.addRule("q7=->q8=L");
        ruleSet.addRule("q7a->q7aR");
        ruleSet.addRule("q8×->q9×N");
        ruleSet.addRule("q8a->q81L");



        ruleSet.setFinalState(9);
        ruleSet.showRules();
        Carriage carriage = new Carriage();
        carriage.startWork(tape, ruleSet);
    }
}


/* swap 0 and 1
ruleSet.addRule("q00->q01R");
ruleSet.addRule("q01->q00R");
ruleSet.addRule("q0#->q9#N");
*/
