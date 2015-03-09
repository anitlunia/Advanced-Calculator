package com.example.borja.AdvancedCalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by jvilar on 21/01/15.
 */
public abstract class CalculatorFragmentBase extends Fragment {
    protected static final String BUNDLE_VIEWMODEL_STATE = "ModelState";

    public static <T> CalculatorFragmentBase getInstance(FragmentManager fragmentManager, String tag, String state, Class<T> calculatorClass) {
        CalculatorFragmentBase instance = (CalculatorFragmentBase)fragmentManager.findFragmentByTag(tag);
        if (instance == null) {
            Bundle args = new Bundle();
            args.putString(BUNDLE_VIEWMODEL_STATE, state);
            try {
                instance = (CalculatorFragmentBase)calculatorClass.newInstance();
            } catch (java.lang.InstantiationException | IllegalAccessException e) {
                throw new IllegalArgumentException("Could not return instance of the calculator.");
            }
            instance.setArguments(args);
        } else {
            instance.setViewModelState(state);
        }
        return instance;
    }

    protected abstract void setViewModelState(String state);
    public abstract String getViewModelState();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String viewModelState = getViewModelState();
        outState.putString(BUNDLE_VIEWMODEL_STATE, viewModelState);
    }
}
