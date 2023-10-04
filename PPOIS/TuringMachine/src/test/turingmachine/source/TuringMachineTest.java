package turingmachine.source;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TuringMachineTest {

    public static TuringMachine turingMachine = new TuringMachine();

    @Test
    void setTape() {
    }

    @Test
    void resetTape() {
    }

    @Test
    void editTape() {
    }

    @Test
    void showTape() {
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "q01->q01R",
            "q0x->q1xR",
            "q0*->q0*R",
            "q11->q2aR",
            "q21->q21L",
            "q2x->q3xL",
            "q2=->q2=L",
            "q2a->q2aL",
            "q31->q4aR",
            "q3a->q3aL",
            "q3*->q6*R",
            "q41->q41R",
            "q4x->q4xR",
            "q4=->q4=R",
            "q4a->q4aR",
            "q4*->q51R",
            "q5 ->q2*L",
            "q6x->q7xR",
            "q6a->q61R",
            "q71->q2aR",
            "q7=->q8=L",
            "q7a->q7aR",
            "q8x->q9xN",
            "q8a->q81L"
    })
    void addRuleToRuleSet(String userInput) {

    }

    @Test
    void delRuleFromRuleSet() {
    }

    @Test
    void showRulesFromRuleSet() {
    }

    @Test
    void setFinalStateToCarriage() {
    }

    @Test
    void setCurrentIndexForCarriage() {
    }

    @Test
    void startWork() {

    }
}