package com.example.borja.AdvancedCalculator.SimpleCalculator;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.borja.AdvancedCalculator.CalculatorFragmentBase;
import com.example.borja.AdvancedCalculator.R;

/**
 * Created by Hecate on 05/03/2015.
 */
public class CalculatorFragment extends CalculatorFragmentBase implements com.example.borja.AdvancedCalculator.SimpleCalculator.ICalculatorView {
    

    private Button.OnClickListener numberListener;
    private Button.OnClickListener operatorListener;
    private Button.OnClickListener decimalListener;
    private Button.OnClickListener equalListener;

    CalculatorViewModel viewModel;
    private static final String BUNDLE_VIEWMODEL_STATE = "ModelState";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewModel = new CalculatorViewModel(new CalculatorModel(), this);
        String state;
        if (savedInstanceState != null)
            state = savedInstanceState.getString(BUNDLE_VIEWMODEL_STATE);
        else
            state = getArguments().getString(BUNDLE_VIEWMODEL_STATE);
        setViewModelState(state);

    }
    public CalculatorFragment(){
        prepareListeners();
    }

    private void prepareListeners() {
        numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(v);
            }

        };
    }

    private void numberPressed(View view){
        Button button = (Button)view;
        viewModel.
    }
    @Override
    protected void setViewModelState(String state) {

    }

    @Override
    public String getViewModelState() {
        return null;
    }

    @Override
    public void showScreen(String value) {

    }
    private void addButton (Context context, String text, LinearLayout layout,View.OnClickListener listener, int buttonBackground){

        Button button = new Button(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        button.setLayoutParams(layoutParams);

        button.setHeight(0);
        button.setWidth(0);
        button.setText(text);
        Drawable background = getResources().getDrawable(buttonBackground);
        button.setBackground(background);
        button.setOnClickListener(listener);

        layout.addView(button);
    }

    private void addNumberButton(String value, LinearLayout layout, View view) {
        addButton(view.getContext(), value, layout, numberListener,
                R.drawable.color_button);
    }
    private  void addOperatorButton(String operator, LinearLayout layout, View view){
        addButton(view.getContext(),operator,layout,operatorListener,R.drawable.color_button_operator);
    }

    private void fillFirstRow(View view) {
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.first_row);
        addNumberButton("7", linearLayout, view);
        addNumberButton("8", linearLayout, view);
        addNumberButton("9", linearLayout, view);
        addOperatorButton("+", linearLayout, view);
    }


}
