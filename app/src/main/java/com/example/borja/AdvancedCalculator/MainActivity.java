package com.example.borja.AdvancedCalculator;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfCalculator.SIMPLE;
import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfView.BUTTONS;
import static com.example.borja.AdvancedCalculator.IDisplayView.TypeOfView.COMBINED;
import static com.example.borja.AdvancedCalculator.ViewModel.Orientation.LANDSCAPE;
import static com.example.borja.AdvancedCalculator.ViewModel.Orientation.PORTRAIT;


public class MainActivity extends ActionBarActivity implements IDisplayView {


    ViewModel viewModel;
    TypeOfView typeOfView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_container);
        boolean isLandscape = orientationIsLandscape();
        typeOfView = isLandscape ? COMBINED : BUTTONS;
        viewModel = ViewModel.getInstance();
        ViewModel.Orientation orientation = isLandscape ? LANDSCAPE: PORTRAIT;
        viewModel.currentViewChanged(this, orientation);
    }

    private boolean orientationIsLandscape () {
        return getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
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



    public void onSimpleCalculatorPressed(){
        viewModel.newTypeOfCalculatorSelected(SIMPLE);
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

    @Override
    public void switchTo(TypeOfView newTypeOfView, TypeOfCalculator calculator, String state) {

        Intent intent;
        switch (newTypeOfView) {
            case BUTTONS:
                break;
            case CALCULATOR:
                intent = new Intent(this, CalculatorActivity.class);
                startActivity(intent);
                break;
            case COMBINED:
                switchTo(calculator, state);
                break;
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

    private void switchCalculatorFragment(String calculatorTag, String state) {
        FragmentManager fragmentManager = getFragmentManager();
        CalculatorFragmentBase currentCalculatorFragment = CalculatorManager.getCalculatorFragment(fragmentManager, calculatorTag,state);
        fragmentManager.beginTransaction()
                .replace(R.id.calculator_container, currentCalculatorFragment,
                        calculatorTag)
                .commit();
    }



}
