package com.company.benzon.benzon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.company.benzon.benzon.views.FuelTypeView;

public class PaymentActivity extends AppCompatActivity {

    private float currentFuelCost;
    private String currentFuelType;

    private EditText moneyEditText, fuelVolumeEditText;

    private float pricePerVolume, volumePerMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        moneyEditText = findViewById(R.id.cost_edit_text);
        fuelVolumeEditText = findViewById(R.id.fuel_volume);

        moneyEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 6) {        //нажатие enter на клавиатуре
                    refreshVolume();
                }
                return false;
            }
        });
        fuelVolumeEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(getApplicationContext(),"ActionID: " + actionId, Toast.LENGTH_SHORT).show();
                if (actionId == 6) {
                    refreshPrice();
                }
                return false;
            }
        });
    }

    public void setCurrentFuelStats(float price, String type){
        currentFuelCost = price;
        currentFuelType = type;
    }

    private void calculatePricePerFuelVolume(){
        if(currentFuelCost != 0) {
            pricePerVolume = volumePerMoney * currentFuelCost;
        }
        else{
            pricePerVolume = 0;
        }
    }

    private void calculateVolumePerPrice(){
        if(currentFuelCost != 0) {
            volumePerMoney = pricePerVolume / currentFuelCost;
        }
        else{
            volumePerMoney = 0;
        }
    }

    public void refreshVolume(){
        pricePerVolume = Float.parseFloat(moneyEditText.getText().toString());
        calculateVolumePerPrice();
        fuelVolumeEditText.setText("" + volumePerMoney);
    }

    public void refreshPrice(){
        volumePerMoney = Float.parseFloat(fuelVolumeEditText.getText().toString());
        calculatePricePerFuelVolume();
        moneyEditText.setText("" + pricePerVolume);
    }

    public void fuelUpButtonClick(View view){
        PaymentProofDialog dialog = new PaymentProofDialog(this, this, currentFuelType, volumePerMoney, pricePerVolume, currentFuelCost);
        dialog.show();
    }

}
