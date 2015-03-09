package com.example.borja.AdvancedCalculator.ProgrammerCalculator;



import static com.example.borja.AdvancedCalculator.ProgrammerCalculator.ProgrammerArithmetic.Operations;

/**
 * Created by Oxana on 05/02/2015.
 */
public class CalculatorViewModel {
    private final com.example.borja.AdvancedCalculator.ProgrammerCalculator.ICalculatorModel model;
    private  final ICalculatorView view;

    private String nextNumber = "F";
    public String operating = "F";
    public String screen = "0";

    public static final String HEX_MODE = "hex";
    public static final String DEC_MODE = "dec";
    public static final String OCT_MODE = "oct";
    private int mode = 10 ;

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


    public void onOperatorKeyPressed(String opChar){
        if(nextNumber.equals("F"))
            model.inputValue(screen,mode);
        switch (opChar){
            case "+":
                model.inputOperation(Operations.Addition);
                break;
            case "-":
                model.inputOperation(Operations.Subtraction);
                break;
            case "*":
                model.inputOperation(Operations.Product);
                break;
            case "/":
                model.inputOperation(Operations.Division);
                break;
            case "and":
                model.inputOperation(Operations.LogicalAnd);
                break;
            case "or":
                model.inputOperation(Operations.LogicalOr);
                break;

        }
        //screen = "";



        if (!operating.equals("T")) {

            screen = model.readScreen(mode);
            nextNumber = "T";
        }
        else
            operating = "T";
    }

    public void onEqualKeyPressed(){
        if(!screen.equals("Error"))
            model.inputValue(screen,mode);
        screen = model.readScreen(mode);
        nextNumber = "T";
        operating = "F";

    }
    public void onModePressed(String modo){
        if (modo.equals(HEX_MODE)){
            mode = 16;
        }

        if (modo.equals(OCT_MODE)){
            mode = 8;
        }
        if (modo.equals(DEC_MODE)){
            mode = 10;
        }

        onEqualKeyPressed();
    }

    public void setState(String state){
        String[] aux = state.split(":");
        nextNumber = aux[0];
        screen = aux[1];
        operating = aux[2];
        mode = Integer.parseInt(aux[3]);
        model.setState(aux[4] + ":" + aux[5] + ":" + mode);
    }

    public String getState(){
        String aux = "" + nextNumber + ":" + screen + ":" + operating + ":" +mode+ ":" + model.getState();
        return aux;
    }
}
