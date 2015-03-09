package com.example.borja.AdvancedCalculator;

import com.example.borja.AdvancedCalculator.SimpleCalculator.CalculatorModel;

import junit.framework.TestCase;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.*;

/**
 * Created by Oxana on 05/02/2015.
 */
public class TestModel extends TestCase {
    CalculatorModel model;
    static final String ERROR_SCREEN = "Error";

    protected void setUp() throws Exception {
        super.setUp();
        model = new CalculatorModel();
    }

    public void testAddTwoPlusTwo() {
        model.inputValue("2");
        model.inputOperation(Addition);
        model.inputValue("2");

        String result = model.readScreen();
        assertEquals("The result should be four", "4", result);
    }

    public void testDivideByZeroGivesError() {
        model.inputValue("1");
        model.inputOperation(Division);
        model.inputValue("0");
        String result = model.readScreen();
        assertEquals("Division by zero should be an error", ERROR_SCREEN, result);
    }

    public void testDivideByZeroGivesError_2() {
        model.inputValue("1");
        model.inputOperation(Division);
        model.inputValue("0");
        model.inputOperation(Addition);
        model.inputValue("3");
        String result = model.readScreen();
        assertEquals("Division by zero should be an error", ERROR_SCREEN, result);
    }



    public void testChangeOperationKeepsSecond() {
        model.inputValue("1");
        model.inputOperation(Addition);
        model.inputOperation(Product);
        model.inputValue("3");
        String result = model.readScreen();
        assertEquals("The second operation must override the first", "3", result);
    }

    public void testoperations() {
        model.inputValue("1");
        model.inputOperation(Addition);
        model.inputValue("4");
        model.inputOperation(Subtraction);
        model.inputValue("3");
        String result = model.readScreen();
        assertEquals("The second operation must override the first", "2", result);
    }

    /*public void testoperations_1() {
        model.inputValue("1");
        model.inputValue("2");
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "12", result);
    }*/

    public void testoperations_2() {
        model.inputValue("0");
        model.inputValue("0");
        model.inputValue("0");
        model.inputValue("1");
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "1", result);
    }
    public void testoperations_3() {
        model.inputValue("12");
        model.inputOperation(Addition);
        model.inputValue("34");
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "46", result);
    }
    public void testoperations_4() {
        model.inputValue("1");
        model.inputOperation(Division);
        model.inputValue("3");
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "0.3333333", result);
    }
    public void testoperations_5() {
        model.inputValue("34");
        model.inputOperation(Addition);
        model.inputValue("23");
        model.inputOperation(Division);
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "57", result);
    }
    public void testoperations_6() {
        model.inputValue("34");
        model.inputOperation(Addition);
        model.inputValue("23");
        model.inputOperation(Division);
        model.inputValue("7");
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "8.1428571", result);
    }
    public void testoperations_7() {
        model.inputValue("3");
        model.inputOperation(Addition);
        model.inputOperation(Product);
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "3", result);
    }
    public void testoperations_8() {
        model.inputValue("3");
        model.inputOperation(Addition);
        model.inputOperation(Product);
        model.inputValue("5");
        String result = model.readScreen();
        assertEquals("This tests only is to make sure values are right displayed on the screen", "15", result);
    }
    public void testoperations_9() {
        model.inputValue("1000");
        model.inputOperation(Product);
        model.inputValue("1000");
        model.inputOperation(Product);
        model.inputValue("1000");
        model.inputOperation(Product);
        String result = model.readScreen();
        assertEquals("Error", ERROR_SCREEN, result);
    }
    public void testoperations_10() { //este test es brujeria
        model.inputValue("3");
        model.inputOperation(Product);
        model.inputOperation(Product);
        model.inputOperation(Product);

        String result = model.readScreen();
        assertEquals("Do nothing", "3", result);
    }
}
