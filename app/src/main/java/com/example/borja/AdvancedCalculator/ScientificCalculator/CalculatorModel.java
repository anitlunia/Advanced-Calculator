package com.example.borja.AdvancedCalculator.ScientificCalculator;

import com.example.borja.AdvancedCalculator.CalculatorArithmetic;
import com.example.borja.AdvancedCalculator.OperationStack;

import java.math.BigDecimal;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.*;


/**
 * Created by Hecate on 19/02/2015.
 */
public class CalculatorModel implements ICalculatorModel{
    private OperationStack elements = new OperationStack();
    private static final int ADDITIVE_PRIORITY = 1;
    private static final int MULTIPLICATIVE_PRIORITY = 2;
    private static final int NO_PRIORITY = -100;
    private static final int PARENTHESIS_PRIORITY = 0;
    private boolean error = false;
    private static final String ERROR_STRING = "Error";

    public String result = "";


    private static int precedence(Operations operation) {
        switch (operation) {
            case Addition: return ADDITIVE_PRIORITY;
            case Subtraction: return ADDITIVE_PRIORITY;
            case Product: return MULTIPLICATIVE_PRIORITY;
            case Division: return MULTIPLICATIVE_PRIORITY;
        }
        return NO_PRIORITY;
    }

    @Override
    public void inputValue(String value) {
        if(elements.isTopNumber())
            elements.popNumber();

        elements.push(new BigDecimal(value));
    }

    @Override
    public void inputOperation(Operations operation) {
        if(elements.isTopOperation()) {
            elements.popOperation();

        }
        reduce(precedence(operation));
        result =CalculatorArithmetic.toString(elements.getTopNumber(), ERROR_STRING) ;
        elements.push(operation);


    }

    public void reduce (int priority){
        if (elements.isTopNumber()){
            while (elements.getTopOperation() != null && precedence(elements.getTopOperation()) >= priority  ){
                BigDecimal aux1 = elements.getTopNumber();
                elements.popNumber();
                CalculatorArithmetic.Operations last_OP = elements.getTopOperation();
                elements.popOperation();
                BigDecimal aux2 = elements.getTopNumber();
                elements.popNumber();
                elements.push(CalculatorArithmetic.operate(aux2,last_OP,aux1));


            }
        }
    }

    @Override
    public void inputFunction(Functions function) {
        BigDecimal aux ;
        if (elements.isTopNumber() ){

            aux = elements.getTopNumber();
            elements.popNumber();
            aux =CalculatorArithmetic.applyFunction(function, aux);
            elements.push(aux);
        }
        result =CalculatorArithmetic.toString(elements.getTopNumber(), ERROR_STRING) ;
    }

    @Override
    public void openParenthesis() {
        if(elements.isTopNumber())
            elements.popNumber();

        elements.pushParenthesis();
    }

    @Override
    public void closeParenthesis() {

        reduce(PARENTHESIS_PRIORITY);
        BigDecimal aux = elements.getTopNumber();
        elements.popNumber();
        if (elements.isTopParenthesis()){
            elements.popParenthesis();
            elements.push(aux);
        }

        else{
            error = true;
        }
        if (error) {
            result = ERROR_STRING;

        }
        else
            result =CalculatorArithmetic.toString(elements.getTopNumber(), ERROR_STRING) ;
    }

    @Override
    public void clear() {
        elements.clear();
        error = false;
    }

    @Override
    public void doOperations() {

        reduce(NO_PRIORITY);
        result = CalculatorArithmetic.toString(elements.getTopNumber(),ERROR_STRING);
        clear();

    }

    @Override
    public String readScreen() {
        return result;
    }

    @Override
    public String getState() {
        return elements.toString() + ":" + result;
    }

    @Override
    public void setState(String state) {
        String[] aux = state.split(":");

        elements = new OperationStack(aux[0]);
        result = aux[1];

    }
}
