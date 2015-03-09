package com.example.borja.AdvancedCalculator.SimpleCalculator;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.*;

/**
 * Created by Oxana on 05/02/2015.
 */
public interface ICalculatorModel {
    void inputValue(String number);
    void inputOperation(Operations op);

    String readScreen();
    void resetModel();
    String getState();
    void setState(String state);

    String memoryAdd(String value);
    String memorySub(String value);
    String memoryShow();
    void memoryClear();
}
