package com.example.borja.AdvancedCalculator.ProgrammerCalculator;

import junit.framework.TestCase;

import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations.Addition;
import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations.Division;
import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations.LogicalAnd;
import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations.LogicalOr;
import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations.Product;

/**
 * Created by Hecate on 26/02/2015.
 */
public class TestModel extends TestCase {
    CalculatorModel model;
    static final String ERROR_SCREEN = "error";

    protected void setUp() throws Exception {
        super.setUp();
        model = new CalculatorModel();
    }

    public void testoperation() {
        model.inputValue("12",10);
        model.inputOperation(Addition);
        model.inputValue("3",10);
        model.inputOperation(Product);
        String result = model.readScreen(10);

        assertEquals("Test operation:", "15", result);
    }

    public void testoperation2() {
        model.inputValue("12",10);
        model.inputOperation(Addition);
        model.inputValue("3",10);

        model.inputOperation(Product);
        model.inputValue("6",10);

        String result = model.readScreen(10);


        assertEquals("Test operation:", "90", result);
    }
    public void testoperation3() {
        model.inputValue("12",10);
        model.inputOperation(Addition);
        model.inputValue("3",10);

        model.inputOperation(Product);
        model.inputValue("6",10);

        String result = model.readScreen(16);


        assertEquals("Test operation:", "5A", result);
    }

    public void testoperation4() {
        model.inputValue("12",10);
        model.inputOperation(Division);
        model.inputValue("5",10);
        String result = model.readScreen(10);
        assertEquals("Test operation:", "2", result);
    }

    public void testoperation5() {
        model.inputValue("9",16);
        model.inputOperation(LogicalAnd);
        model.inputValue("12",16);
        String result = model.readScreen(16);
        assertEquals("Test operation:", "0", result);
    }
    public void testoperation6() {
        model.inputValue("9",16);
        model.inputOperation(LogicalOr);
        model.inputValue("12",16);
        String result = model.readScreen(16);
        assertEquals("Test operation:", "1B", result);
    }

    public void testoperation7() {
        model.inputValue("12",10);
        model.inputOperation(Product);
        model.inputValue("12",10);
        String result = model.readScreen(10);
        assertEquals("Test operation:", "144", result);
    }

    public void testoperation8() {
        model.inputValue("12",10);
        model.inputOperation(Product);
        model.inputValue("12",10);
        String result = model.readScreen(8);
        assertEquals("Test operation:", "220", result);
    }

    public void testoperation9() {
        model.inputValue("12",10);
        model.inputOperation(Product);
        model.inputValue("12",10);
        String result = model.readScreen(16);
        assertEquals("Test operation:", "90", result);
    }

}
