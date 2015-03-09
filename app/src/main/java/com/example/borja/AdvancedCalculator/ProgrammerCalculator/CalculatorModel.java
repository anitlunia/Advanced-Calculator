package com.example.borja.AdvancedCalculator.ProgrammerCalculator;

import com.example.borja.AdvancedCalculator.CalculatorArithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.*;
import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations.*;

/**
 * Created by Hecate on 26/02/2015.
 */
public class CalculatorModel implements ICalculatorModel{

    BigInteger lastResult = null;
    Operations pendingOp = null;

    String error = "Error";


    @Override
    public void inputValue(String value, int radix) {
        if(pendingOp != null){
            if (value != "") {
                lastResult = operate(lastResult, pendingOp, new BigInteger(value,radix));

            }

        }
        else {
            if(value == "Error" && value =="")
                lastResult = new BigInteger("0",radix);
            else
                lastResult = new BigInteger(value,radix);
        }


    }

    @Override
    public void inputOperation(Operations operation) {
        pendingOp= operation;

    }

    @Override
    public String readScreen(int radix) {
        String result = lastResult.toString(radix).toUpperCase();
        return result;
    }

    @Override
    public String getState() {
        String auxOp = "";
        String auxNumb = "";
        if(lastResult == null)
            auxNumb = "0";
        else
            auxNumb = lastResult.toString();
        if(pendingOp == null)
            return auxNumb + ":!";
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
            case LogicalAnd:
                auxOp = "A";
                break;
            case LogicalOr:
                auxOp = "O";
                break;
        }

        return auxNumb + ":" + auxOp;
    }

    @Override
    public void setState(String state) {
        String[] aux = state.split(":");
        int mode = Integer.parseInt(aux[2]);
        lastResult = new BigInteger(aux[0], mode);

        switch (aux[1]){
            case "!":
                pendingOp = null;
                break;
            case "+":
                pendingOp = Addition;
                break;
            case "-":
                pendingOp = Subtraction;
                break;
            case "*":
                pendingOp = Product;
                break;
            case "/":
                pendingOp = Subtraction;
                break;
            case "A":
                pendingOp = LogicalAnd;
                break;
            case "O":
                pendingOp = LogicalOr;
                break;

        }
    }

}
