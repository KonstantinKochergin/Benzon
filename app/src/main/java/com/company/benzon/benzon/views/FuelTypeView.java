package com.company.benzon.benzon.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.company.benzon.benzon.R;

public class FuelTypeView extends ConstraintLayout {

    private TextView fuelTypeTextView;
    private TextView fuelPriceTextView;
    private ConstraintLayout layout;

    private boolean isActive = false;


    public FuelTypeView(Context context) {
        super(context);
        init();
    }

    public FuelTypeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FuelTypeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.fuel_type_view, this);
        fuelTypeTextView = findViewById(R.id.fuel_type);
        fuelPriceTextView = findViewById(R.id.fuel_price);
        layout = findViewById(R.id.main_layout);
    }

    public void setFuelType(String fuelType){
        fuelTypeTextView.setText(fuelType);
    }

    public void setFuelPrice(float price){
        fuelPriceTextView.setText(String.valueOf(price));
    }

    public String getFuelType(){
        return fuelTypeTextView.getText().toString();
    }

    public float getFuelPrice(){
        return Float.parseFloat(fuelPriceTextView.getText().toString());
    }

    public void changeStatus(){
        if(!isActive){
            isActive = true;

        }
        else{

        }
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        layout.setBackgroundColor(color);
    }
}
