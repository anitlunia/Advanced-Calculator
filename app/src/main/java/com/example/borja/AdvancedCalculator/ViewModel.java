package com.example.borja.AdvancedCalculator;

import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfCalculator;
import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfCalculator.SIMPLE;
import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfView;
import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfView.*;
import static com.example.borja.AdvancedCalculator.ViewModel.Orientation.PORTRAIT;

/**
 * Created by Hecate on 26/02/2015.
 */
public class ViewModel {

    public enum Orientation { LANDSCAPE, PORTRAIT }

    IDisplayView currentView;
    TypeOfCalculator currentCalculator;

    String[] calculatorState;

    private static ViewModel instance;


    private ViewModel(){
        calculatorState = new String[3];
        currentCalculator = SIMPLE;
        newTypeOfCalculatorSelected(currentCalculator);
    }

    public static ViewModel getInstance() {
        if (instance == null)
            instance = new ViewModel();
        return instance;
    }

    public void currentViewChanged(IDisplayView newView, Orientation orientation) {
        switch (newView.typeOfView()) {
        case BUTTONS:
            if(currentView.typeOfView() == COMBINED)
                switchToViewAndCalculator(CALCULATOR, currentCalculator);
            break;
        case CALCULATOR:
            if(orientation == PORTRAIT)
                switchToCalculator(currentCalculator);
            else
                switchToViewAndCalculator(COMBINED, currentCalculator);
            break;
        case COMBINED:
            switchToCalculator(currentCalculator);
        }
    }

    public void newTypeOfCalculatorSelected(TypeOfCalculator calculator) {
        if (currentView.typeOfView() == BUTTONS) {
            switchToViewAndCalculator(CALCULATOR, calculator);
        } else
            switchToCalculator(calculator);
    }
    private void switchToCalculator(TypeOfCalculator calculator) {
        saveCurrentCalculatorState();
        currentCalculator = calculator;
        currentView.switchTo(currentCalculator,
                calculatorState[currentCalculator.ordinal()]);
    }

    private void switchToViewAndCalculator(TypeOfView typeOfView,
                                           TypeOfCalculator calculator) {
        saveCurrentCalculatorState();
        currentCalculator = calculator;
        currentView.switchTo(typeOfView, currentCalculator,
                calculatorState[currentCalculator.ordinal()]);
    }

    private void saveCurrentCalculatorState(){
        if(currentView.typeOfView() != BUTTONS)
            calculatorState[currentCalculator.ordinal()] = currentView.getCalculatorViewModelState();
    }
    public void viewReceivedOnPause(){
        saveCurrentCalculatorState();
    }



}
