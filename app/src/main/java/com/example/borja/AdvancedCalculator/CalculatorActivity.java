package com.example.borja.AdvancedCalculator;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.borja.AdvancedCalculator.R;

import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfView.BUTTONS;
import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfView.CALCULATOR;
import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfView.COMBINED;
import static com.example.borja.AdvancedCalculator.ViewModel.Orientation.LANDSCAPE;
import static com.example.borja.AdvancedCalculator.ViewModel.Orientation.PORTRAIT;

public class CalculatorActivity extends ActionBarActivity implements IDisplayView {


    ViewModel viewModel;
    TypeOfView typeOfView = CALCULATOR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_calculator2);
        viewModel = ViewModel.getInstance();
        ViewModel.Orientation orientation = LANDSCAPE;
        viewModel.currentViewChanged(this, orientation);
    }

    protected void onPause() {
        super.onPause();
        viewModel.viewReceivedOnPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void switchTo(TypeOfCalculator calculator, String state) {
        switch (calculator){
            case SIMPLE:
                switchCalculatorFragment("SimpleCalculator",state);
                break;
            case SCIENTIFIC:
                switchCalculatorFragment("ScientificCalculator",state);
                break;
            case PROGRAMMER:
                switchCalculatorFragment("ProgrammerCalculator",state);
                break;
        }
    }

    private void switchCalculatorFragment(String calculatorTag, String state) {
        FragmentManager fragmentManager = getFragmentManager();
        CalculatorFragmentBase currentCalculatorFragment = CalculatorManager.getCalculatorFragment(fragmentManager, calculatorTag,state);
        fragmentManager.beginTransaction()
                .replace(R.id.calculator_container, currentCalculatorFragment,
                        calculatorTag)
                .commit();
    }

    @Override
    public void switchTo(TypeOfView newTypeOfView, TypeOfCalculator calculator, String state) {
        if (newTypeOfView == CALCULATOR){
            switchTo(calculator,state);
        }
        else{
            finish();
        }
    }

    @Override
    public TypeOfView typeOfView() {
        return null;
    }

    @Override
    public String getCalculatorViewModelState() {
        return null;
    }
}
