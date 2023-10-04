package turingmachine.source.parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RuleSetTest {

    private static final RuleSet ruleSet = new RuleSet();

    @BeforeAll
    public static void setUp(){
        ruleSet.addRule("q01->q10R");
    }

    @Test
    void isRuleExist() {
        Assertions.assertTrue(ruleSet.isRuleExist(0,'1'));
    }

    @Test
    void getWriteableSymbolOfRule() {
        Assertions.assertEquals('0', ruleSet.getWriteableSymbolOfRule(0,'1'));
    }

    @Test
    void getShiftOfRule() {
        Assertions.assertEquals('R', ruleSet.getShiftOfRule(0,'1'));
    }

    @Test
    void getTransitionalStateOfRule() {
        Assertions.assertEquals(1, ruleSet.getTransitionalStateOfRule(0,'1'));
    }

    @Test
    public void addRule() {
        Assertions.assertEquals(1, ruleSet.addRule("q11->q20R"));
        Assertions.assertEquals(0, ruleSet.addRule("q11->q20R"));
    }

    @Test
    void delRule() {
        Assertions.assertEquals(1, ruleSet.delRule("q11->q20R"));
        Assertions.assertEquals(0, ruleSet.delRule("q11->q20R"));
    }
}