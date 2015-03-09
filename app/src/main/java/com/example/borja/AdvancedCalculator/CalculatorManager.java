package com.example.borja.AdvancedCalculator;
import android.app.FragmentManager;

/**
 * Created by jvilar on 12/01/15.
 */
public class CalculatorManager {
    static public final String SIMPLE_CALCULATOR = "SimpleCalculator";
    static public final String SCIENTIFIC_CALCULATOR = "ScientificCalculator";
    static public final String PROGRAMMER_CALCULATOR = "ProgrammerCalculator";

    public static CalculatorFragmentBase getCalculatorFragment(FragmentManager fragmentManager, String calculatorTag, String state) {
        CalculatorFragmentBase calculator;
        switch (calculatorTag) {
            case SCIENTIFIC_CALCULATOR:
                calculator = CalculatorFragmentBase.getInstance(fragmentManager, calculatorTag, state,
                        com.example.borja.AdvancedCalculator.ScientificCalculator.CalculatorFragment.class);
                break;
            case SIMPLE_CALCULATOR:
                calculator = CalculatorFragmentBase.getInstance(fragmentManager, calculatorTag, state,
                        com.example.borja.AdvancedCalculator.SimpleCalculator.CalculatorFragment.class);
                break;
            case PROGRAMMER_CALCULATOR:
                calculator = CalculatorFragmentBase
                                     .getInstance(fragmentManager, calculatorTag, state,
                                             com.example.borja.AdvancedCalculator.ProgrammerCalculator.CalculatorFragment.class);
                break;
            default:
                throw new IllegalArgumentException("Unknown FragmentTag");
        }
        return calculator;
    }
}
