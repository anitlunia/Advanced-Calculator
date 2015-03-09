package com.example.borja.AdvancedCalculator.SimpleCalculator;


import com.example.borja.AdvancedCalculator.CalculatorArithmetic;

import java.math.BigDecimal;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.*;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Addition;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Subtraction;

/**
 * Created by Oxana on 05/02/2015.
 */
public class CalculatorModel implements ICalculatorModel{

    BigDecimal lastResult = null;
    Operations pendingOp = null;

    String error = "Error";

    private BigDecimal _memory = new BigDecimal(0);

    @Override
    public void inputValue(String number) {
        if(pendingOp != null){
            if (number != "") {
                lastResult = operate(lastResult, pendingOp, new BigDecimal(number));

            }

        }
        else {
            if(number == "Error" && number =="")
                lastResult = new BigDecimal(0);
            else
                lastResult = new BigDecimal(number);
        }
    }

    @Override
    public void inputOperation(Operations op) {
        pendingOp = op;
    }

    @Override
    public String readScreen() {
        String result = CalculatorArithmetic.toString(lastResult, error);
        return result;
    }

    @Override
    public void resetModel() {
        //lastResult = null;
        pendingOp = null;
    }

    @Override
    public void setState(String state) {
        String[] aux = state.split(":");

        lastResult = new BigDecimal(aux[0]);


        if(aux[1] == "!")
            pendingOp = null;
        if(aux[1] == "+")
            pendingOp = Operations.Addition;
        if(aux[1] == "-")
            pendingOp = Operations.Subtraction;
        if(aux[1] == "*")
            pendingOp = Operations.Product;
        if(aux[1] == "/")
            pendingOp = Operations.Division;

        _memory = new BigDecimal(aux[2]);
    }

    @Override
    public String getState() {
        String auxOp = "";
        String auxNumb = "";
        String auxMem = _memory.toString();
        if(lastResult == null)
            auxNumb = "0";
        else
            auxNumb = lastResult.toString();
        if(pendingOp == null)
            return auxNumb + ":!:" + auxMem;
        switch (pendingOp) {
            case Addition:
                auxOp = "+";
                break;
            case Subtraction:
                auxOp = "-";
                break;
            case Product:
                auxOp = "*";
                break;
            case Division:
                auxOp = "/";
                break;
        }

        return auxNumb + ":" + auxOp  + ":" + auxMem;
    }

    @Override
    public String memoryAdd(String value){
        if(value.equals("Error"))
            value = "0";
        _memory = operate(_memory, Addition, new BigDecimal(value));
        return  CalculatorArithmetic.toString(_memory, error);
    }

    @Override
    public String memorySub(String value){
        if(value.equals("Error"))
            value = "0";
        _memory = operate(_memory, Subtraction, new BigDecimal(value));
        return  CalculatorArithmetic.toString(_memory, error);
    }

    @Override
    public String memoryShow(){
        return  CalculatorArithmetic.toString(_memory, error);
    }

    @Override
    public void memoryClear(){
        _memory = new BigDecimal(0);
    }
}
