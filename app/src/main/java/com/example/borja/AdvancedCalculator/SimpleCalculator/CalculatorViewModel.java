package com.example.borja.AdvancedCalculator.SimpleCalculator;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Addition;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Division;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Product;
import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations.Subtraction;

/**
 * Created by Oxana on 05/02/2015.
 */
public class CalculatorViewModel{
    private final ICalculatorModel model;
    private  ICalculatorView view;

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
        screen = model.readScreen();
        nextNumber = "T";
        operating = "F";
        model.resetModel();

    }

    public String addMemory(){
        if(!screen.equals("Error")) {
            model.memoryAdd(screen);
            nextNumber = "T";
            return screen;
        }
        return "Error";
    }

    public String subMemory(){
        if(!screen.equals("Error")) {
            model.memorySub(screen);
            nextNumber = "T";
            return screen;
        }
        return "Error";
    }

    public String showMemory(){
        screen = model.memoryShow();
        return screen;
    }

    public void clearMemory(){
        model.memoryClear();
    }

    public void setState(String state){
        String[] aux = state.split(":");
        nextNumber = aux[0];
        screen = aux[1];
        operating = aux[2];
        model.setState(aux[3] + ":" + aux[4] + ":" + aux[5]);
    }

    public String getState(){
        String aux = "" + nextNumber + ":" + screen + ":" + operating + ":" + model.getState();
        return aux;
    }
}
