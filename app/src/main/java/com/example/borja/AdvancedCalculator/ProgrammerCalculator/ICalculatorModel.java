package com.example.borja.AdvancedCalculator.ProgrammerCalculator;

import com.example.borja.AdvancedCalculator.CalculatorArithmetic;

import java.math.BigDecimal;

import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations;

/**
 * Created by Hecate on 26/02/2015.
 */
public interface ICalculatorModel {



    void inputValue(String value, int radix);
    void inputOperation(Operations operation);
    String readScreen(int radix);
    String getState();
    void setState(String state);
}
