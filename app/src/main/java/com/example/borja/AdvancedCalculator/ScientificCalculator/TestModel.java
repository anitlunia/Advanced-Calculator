package com.example.borja.AdvancedCalculator.ScientificCalculator;

import com.example.borja.AdvancedCalculator.CalculatorArithmetic;
import com.example.borja.AdvancedCalculator.ScientificCalculator.CalculatorModel;

import junit.framework.TestCase;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.Cos;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.Sin;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.Sqrt;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.Tan;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Addition;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Division;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Product;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Subtraction;

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
        model.doOperations();

        String result = model.readScreen();
        assertEquals("The result should be four", "4", result);
    }

    public void testDivideByZeroGivesError() {
        model.inputValue("1");
        model.inputOperation(Division);
        model.inputValue("0");
        model.doOperations();
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




    public void testoperations12() {
        model.inputValue("12");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.inputOperation(Product);
        String result = model.readScreen();
        assertEquals("The second operation must override the first", "3", result);
    }

    public void testoperations() {
        model.inputValue("12");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.inputOperation(Product);
        model.inputValue("6");
        model.doOperations();
        String result = model.readScreen();
        assertEquals("Do operation", "30", result);
    }

    public void testoperations3() {
        model.inputValue("12");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.inputOperation(Product);
        model.inputValue("6");
        model.inputOperation(Product);
        String result = model.readScreen();
        assertEquals("Do operation", "18", result);
    }

    public void testoperations4() {
        model.inputValue("12");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.inputOperation(Product);
        model.inputValue("6");
        model.inputOperation(Product);
        model.inputValue("2");
        model.doOperations();
        String result = model.readScreen();
        assertEquals("Do operation", "48", result);
    }

    public void testoperations5() {
        model.openParenthesis();
        model.inputValue("12");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.closeParenthesis();
        String result = model.readScreen();
        assertEquals("Do operation", "15", result);
    }

    public void testoperations6() {
        model.inputValue("5");
        model.inputOperation(Product);
        model.openParenthesis();
        model.inputValue("8");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.closeParenthesis();
        String result = model.readScreen();
        assertEquals("Do operation", "11", result);
    }
    public void testoperations7() {
        model.inputValue("5");
        model.inputOperation(Product);
        model.openParenthesis();
        model.inputValue("8");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.closeParenthesis();
        model.doOperations();
        String result = model.readScreen();
        assertEquals("Do operation", "55", result);
    }
    public void testfunction1() {
        model.openParenthesis();
        model.inputValue("0");
        model.inputOperation(Subtraction);
        model.inputValue("6");
        model.closeParenthesis();
        model.inputFunction(Sqrt);
        String result = model.readScreen();
        assertEquals("Do function", ERROR_SCREEN, result);
    }

    public void testfunction1_2() {
        model.openParenthesis();
        model.inputValue("6");
        model.closeParenthesis();
        model.inputOperation(Division);
        model.inputValue("0");
        model.doOperations();
        String result = model.readScreen();
        assertEquals("Do function", ERROR_SCREEN, result);
    }



    public void testfunction2() {
        model.inputValue("3.14");
        model.inputFunction(Sin);
        String result = model.readScreen();
        assertEquals("Do function", "0.0015926", result);
    }

    public void testfunction3() {
        model.inputValue("3.14");
        model.inputFunction(Cos);
        String result = model.readScreen();
        assertEquals("Do function", "-0.9999987", result);
    }

    public void testfunction4() {
        model.inputValue("3.14");
        model.inputFunction(Tan);
        String result = model.readScreen();
        assertEquals("Do function", "-0.0015926", result);
    }

    public void testoperations8() {
        model.inputValue("12");
        model.inputOperation(Addition);
        model.inputValue("3");
        model.clear();
        model.inputValue("5");
        model.doOperations();
        String result = model.readScreen();
        assertEquals("Do operation", "5", result);
    }




}
