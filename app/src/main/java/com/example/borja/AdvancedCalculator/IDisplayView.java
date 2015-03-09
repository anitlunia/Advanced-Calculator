package com.example.borja.AdvancedCalculator;
/**
 * Created by Hecate on 26/02/2015.
 */


public interface IDisplayView {
    public enum TypeOfView {BUTTONS, CALCULATOR, COMBINED}
    public enum TypeOfCalculator { SIMPLE, SCIENTIFIC, PROGRAMMER}

    void switchTo(TypeOfCalculator calculator, String state);
    void switchTo(TypeOfView newTypeOfView, TypeOfCalculator calculator,
                  String state);
    TypeOfView typeOfView();
    String getCalculatorViewModelState();
}
