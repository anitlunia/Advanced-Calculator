package com.example.borja.AdvancedCalculator.ScientificCalculator;




import com.example.borja.AdvancedCalculator.CalculatorArithmetic;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.Cos;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.DegCos;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.DegSin;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Functions.DegTan;
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
public class CalculatorViewModel {
    private final com.example.borja.AdvancedCalculator.ScientificCalculator.ICalculatorModel model;
    private final  ICalculatorView view;
    private String nextNumber = "F";
    public String operating = "F";

    public String screen = "0";

    public CalculatorViewModel(ICalculatorModel model, ICalculatorView view){
        this.model = model;
        this.view = view;

    }

    public void onNumericKeyPressed(char digit){
        if(nextNumber == "T" || screen.equals("0")){
            screen = "";
            nextNumber = "F";
        }

        screen += digit;
    }
    public void onDecimalPointPressed(){
        if (screen.contains(".") == false ){
            screen += ".";
            //Log.d("CHACHICALCULATOR","readScreenchachi:"+ model.readScreen());
        }
    }

    public void onOperatorKeyPressed(char opChar){
        if(nextNumber.equals("F"))
            model.inputValue(screen);
        switch (opChar){
            case '+':
                model.inputOperation(Addition);
                break;
            case '-':
                model.inputOperation(Subtraction);
                break;
            case '*':
                model.inputOperation(Product);
                break;
            case '/':
                model.inputOperation(Division);
                break;
        }
        //screen = "";



        if (!operating.equals("T")) {

            screen = model.readScreen();
            nextNumber = "T";
        }
        else
            operating = "T";
    }



    public void onEqualKeyPressed(){

        if(!screen.equals("Error"))
            model.inputValue(screen);
        model.doOperations();
        screen = model.readScreen();
        nextNumber = "T";
        operating = "F";

    }

    public  void  onOpenParenthesisPressed(){
        model.openParenthesis();
        nextNumber = "T";

    }
    public void onCloseParenthesisPressed(){
        model.closeParenthesis();
        screen = model.readScreen();
        nextNumber = "T";
    }

    public  void onFunctionKeyPressed(String function){
        if (function.equals("SIN")) {
            model.inputFunction(Sin);
        }
        if (function.equals("COS")) {
            model.inputFunction(Cos);
        }
        if (function.equals("TAN")) {
            model.inputFunction(Tan);
        }
        if (function.equals("DEGSIN")) {
            model.inputFunction(DegSin);
        }
        if (function.equals("DEGCOS")) {
            model.inputFunction(DegCos);
        }
        if (function.equals("DEGTAN")) {
            model.inputFunction(DegTan);
        }
        if (function.equals("SQRT")) {
            model.inputFunction(Sqrt);
        }

        screen = model.readScreen();
    }


    public void onClearPressed(){
        screen = "0";

    }
    public void onAllClearPressed(){
        onClearPressed();
        model.clear();
        operating = "F";
        nextNumber = "F";
    }

    public void setState(String state){
        String[] aux = state.split(":");
        nextNumber = aux[0];
        screen = aux[1];
        operating = aux[2];
        model.setState(aux[3] + ":" + aux[4]);
    }

    public String getState(){
        String aux = "" + nextNumber + ":" + screen + ":" + operating + ":" + model.getState();
        return aux;
    }
}
