package com.example.borja.AdvancedCalculator.ScientificCalculator;


import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations;

/**
 * Created by Hecate on 19/02/2015.
 */
public interface ICalculatorModel {

    void inputValue(String value);
    void inputOperation(Operations operation);
    void inputFunction(Functions function);
    void openParenthesis();
    void closeParenthesis();
    void clear();

    void doOperations();

    String readScreen();
    String getState();
    void setState(String state);
}
