package com.company.benzon.benzon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class PaymentCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private String customerName, customerSurname, fuelType, transactionDate, transactionTime;
    private float cost, volume, price;

    private TextView customerTV, fuelTypeTV, costTV, volumeTV, priceTV, dateTV, timeTV;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_check);

        button = findViewById(R.id.ok_button);
        button.setOnClickListener(this);

        initViews();
        initTransactionData(getIntent());
        setViewsData();

    }

    private void initViews(){
        customerTV = findViewById(R.id.customer);
        fuelTypeTV = findViewById(R.id.fuel_type);
        costTV = findViewById(R.id.cost);
        volumeTV = findViewById(R.id.volume);
        priceTV = findViewById(R.id.price);
        dateTV = findViewById(R.id.date);
        timeTV = findViewById(R.id.time);
    }

    private void initTransactionData(Intent intent){
        cost = intent.getFloatExtra("cost", 0);
        volume = intent.getFloatExtra("volume", 0);
        price = intent.getFloatExtra("price", 0);
        fuelType = intent.getStringExtra("fuel_type");
        transactionDate = intent.getStringExtra("date");
        transactionTime = intent.getStringExtra("time");
        customerName = GlobalData.user.getName();
        customerSurname= GlobalData.user.getSurname();
    }

    private void setViewsData(){
        String customer = customerSurname + " " + customerName;
        customerTV.setText(customer);
        fuelTypeTV.setText(fuelType);
        String costStr = String.format("%.2f", cost) + " Р";
        costTV.setText(costStr);
        String volumeStr = String.format("%.2f", volume) + " Л";
        volumeTV.setText(volumeStr);
        String priceStr = String.format("%.2f", price) + " Р";
        priceTV.setText(priceStr);
        dateTV.setText(transactionDate);
        timeTV.setText(transactionTime);
    }

    @Override
    public void onClick(View v) {
        /* сохранение транзакции в историю*/
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
